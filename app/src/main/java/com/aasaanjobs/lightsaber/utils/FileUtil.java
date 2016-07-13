package com.aasaanjobs.lightsaber.utils;

import android.content.Context;
import android.text.TextUtils;

import com.aasaanjobs.lightsaber.data.preferences.SharedPrefService;
import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.root.exceptions.ValueNotStoredException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Singleton;


/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */

@Singleton
public class FileUtil {

    private static final String DB_VERSION = "1";
    private final Context context;
    private final SharedPrefService prefService;
    private String cachedAssetPath;
    private static final String VERSION_PATTERN = "_\\d+\\.realm";

    public FileUtil(@ApplicationContext Context context,
                    SharedPrefService prefService) {
        this.context = context;
        this.prefService = prefService;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void copy(File src, File dst) throws IOException {
        if(dst.exists()) {
            dst.delete();
        }
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String loadDatabaseToLocalStorage(String databaseName) {
        String asset = findAsset( "", databaseName);

        File file = new File(generateDatabaseFileName(databaseName));
        if (file.exists()) {
            file.delete();
        }
        try {
            InputStream is = context.getAssets().open(asset);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return file.getName();
    }

    public String generateDatabaseFileName(String databaseName) {
        return context.getFilesDir() + File.separator + databaseName + ".realm";
    }

    public String getFileNameForDatabase(String databaseName) {
        return new File(generateDatabaseFileName(databaseName)).getName();
    }

    public Integer getCurrentDbVersion(String databaseName) {
        int currentVersion = -1;
        try {
            currentVersion = prefService.retrieveValue(DB_VERSION, -1);
        } catch (ValueNotStoredException e) {
            e.printStackTrace();
        }
        return currentVersion == -1 ? null : currentVersion;
    }

    public int getAssetsDbVersion(String databaseName) {
        String dbAsset = findAsset( "", databaseName);
        Pattern pattern = Pattern.compile(VERSION_PATTERN);
        Matcher matcher = pattern.matcher(dbAsset);

        if(matcher.find()) {
            String version = matcher.group().substring(1, matcher.group().indexOf('.'));
            return Integer.parseInt(version);
        }
        return 0;
    }

    public void storeDatabaseVersion(int version) {
        prefService.storeValue(DB_VERSION, version);
    }

    public boolean isDatabaseAssetExists(String databaseName) {
        return !TextUtils.isEmpty(findAsset("", databaseName));
    }

    public void clearCache() {
        cachedAssetPath = null;
    }

    private String findAsset(String path, String databaseName) {
        if (!TextUtils.isEmpty(cachedAssetPath)) {
            return cachedAssetPath;
        } else {
            try {
                String[] list;
                list = context.getAssets().list(path);
                if (list.length > 0) {
                    for (String file : list) {
                        String asset = findAsset(TextUtils.isEmpty(path) ? file : path
                                        + File.separator + file,
                                databaseName);
                        if (!TextUtils.isEmpty(asset)) {
                            return asset;
                        }
                    }
                } else {
                    String fileName = new File(path).getName();
                    if (!TextUtils.isEmpty(fileName)) {
                        if (fileName.matches(databaseName + VERSION_PATTERN)
                                || fileName.matches(databaseName + ".realm")) {
                            cachedAssetPath = path;
                            return path;
                        }
                    }
                }
            } catch (IOException e) {
                return null;
            }
            return null;
        }
    }

}
