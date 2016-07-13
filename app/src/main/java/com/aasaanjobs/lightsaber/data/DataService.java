package com.aasaanjobs.lightsaber.data;

import com.aasaanjobs.lightsaber.data.network.RetrofitRepoListener;
import com.aasaanjobs.lightsaber.models.BaseListDO;

import io.realm.RealmModel;
import io.realm.RealmResults;
import retrofit2.Call;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */
public interface DataService {

    <T> T create(final Class<T> service);

    <P extends RealmModel,T extends BaseListDO<P>> void getList(Class<P> clazz, Call<T> call
            , ServiceRepoListener<P> listener);

    <T extends RealmModel> void get(Class<T> clazz, Call<T> call, ServiceRepoListener<T> listener);

    <T> void getFromServer(Call<T> call, RetrofitRepoListener<T> listener);

    <T extends RealmModel> RealmResults<T> getFromDatabase(Class<T> clazz);

}
