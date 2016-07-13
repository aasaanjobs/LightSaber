package com.aasaanjobs.lightsaber.data.db.utils;

import android.content.Context;

import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.utils.FileUtil;

import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

@Singleton
public class RealmManager implements RealmService {

    private static final String LIGHT_SABER_APP = "LightSaber.realm";
    public static final long SCHEMA_VERSION = 1;
    private Realm realm;

    public RealmManager(@ApplicationContext final Context context, FileUtil fileUtil) {
        RealmConfiguration configuration = new RealmConfiguration
                .Builder(context)
                .name(LIGHT_SABER_APP)
                .schemaVersion(SCHEMA_VERSION)
                .migration(new LightSaberRealmMigration())
                .build();
        realm = Realm.getInstance(configuration);

//        RealmAssetHelper assetHelper = new RealmAssetHelper(context, fileUtil);
//        assetHelper.loadDatabaseToStorage(LIGHT_SABER_APP, new RealmAssetHelperStorageListener() {
//            @Override
//            public void onLoadedToStorage(String filePath, RealmAssetHelperStatus status) {
//
//            }
//        });
    }

    @Override
    public <T extends RealmModel>void insert(final T t, final RealmRepoListener<T> listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @SuppressWarnings("unchecked")
            @Override
            public void onSuccess() {
                listener.onSuccess((RealmResults<T>) read(t.getClass()));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public <T extends RealmModel> void insertAll(final List<T> t, final RealmRepoListener<T> listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @SuppressWarnings("unchecked")
            @Override
            public void onSuccess() {
                listener.onSuccess((RealmResults<T>) read(t.get(0).getClass()));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public Realm getRealm() {
        return this.realm;
    }

    @Override
    public <T extends RealmModel>void insert(final T t) {realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        });
    }

    @Override
    public <T extends RealmModel> void insertAll(final List<T> t) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        });
    }

    @Override
    public <T extends RealmModel> RealmResults<T> read(Class<T> clazz) {
        return realm.where(clazz).findAll();
    }

    @Override
    public <T extends RealmModel> RealmResults<T> read(RealmQuery<T> query) {
        return query.findAll();
    }

    @Override
    public <T extends RealmModel> T readFirst(Class<T> clazz) {
        return realm.where(clazz).findFirst();
    }

    @Override
    public <T extends RealmModel> T readFirst(RealmQuery<T> query) {
        return query.findFirst();
    }

    @Override
    public <T extends RealmModel> void delete(Class<T> clazz) {
        realm.delete(clazz);
    }

    @Override
    public <T extends RealmModel> void delete(RealmQuery<T> query) {
        RealmResults<T> results = query.findAll();
        results.deleteAllFromRealm();
    }

    @Override
    public <T extends RealmModel> void deleteAll() {
        realm.deleteAll();
    }

    @Override
    public String getDatabaseName() {
        return realm.getConfiguration().getRealmFileName();
    }
}
