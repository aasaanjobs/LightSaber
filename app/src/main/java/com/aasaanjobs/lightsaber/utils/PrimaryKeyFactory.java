package com.aasaanjobs.lightsaber.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

import static java.lang.String.format;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */
public class PrimaryKeyFactory {

    private static final String PRIMARY_KEY_FIELD = "id";


    private final static PrimaryKeyFactory instance = new PrimaryKeyFactory();
    private Map<Class<? extends RealmModel>, AtomicLong> keys;

    public static PrimaryKeyFactory getInstance() {
        return instance;
    }

    public synchronized void initialize(final Realm realm) {
        if (keys != null) {
            throw new IllegalStateException("already initialized");
        }
        keys = new HashMap<>();
        final RealmConfiguration configuration = realm.getConfiguration();
        final RealmSchema realmSchema = realm.getSchema();
        for (final Class<? extends RealmModel> c : configuration.getRealmObjectClasses()) {

            final RealmObjectSchema objectSchema = realmSchema.get(c.getSimpleName());
            Log.i(getClass().getSimpleName(), format("schema for class %s : %s", c.getName(), objectSchema));
            if (objectSchema != null && objectSchema.hasPrimaryKey()) {
                Number keyValue = null;
                try {
                    keyValue = realm.where(c).max(PRIMARY_KEY_FIELD);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.d(getClass().getSimpleName(), format("error while getting number primary key %s " +
                            " for %s",PRIMARY_KEY_FIELD, c.getName()), ex);
                }
                if (keyValue == null) {
                    Log.w(getClass().getSimpleName(), format("can't find number primary key %s " +
                            " for %s.",PRIMARY_KEY_FIELD, c.getName()));
                } else {
                    keys.put(c, new AtomicLong(keyValue.longValue()));
                }
            }
        }
    }

    public synchronized long nextKey(final Class<? extends RealmModel> clazz) {
        if (keys == null) {
            throw new IllegalStateException("not initialized yet");
        }
        AtomicLong l = keys.get(clazz);
        if (l == null) {
            Log.i(getClass().getSimpleName(), "There was no primary keys for " + clazz.getName());
            l = new AtomicLong(0);
            keys.put(clazz, l);
        }
        return l.incrementAndGet();
    }

}