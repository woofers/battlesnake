package com.battlesnake.game;

import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;

public final class GameInfo extends JsonObject {

    @SerializedName(value = "id", alternate = { "game_id" })
    private String id;
}
