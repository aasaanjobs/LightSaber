package com.aasaanjobs.lightsaber.data.db.utils;

import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
public interface RealmRepoListener<T extends RealmModel> {
    void onSuccess(RealmResults<T> results);
    void onError(Throwable error);
}
