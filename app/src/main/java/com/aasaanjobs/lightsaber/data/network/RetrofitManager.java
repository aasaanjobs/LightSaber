package com.aasaanjobs.lightsaber.data.network;


import java.io.IOException;

import javax.inject.Singleton;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static javax.net.ssl.HttpsURLConnection.HTTP_MULT_CHOICE;
import static javax.net.ssl.HttpsURLConnection.HTTP_OK;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

@Singleton
public class RetrofitManager implements RetrofitService {

    protected final Retrofit retrofit;

    public RetrofitManager(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    @Override
    public <T> Response<T> execute(Call<T> call) throws IOException {
        return call.execute();
    }

    @Override
    public <T> void enqueue(Call<T> call, final RetrofitRepoListener<T> listener, boolean showLoading) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(response.code() >= HTTP_OK && response.code() <= HTTP_MULT_CHOICE) {
                    listener.onSuccessResponse(call,response);
                } else {
                    listener.onErrorResponse(call,response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailure(call,t);
            }
        });
    }

    @Override
    public <T> boolean isExecuted(Call<T> call) {
        return call.isExecuted();
    }

    @Override
    public <T> void cancel(Call<T> call) {
        call.cancel();
    }

    @Override
    public <T> boolean isCanceled(Call<T> call) {
        return call.isCanceled();
    }

    @Override
    public <T> Call<T> clone(Call<T> call) {
        return call.clone();
    }

    @Override
    public <T> Request request(Call<T> call) {
        return call.request();
    }
}
