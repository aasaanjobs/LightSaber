package com.aasaanjobs.lightsaber.models;

import com.google.gson.annotations.SerializedName;

import io.realm.annotations.Ignore;


/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

public class BaseDO<T>  {

    @SerializedName("_version")
    private int version;

    @Ignore
    @SerializedName("found")
    private boolean found;

    @Ignore
    @SerializedName("_index")
    private String index;

    @Ignore
    @SerializedName("_id")
    private String id;

    @Ignore
    @SerializedName("_type")
    private String type;

    @Ignore
    @SerializedName("_source")
    private T source;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
