package com.battlesnake.http.serialization;

import com.google.gson.Gson;

public abstract class JsonObject
{
    public String toJson()
    {
        return new Gson().toJson(this);
    }
}
