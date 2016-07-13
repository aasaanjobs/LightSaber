package com.aasaanjobs.lightsaber.data.db.utils;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public class LightSaberRealmMigration implements RealmMigration {

    private static final String TAG = "RealmConfig";

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        //handle migrations here
        Log.i(TAG,String.valueOf(oldVersion));
        Log.i(TAG,String.valueOf(newVersion));
    }
}
