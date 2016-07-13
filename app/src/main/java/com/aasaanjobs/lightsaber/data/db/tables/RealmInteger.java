package com.aasaanjobs.lightsaber.data.db.tables;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */

@RealmClass
public class RealmInteger implements RealmModel {

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
