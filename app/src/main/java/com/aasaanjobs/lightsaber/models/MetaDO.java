package com.aasaanjobs.lightsaber.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

public class MetaDO {

    @SerializedName("limit")
    private String limit;

    @SerializedName("previous")
    private String previous;

    @SerializedName("offset")
    private String offset;

    @SerializedName("count")
    private String count;

    @SerializedName("next")
    private String next;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
