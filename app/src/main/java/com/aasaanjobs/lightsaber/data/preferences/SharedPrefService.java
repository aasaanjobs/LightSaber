package com.aasaanjobs.lightsaber.data.preferences;

import com.aasaanjobs.lightsaber.root.exceptions.ValueNotStoredException;

import java.util.Map;
import java.util.Set;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public interface SharedPrefService {

    void storeValue(String key, int value);

    void storeValue(String key, float value);

    void storeValue(String key, long value);

    void storeValue(String key, boolean value);

    void storeValue(String key, String value);

    void storeValue(String key, Set<String> value);

    float retrieveValue(String key, float defaultValue) throws ValueNotStoredException;

    long retrieveValue(String key, long defaultValue) throws ValueNotStoredException;

    String retrieveValue(String key, String defaultValue) throws ValueNotStoredException;

    boolean retrieveValue(String key, boolean defaultValue) throws ValueNotStoredException;

    int retrieveValue(String key, int defaultValue) throws ValueNotStoredException;

    Set<String> retrieveValue(String key, Set<String> defaultValue) throws ValueNotStoredException;

    Map<String, ?> retrieveAll();

    void removeValue(String key);

    void clearPref();

}
