package com.aasaanjobs.lightsaber.data.db.utils;

import java.util.List;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public class ElasticFilter {
    private List<FilterModel> AND;
    private List<FilterModel> OR;

    public List<FilterModel> getAND() {
        return AND;
    }

    public void setAND(List<FilterModel> AND) {
        this.AND = AND;
    }

    public List<FilterModel> getOR() {
        return OR;
    }

    public void setOR(List<FilterModel> OR) {
        this.OR = OR;
    }
}
