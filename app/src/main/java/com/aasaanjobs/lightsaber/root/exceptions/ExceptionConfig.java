package com.aasaanjobs.lightsaber.root.exceptions;


import com.aasaanjobs.lightsaber.R;

import java.util.HashMap;


/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public class ExceptionConfig {

    private static HashMap<Integer,Integer> exceptionDictionary = new HashMap<>();

    static {
        store(ExceptionCodeConstants.VALUE_NOT_FOUND, R.string.app_name);
    }

    private static void store(Integer valueNotFound, int app_name) {

    }

}
