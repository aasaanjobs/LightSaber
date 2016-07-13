package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmString;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmStringListAdapter extends TypeAdapter<RealmList<RealmString>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
       try {
           out.beginArray();
           for(RealmString realmString : value) {
               out.value(realmString.getValue());
           }
           out.endArray();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public RealmList<RealmString> read(JsonReader in) throws IOException {
        try {
            in.beginArray();
            RealmList<RealmString> realmStrings = new RealmList<>();
            while (in.hasNext()) {
                RealmString realmString = new RealmString();
                realmString.setValue(in.nextString());
                realmStrings.add(realmString);
            }
            in.endArray();
            return realmStrings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RealmList<>();
    }
}
