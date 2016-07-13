package com.aasaanjobs.lightsaber.data.network;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */
public interface RetrofitRepoListener<T>  {
    void onErrorResponse(Call<T> call, Response<T> response);

    void onSuccessResponse(Call<T> call, Response<T> response);

    void onFailure(Call<T> call, Throwable t);
}
