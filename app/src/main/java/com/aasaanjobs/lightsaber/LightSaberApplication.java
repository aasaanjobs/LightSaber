package com.aasaanjobs.lightsaber;

import android.app.Application;


import com.aasaanjobs.lightsaber.root.di.components.ApplicationComponent;
import com.aasaanjobs.lightsaber.root.di.modules.ApplicationModule;
import com.aasaanjobs.lightsaber.root.di.modules.DataModule;
import com.aasaanjobs.lightsaber.root.di.modules.UtilityModule;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;


/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */
public class LightSaberApplication extends Application  {

    private static ApplicationComponent applicationComponent;

    @Inject
    Stetho.Initializer initializer;

    @Override
    public void onCreate() {
        super.onCreate();
//        applicationComponent = DaggerApplicationComponent
//                .builder()
//                .applicationModule(new ApplicationModule(this))
//                .dataModule(new DataModule())
//                .utilityModule(new UtilityModule())
//                .build();
//
//        applicationComponent.injectApplication(this);

        Stetho.initialize(initializer);
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
