package com.aasaanjobs.lightsaber.data;


import com.aasaanjobs.lightsaber.data.db.utils.RealmQueryFactory;
import com.aasaanjobs.lightsaber.data.db.utils.RealmRepoListener;
import com.aasaanjobs.lightsaber.data.db.utils.RealmService;
import com.aasaanjobs.lightsaber.data.network.RetrofitRepoListener;
import com.aasaanjobs.lightsaber.data.network.RetrofitService;
import com.aasaanjobs.lightsaber.models.BaseListDO;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmModel;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */

@Singleton
public class DataManager implements DataService {

    private final RetrofitService retrofitService;
    private final RealmService realmService;
    private final RealmQueryFactory factory;

    @Inject
    public DataManager(RetrofitService retrofitService, RealmService realmService, RealmQueryFactory factory) {
        this.retrofitService = retrofitService;
        this.realmService = realmService;
        this.factory = factory;
    }

    @Override
    public <T> T create(Class<T> service) {
        return retrofitService.create(service);
    }


    @Override
    public <P extends RealmModel,T extends BaseListDO<P>> void getList(Class<P> clazz, Call<T> call
            , final ServiceRepoListener<P> listener) {

        final RealmResults<P> realmResults = realmService.read(clazz);
        if(realmResults.isEmpty()) {
            retrofitService.enqueue(call, new RetrofitRepoListener<T>() {

                @Override
                public void onErrorResponse(Call<T> call, Response<T> response) {
                    listener.onError(new Exception());
                }

                @Override
                public void onSuccessResponse(Call<T> call, Response<T> response) {
                    cacheListData(response, listener);
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    listener.onError(t);
                }
            },true);
        } else {
            listener.onSuccess(realmResults);
        }
    }

    @Override
    public <T extends RealmModel> void get(Class<T> clazz, Call<T> call, final ServiceRepoListener<T> listener) {
        RealmResults<T> results = realmService.read(clazz);
        if(results.isEmpty()) {
            retrofitService.enqueue(call, new RetrofitRepoListener<T>() {
                @Override
                public void onErrorResponse(Call<T> call, Response<T> response) {
                    listener.onError(new Exception());
                }

                @Override
                public void onSuccessResponse(Call<T> call, Response<T> response) {
                    cacheData(response,listener);
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    listener.onError(t);
                }
            }, true);
        } else {
            listener.onSuccess(results);
        }
    }


    private <T extends RealmModel> void cacheData(Response<T> response, final ServiceRepoListener<T> listener) {
        realmService.insert(response.body(), new RealmRepoListener<T>() {
            @Override
            public void onSuccess(RealmResults<T> results) {
                listener.onSuccess(results);
            }

            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    private <P extends RealmModel,T extends BaseListDO<P>> void cacheListData(Response<T> response
            , final ServiceRepoListener<P> listener) {
        realmService.insertAll(response.body().getObjects(), new RealmRepoListener<P>() {
            @Override
            public void onSuccess(RealmResults<P> results) {
                listener.onSuccess(results);
            }

            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public <T> void getFromServer(Call<T> call, final RetrofitRepoListener<T> listener) {
        retrofitService.enqueue(call, new RetrofitRepoListener<T>() {

            @Override
            public void onErrorResponse(Call<T> call, Response<T> response) {

            }

            @Override
            public void onSuccessResponse(Call<T> call, Response<T> response) {

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailure(call,t);
            }
        }, true);
    }

    @Override
    public <T extends RealmModel> RealmResults<T> getFromDatabase(Class<T> clazz) {
        return realmService.read(clazz);
    }
}
