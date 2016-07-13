package com.aasaanjobs.lightsaber.utils;

import android.content.Context;
import android.widget.Toast;


import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */
@Singleton
public class ExceptionUtil {

    private final Context context;
    private final LoggerUtil loggerUtil;
    private String GENERAL_MESSAGE = "Something went wrong";

    @Inject
    public ExceptionUtil(@ApplicationContext Context context
            , LoggerUtil loggerUtil) {
        this.context = context;
        this.loggerUtil = loggerUtil;
    }

    public void showException(Exception e) {
        String message = e.getMessage();
        if(message != null && !message.isEmpty()) {
            message = GENERAL_MESSAGE;
        }
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        loggerUtil.error(message);
    }

    public void logException(Exception e) {
        String message = e.getMessage();
        if(message != null && !message.isEmpty()) {
            message = GENERAL_MESSAGE;
        }
        loggerUtil.error(message);
    }
}
