package com.aasaanjobs.lightsaber.root.exceptions;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */
public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
