package com.aasaanjobs.lightsaber.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.root.exceptions.ValueNotStoredException;

import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;


/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
@Singleton
public class SharedPrefManager implements SharedPrefService {

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    public SharedPrefManager(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(
                         SharedPrefConstants.SHARED_PREF_NAME
                        ,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public void storeValue(String key, int value) {
        this.editor.putInt(key,value);
        this.editor.commit();
    }

    @Override
    public void storeValue(String key, float value) {
        this.editor.putFloat(key,value);
        this.editor.commit();
    }

    @Override
    public void storeValue(String key, long value) {
        this.editor.putLong(key,value);
        this.editor.commit();
    }

    @Override
    public void storeValue(String key, boolean value) {
        this.editor.putBoolean(key,value);
        this.editor.commit();
    }

    @Override
    public void storeValue(String key, String value) {
        this.editor.putString(key,value);
        this.editor.commit();
    }

    @Override
    public void storeValue(String key, Set<String> value) {
        this.editor.putStringSet(key, value);
        this.editor.commit();
    }

    @Override
    public float retrieveValue(String key, float defaultValue)
            throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getFloat(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public long retrieveValue(String key, long defaultValue)
            throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getLong(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public String retrieveValue(String key, String defaultValue)
            throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getString(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public boolean retrieveValue(String key, boolean defaultValue)
            throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getBoolean(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public int retrieveValue(String key, int defaultValue) throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getInt(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public Set<String> retrieveValue(String key, Set<String> defaultValue)
            throws ValueNotStoredException {
        if(isKeyPresent(key)) {
            return preferences.getStringSet(key,defaultValue);
        } else {
            throw new ValueNotStoredException();
        }
    }

    @Override
    public Map<String, ?> retrieveAll() {
        return preferences.getAll();
    }

    @Override
    public void removeValue(String key) {
        this.editor.remove(key).commit();
    }

    @Override
    public void clearPref() {
        this.editor.clear().commit();
    }

    private boolean isKeyPresent(String key) {
        return preferences.contains(key);
    }

}

