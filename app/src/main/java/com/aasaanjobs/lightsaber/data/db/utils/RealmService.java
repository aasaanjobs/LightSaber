package com.aasaanjobs.lightsaber.data.db.utils;

import java.io.Serializable;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
public interface RealmService extends Serializable {

    Realm getRealm();

    <T extends RealmModel> void insert(final T t);

    <T extends RealmModel> void insertAll(final List<T> t);

    <T extends RealmModel> void insert(final T t, RealmRepoListener<T> listener);

    <T extends RealmModel> void insertAll(final List<T> t, RealmRepoListener<T> listener);

    <T extends RealmModel> RealmResults<T> read(Class<T> clazz);

    <T extends RealmModel> RealmResults<T> read(RealmQuery<T> query);

    <T extends RealmModel> T readFirst(Class<T> clazz);

    <T extends RealmModel> T readFirst(RealmQuery<T> query);

    <T extends RealmModel> void delete(Class<T> clazz);

    <T extends RealmModel> void delete(RealmQuery<T> query);

    <T extends RealmModel> void deleteAll();

    String getDatabaseName();
}
