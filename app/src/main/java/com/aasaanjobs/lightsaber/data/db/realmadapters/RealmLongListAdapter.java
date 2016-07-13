package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmLong;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmLongListAdapter extends TypeAdapter<RealmList<RealmLong>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmLong> value) throws IOException {
        out.beginArray();
        for(RealmLong realmLong : value) {
            out.value(realmLong.getValue());
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmLong> read(JsonReader in) throws IOException {
        RealmList<RealmLong> realmLongs = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmLong realmLong = new RealmLong();
            realmLong.setValue(in.nextLong());
            realmLongs.add(realmLong);
        }
        in.endArray();
        return realmLongs;
    }
}
