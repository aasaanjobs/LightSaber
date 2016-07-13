package com.aasaanjobs.lightsaber.data;

import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public interface ServiceRepoListener<T extends RealmModel> {
    void onSuccess(RealmResults<T> realmResults);

    void onError(Throwable error);
}
