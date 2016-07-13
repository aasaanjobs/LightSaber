package com.aasaanjobs.lightsaber.data.db.utils;

import android.content.Context;

import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.utils.FileUtil;
import com.aasaanjobs.lightsaber.utils.StringUtil;


/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */

public class RealmAssetHelper {

    private final FileUtil fileUtil;

    @ApplicationContext
    protected Context context;

    public RealmAssetHelper(Context context, FileUtil fileUtil) {
        this.context = context;
        this.fileUtil = fileUtil;
    }

    public void loadDatabaseToStorage(String databaseName, RealmAssetHelperStorageListener listener)
            throws RuntimeException {
        fileUtil.clearCache();
        if (StringUtil.isEmpty(databaseName)) {
            throw new RuntimeException("The database name is empty");
        }

        if (!fileUtil.isDatabaseAssetExists(databaseName)) {
            throw new RuntimeException("An asset for requested database doesn't exist");
        }

        Integer currentDbVersion = fileUtil.getCurrentDbVersion(databaseName);
        int assetsDbVersion = fileUtil.getAssetsDbVersion(databaseName);

        //fresh install
        if (currentDbVersion == null) {
            String path = fileUtil.loadDatabaseToLocalStorage(databaseName);
            if (StringUtil.isEmpty(path)) {
                throw new RuntimeException("Can't find copied file");
            }
            fileUtil.storeDatabaseVersion(assetsDbVersion);
            if (listener != null) {
                listener.onLoadedToStorage(path, RealmAssetHelperStatus.INSTALLED);
            }
        } else {
            //update required
            if (assetsDbVersion > currentDbVersion) {
                String path = fileUtil.loadDatabaseToLocalStorage(databaseName);
                if (StringUtil.isEmpty(path)) {
                    throw new RuntimeException("Can't find copied file");
                }
                fileUtil.storeDatabaseVersion(assetsDbVersion);
                if (listener != null) {
                    listener.onLoadedToStorage(path, RealmAssetHelperStatus.UPDATED);
                }
                //do not update
            } else {

                String path = fileUtil.getFileNameForDatabase(databaseName);
                if (StringUtil.isEmpty(path)) {
                    throw new RuntimeException("Can't find copied file");
                }
                if (listener != null) {
                    listener.onLoadedToStorage(path, RealmAssetHelperStatus.IGNORED);
                }
            }
        }
    }
}