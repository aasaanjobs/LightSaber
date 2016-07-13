package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmInteger;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmIntListAdapter extends TypeAdapter<RealmList<RealmInteger>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmInteger> value) throws IOException {
        out.beginArray();
        for(RealmInteger realmInteger : value) {
            out.value(realmInteger.getValue());
        }
        out.endArray();
    }

    @Override
    public RealmList<RealmInteger> read(JsonReader in) throws IOException {
        RealmList<RealmInteger> realmIntegers = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmInteger realmInteger = new RealmInteger();
            realmInteger.setValue(in.nextInt());
            realmIntegers.add(realmInteger);
        }
        in.endArray();
        return realmIntegers;
    }
}
