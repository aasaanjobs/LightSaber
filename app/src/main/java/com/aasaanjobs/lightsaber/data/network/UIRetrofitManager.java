package com.aasaanjobs.lightsaber.data.network;

import android.content.Context;


import com.aasaanjobs.lightsaber.root.di.customscopes.ScopedActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.net.HttpURLConnection.HTTP_MULT_CHOICE;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by nazmuddinmavliwala on 28/06/16.
 */

@ScopedActivity
public class UIRetrofitManager extends RetrofitManager {

    LightSaberProgressDialog progressDialog;

    public UIRetrofitManager(Retrofit retrofit, Context context) {
        super(retrofit);
        progressDialog = new LightSaberProgressDialog(context);
    }

    @Override
    public <T> void enqueue(Call<T> call, final RetrofitRepoListener<T> listener, boolean showLoading) {
        if(showLoading) {
            progressDialog.show();
        }
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                dismissProgressDialog();
                if(response.code() >= HTTP_OK && response.code() <= HTTP_MULT_CHOICE) {
                    listener.onSuccessResponse(call,response);
                } else {
                    listener.onErrorResponse(call,response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                dismissProgressDialog();
                listener.onFailure(call,t);
            }
        });
    }


    private void dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
