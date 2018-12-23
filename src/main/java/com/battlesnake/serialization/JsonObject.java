package com.battlesnake.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.interceptors.InterceptorFactory;
import com.google.gson.FieldNamingPolicy;

public abstract class JsonObject {

    protected Gson gson() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new InterceptorFactory())
            .create();
    }

    public String toJson() {
        return gson().toJson(this);
    }
}
