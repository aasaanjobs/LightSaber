package com.aasaanjobs.lightsaber.data.db.tables;


import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */

@RealmClass
public class RealmFloat implements RealmModel {

    private Float value;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
