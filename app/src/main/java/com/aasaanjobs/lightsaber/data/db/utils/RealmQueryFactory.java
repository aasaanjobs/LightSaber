package com.aasaanjobs.lightsaber.data.db.utils;


import com.aasaanjobs.lightsaber.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import okhttp3.HttpUrl;

/**
 * Created by nazmuddinmavliwala on 03/06/16.
 */

@Singleton
public class RealmQueryFactory {

    private static final String FILTER = "filter";
    private static final String QUERY = "query";
    private static final String AND = "and";
    private static final String OR = "or";
    private final RealmService realmService;
    private final Realm realm;

    public RealmQueryFactory(RealmService realmService) {
        this.realmService = realmService;
        realm = realmService.getRealm();
    }

    public <T extends RealmModel> RealmQuery<T> constructRealmQuery(Class<T> clazz, HttpUrl url) {

        //check the number of entries of the given class, if 0. don't process further
        int size = realm.where(clazz).findAll().size();
        if(size > 0) {
            //get elastic filter object
            ElasticQueryObject queryObject = getQueryObject(url);

            //init query
            RealmResults<T> query = realm.where(clazz).findAll();

            //check for path
            if(!StringUtil.isEmpty(queryObject.getPath())) {
                //assuming path always refer to primary key
                query = query.where()
                        .equalTo(getPrimaryKeyForClass(clazz), queryObject.getPath()).findAll();
                if(query.size() == 0) {
                    return query.where();
                }
            }

            //check for query
            if(queryObject.getQueryMap().size() > 0) {
                HashMap<String, String> map = queryObject.getQueryMap();
                for(HashMap.Entry<String, String> entrySet : map.entrySet()) {
                    query.where().equalTo(entrySet.getKey(),entrySet.getValue());
                }
            }

            //check for filters
            ElasticFilter filter = queryObject.getFilter();
            if(filter != null) {

                //process AND
                List<FilterModel> and = filter.getAND();
                for (FilterModel model : and) {
                    ElasticOperator operator = model.getElasticOperator();
                }

                //process OR
                List<FilterModel> or = filter.getOR();
                for (FilterModel model : or) {
                    ElasticOperator operator = model.getElasticOperator();
                }
            }
        } else {
            return realm.where(clazz);
        }

        return realm.where(clazz);
    }

    private <T extends RealmModel> String getPrimaryKeyForClass(Class<T> clazz) {
        //return primary key for class clazz
        return null;
    }

    private ElasticQueryObject getQueryObject(HttpUrl url) {
        ElasticQueryObject queryObject = new ElasticQueryObject();
        if(hasPath(url)) {
            queryObject.setPath(getPath(url));
        }
        if(hasQuery(url)) {
            queryObject.setQueryMap(getQueryMap(url));
        }
        if(hasFilter(url)) {
            queryObject.setFilter(getFilter(url));
        }
        return queryObject;
    }

    public ElasticFilter getFilter(HttpUrl url) {
        ElasticFilter filter = new ElasticFilter();
        try {
            JSONObject jsonObject = new JSONObject(url.queryParameter(FILTER));
            JSONArray jsonArray = jsonObject.names();
            for(int i = 0 ; i < jsonArray.length() ; i ++ ) {
                String key = jsonArray.get(i).toString();
                List<FilterModel> filterModels = getFilterModels(jsonObject,key);
                //AND
                if(AND.equals(key)) {
                    filter.setAND(filterModels);
                }
                //OR
                else if(OR.equals(key)) {
                    filter.setOR(filterModels);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filter;
    }

    private List<FilterModel> getFilterModels(JSONObject jsonObject, String key) {
        try {
            List<FilterModel> models = new ArrayList<>();
            JSONObject parentObject = new JSONObject(jsonObject.get(key).toString());
            JSONArray parentArray = parentObject.names();

            for (int j = 0 ; j < parentArray.length() ; j ++) {

                String childKey = parentArray.get(j).toString();
                FilterModel filterModel = new FilterModel();
                filterModel.setKey(childKey);

                JSONObject childObject = new JSONObject(parentObject.get(childKey).toString());
                JSONArray childArray = childObject.names();
                String operatorKey = childArray.get(0).toString();
                ElasticOperator operator = ElasticOperator.valueOf(operatorKey);
                switch (operator) {
                    case gte:case lte:case gt:case lt:case neq:case eq:
                        filterModel.setValue(childObject.getString(operatorKey));
                        break;
                    case between:
                        JSONArray betweenArray = childObject.getJSONArray(operatorKey);
                        filterModel.setLower(childObject.getLong(betweenArray.get(0).toString()));
                        filterModel.setUpper(childObject.getLong(betweenArray.get(1).toString()));
                        break;
                    case inq:case nin:
                        break;
                    case exists:
                        filterModel.setExists(childObject.getBoolean(operatorKey));
                    case missing:
                        filterModel.setExists(childObject.getBoolean(operatorKey));
                        break;
                    case distance:
                        break;
                }
                filterModel.setElasticOperator(operator);
                models.add(filterModel);
            }
            return models;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasFilter(HttpUrl url) {
        Set<String> queries = url.queryParameterNames();
        for(String query : queries) {
            if(FILTER.equals(query)) {
                return true;
            }
        }
        return false;
    }

    private HashMap<String, String> getQueryMap(HttpUrl url) {
        HashMap<String,String> hashMap = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(url.queryParameter(QUERY));
            JSONArray jsonArray = jsonObject.names();
            for (int i = 0 ; i < jsonArray.length() ; i ++) {
                String key = jsonArray.get(i).toString();
                String value = jsonObject.get(key).toString();
                hashMap.put(key,value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private boolean hasQuery(HttpUrl url) {
        Set<String> queries = url.queryParameterNames();
        for(String query : queries) {
            if(QUERY.equals(query)) {
                return true;
            }
        }
        return false;
    }

    private String getPath(HttpUrl url) {
        return null;
    }

    private boolean hasPath(HttpUrl url) {
        return false;
    }

}
