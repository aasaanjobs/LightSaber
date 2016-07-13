package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmBoolean;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmBooleanListAdapter extends TypeAdapter<RealmList<RealmBoolean>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmBoolean> value) throws IOException {
        out.beginArray();
        for(RealmBoolean realmBoolean : value) {
            out.value(realmBoolean.getValue());
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmBoolean> read(JsonReader in) throws IOException {
        RealmList<RealmBoolean> realmBooleans = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmBoolean realmBoolean = new RealmBoolean();
            realmBoolean.setValue(in.nextBoolean());
            realmBooleans.add(realmBoolean);
        }
        in.endArray();
        return realmBooleans;
    }
}
