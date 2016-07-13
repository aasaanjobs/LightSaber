package com.aasaanjobs.lightsaber.root.di.modules;

import android.content.Context;

import com.aasaanjobs.lightsaber.data.db.utils.RealmService;
import com.aasaanjobs.lightsaber.data.preferences.SharedPrefService;
import com.aasaanjobs.lightsaber.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.lightsaber.utils.FileUtil;
import com.aasaanjobs.lightsaber.utils.PrimaryKeyFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 22/05/16.
 */

@Singleton
@Module
public class UtilityModule {

    @Singleton
    @Provides
    public PrimaryKeyFactory providePrimaryKeyFactory(RealmService realmService) {
        PrimaryKeyFactory primaryKeyFactory =  PrimaryKeyFactory
                .getInstance();
        primaryKeyFactory.initialize(realmService
                .getRealm());
        return primaryKeyFactory;
    }

    @Singleton
    @Provides
    public FileUtil provideFileUtil(@ApplicationContext Context context
            , SharedPrefService sharedPrefService) {
        return new FileUtil(context,sharedPrefService);
    }
}
