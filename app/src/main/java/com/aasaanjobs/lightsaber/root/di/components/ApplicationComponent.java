package com.aasaanjobs.lightsaber.root.di.components;

import android.app.Application;
import android.content.Context;

import com.aasaanjobs.lightsaber.LightSaberApplication;
import com.aasaanjobs.lightsaber.data.db.utils.RealmService;
import com.aasaanjobs.lightsaber.data.network.RetrofitService;
import com.aasaanjobs.lightsaber.data.preferences.SharedPrefService;
import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.root.di.customidentifiers.WithoutProgressDialog;
import com.aasaanjobs.lightsaber.root.di.modules.ApplicationModule;
import com.aasaanjobs.lightsaber.root.di.modules.DataModule;
import com.aasaanjobs.lightsaber.root.di.modules.UtilityModule;
import com.aasaanjobs.lightsaber.utils.PrimaryKeyFactory;
import com.birbit.android.jobqueue.JobManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class,
        UtilityModule.class
})
public interface ApplicationComponent {

    @ApplicationContext
    Context provideApplcationContext();

    Application provideApplication();

    Gson provideGson();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    @WithoutProgressDialog
    RetrofitService provideRetrofitService();

    RealmService provideRealmService();

    SharedPrefService provideSharedPrefService();

    PrimaryKeyFactory providePrimaryKeyFactory();

    JobManager provideJobManager();


    void injectApplication(LightSaberApplication application);

}
