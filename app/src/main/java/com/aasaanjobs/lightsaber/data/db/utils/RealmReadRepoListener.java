package com.aasaanjobs.lightsaber.data.db.utils;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
public interface RealmReadRepoListener<T> {
    void onSuccess(T t);
    void onError(Throwable error);
}
