package com.battlesnake.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.interceptors.InterceptorFactory;

public abstract class JsonObject {

    protected Gson gson() {
        return new GsonBuilder()
            .registerTypeAdapterFactory(new InterceptorFactory())
            .create();
    }

    public String toJson() {
        return gson().toJson(this);
    }
}
