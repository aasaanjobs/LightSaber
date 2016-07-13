package com.aasaanjobs.lightsaber.data.db.tables;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */

@RealmClass
public class RealmLong implements RealmModel {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
