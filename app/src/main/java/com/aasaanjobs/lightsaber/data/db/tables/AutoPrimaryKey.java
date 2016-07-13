package com.aasaanjobs.lightsaber.data.db.tables;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */

@Documented
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoPrimaryKey {
}
