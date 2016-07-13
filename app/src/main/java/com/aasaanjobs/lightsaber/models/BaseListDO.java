package com.aasaanjobs.lightsaber.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.annotations.Ignore;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

public class BaseListDO<T> {

    @Ignore
    @SerializedName("meta")
    private MetaDO metaDO;

    @SerializedName("objects")
    private List<T> objects;

    public MetaDO getMetaDO() {
        return metaDO;
    }

    public void setMetaDO(MetaDO metaDO) {
        this.metaDO = metaDO;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
