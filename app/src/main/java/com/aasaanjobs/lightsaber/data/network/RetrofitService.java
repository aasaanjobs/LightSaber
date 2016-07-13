package com.aasaanjobs.lightsaber.data.network;


import java.io.IOException;
import java.io.Serializable;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

public interface RetrofitService extends Serializable {

    <T> T create(final Class<T> service);

    <T> Response<T> execute(Call<T> call) throws IOException;

    <T> void enqueue(Call<T> call, RetrofitRepoListener<T> listener, boolean showLoading);

    <T> boolean isExecuted(Call<T> call);

    <T> void cancel(Call<T> call);

    <T> boolean isCanceled(Call<T> call);

    <T> Call<T> clone(Call<T> call);

    <T> Request request(Call<T> call);

}
