package com.aasaanjobs.lightsaber.data.db.tables;


import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */

@RealmClass
public class RealmDouble implements RealmModel {

    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
