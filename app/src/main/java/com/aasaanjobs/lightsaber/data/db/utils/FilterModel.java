package com.aasaanjobs.lightsaber.data.db.utils;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public class FilterModel {

    private String key;
    private ElasticOperator elasticOperator;
    private String value;
    private boolean exists;
    private boolean missing;
    private long upper;
    private long lower;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setElasticOperator(ElasticOperator elasticOperator) {
        this.elasticOperator = elasticOperator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public long getUpper() {
        return upper;
    }

    public void setUpper(long upper) {
        this.upper = upper;
    }

    public long getLower() {
        return lower;
    }

    public void setLower(long lower) {
        this.lower = lower;
    }

    public ElasticOperator getElasticOperator() {
        return null;
    }
}
