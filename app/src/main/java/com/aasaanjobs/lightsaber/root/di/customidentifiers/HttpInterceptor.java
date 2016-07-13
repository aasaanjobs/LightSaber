package com.aasaanjobs.lightsaber.root.di.customidentifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface HttpInterceptor {
}
