package com.aasaanjobs.lightsaber.utils;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by nazmuddinmavliwala on 22/05/16.
 */

public class LoggerUtil {

    private String tag = "AasaanjobsEmployer";

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Inject
    public LoggerUtil() {
    }

    public LoggerUtil(String tag) {
        this.tag = tag;
    }

    public void info(String message) {
        Log.i(tag, message);
    }

    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    public void debug(String message) {
        Log.d(tag,message);
    }

    public void debug(String tag, String message) {
        Log.d(tag,message);
    }

    public void error(String message) {
        Log.e(tag,message);
    }

    public void error(String tag, String message) {
        Log.e(tag,message);
    }

    public void warn(String message) {
        Log.w(tag,message);
    }

    public void warn(String tag, String message) {
        Log.w(tag,message);
    }

    public void wtf(String message) {
        Log.wtf(tag,message);
    }

    public void wtf(String tag, String message) {
        Log.wtf(tag,message);
    }
}
