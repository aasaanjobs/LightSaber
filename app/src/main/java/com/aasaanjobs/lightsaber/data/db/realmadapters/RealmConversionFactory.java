package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import io.realm.RealmModel;

public interface RealmConversionFactory<T extends RealmModel> extends JsonSerializer<T>, JsonDeserializer<T> {

}
