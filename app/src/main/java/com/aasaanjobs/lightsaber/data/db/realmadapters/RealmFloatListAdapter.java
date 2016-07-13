package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmFloat;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmFloatListAdapter extends TypeAdapter<RealmList<RealmFloat>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmFloat> value) throws IOException {
        out.beginArray();
        for(RealmFloat realmFloat : value) {
            out.value(realmFloat.getValue());
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmFloat> read(JsonReader in) throws IOException {
        RealmList<RealmFloat> realmFloats = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmFloat realmFloat = new RealmFloat();
            realmFloat.setValue(Float.valueOf(in.nextName()));
            realmFloats.add(realmFloat);
        }
        in.endArray();
        return realmFloats;
    }
}
