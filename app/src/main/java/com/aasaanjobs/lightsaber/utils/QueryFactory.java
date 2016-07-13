package com.aasaanjobs.lightsaber.utils;


import com.aasaanjobs.lightsaber.root.exceptions.EmptyQueryException;

import java.util.HashMap;

import okhttp3.HttpUrl;

/**
 * Created by nazmuddinmavliwala on 07/06/16.
 */
public class QueryFactory {


    public static HashMap<String, String> extractQueries(String url) throws EmptyQueryException {
        if(url == null) {
            throw new EmptyQueryException();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        HttpUrl httpUrl = HttpUrl.parse(url);
        for (int i = 0, size = httpUrl.querySize(); i < size; i++) {
            hashMap.put(httpUrl.queryParameterName(i),httpUrl.queryParameterValue(i));
        }

        return hashMap;
    }

}
