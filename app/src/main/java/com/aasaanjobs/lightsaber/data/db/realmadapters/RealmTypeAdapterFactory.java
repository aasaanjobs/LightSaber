package com.aasaanjobs.lightsaber.data.db.realmadapters;

import com.aasaanjobs.lightsaber.data.db.tables.RealmBoolean;
import com.aasaanjobs.lightsaber.data.db.tables.RealmDouble;
import com.aasaanjobs.lightsaber.data.db.tables.RealmFloat;
import com.aasaanjobs.lightsaber.data.db.tables.RealmInteger;
import com.aasaanjobs.lightsaber.data.db.tables.RealmLong;
import com.aasaanjobs.lightsaber.data.db.tables.RealmString;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        if(!isAssignableFrom(type,RealmList.class)) {
            return null;

        } else if(isAssignableFrom(type, RealmList.class
                , RealmString.class)) {
            return (TypeAdapter<T>) new RealmStringListAdapter();

        } else if(isAssignableFrom(type, RealmList.class
                , RealmBoolean.class)) {
            return (TypeAdapter<T>) new RealmBooleanListAdapter();

        } else if(isAssignableFrom(type, RealmList.class
                , RealmInteger.class)) {
            return (TypeAdapter<T>) new RealmIntListAdapter();

        } else if(isAssignableFrom(type,RealmList.class
                , RealmDouble.class)) {
            return (TypeAdapter<T>) new RealmDoubleListAdapter();

        } else if(isAssignableFrom(type, RealmList.class
                , RealmFloat.class)) {
            return (TypeAdapter<T>) new RealmFloatListAdapter();

        } else if(isAssignableFrom(type, RealmList.class
                , RealmLong.class)) {
            return (TypeAdapter<T>) new RealmLongListAdapter();

        } else {
            return null;
        }
    }

     <T,P> boolean isAssignableFrom(TypeToken<T> typeToken, Class<P> clazz) {
        return clazz.isAssignableFrom(typeToken.getRawType());
    }

     @SuppressWarnings("unchecked")
     <T,P,Q> boolean isAssignableFrom(TypeToken<T> typeToken, Class<P> clazz
            , Class<Q> genericParameter) {
        boolean isAssignable =  clazz.isAssignableFrom(typeToken.getRawType());
        if(isAssignable) {
            Type type = typeToken.getType();
            if(type instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType)type;
                try {
                    Type[] args = pt.getActualTypeArguments();
                    return ((Class)args[0]).isAssignableFrom(genericParameter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return false;
        }
        return false;
    }
}
