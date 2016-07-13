package com.aasaanjobs.lightsaber.data.db.tables;


import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
@RealmClass
public class RealmBoolean implements RealmModel {

    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
