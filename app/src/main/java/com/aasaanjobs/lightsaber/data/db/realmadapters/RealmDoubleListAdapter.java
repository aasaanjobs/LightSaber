package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmDouble;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmDoubleListAdapter extends TypeAdapter<RealmList<RealmDouble>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmDouble> value) throws IOException {
        out.beginArray();
        for(RealmDouble realmDouble : value) {
            out.value(realmDouble.getValue());
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmDouble> read(JsonReader in) throws IOException {
        RealmList<RealmDouble> realmDoubles = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmDouble realmDouble = new RealmDouble();
            realmDouble.setValue(in.nextDouble());
            realmDoubles.add(realmDouble);
        }
        in.endArray();
        return realmDoubles;
    }
}
