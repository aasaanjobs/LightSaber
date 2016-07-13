package com.aasaanjobs.lightsaber.data.db.utils;

import java.util.HashMap;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public class ElasticQueryObject {
    private String path;
    private HashMap<String,String> queryMap;
    private ElasticFilter filter;

    public HashMap<String, String> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(HashMap<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public ElasticFilter getFilter() {
        return filter;
    }

    public void setFilter(ElasticFilter filter) {
        this.filter = filter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
