package com.battlesnake.game;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.battlesnake.game.snake.Snake;
import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.interceptors.Intercept;
import com.google.gson.interceptors.JsonPostDeserializer;

@Intercept(postDeserialize = Game.Setup.class)
@Accessors(fluent = true)
public final class Game extends JsonObject {

    public static final class Setup implements JsonPostDeserializer<Game> {
        @Override
        public void postDeserialize(Game state) {
            state.board().init(state);
        }
    }

    private static final class Info {
        @SerializedName(value = "id", alternate = { "game_id" })
        @Getter private String id;
    }

    @Getter private Board board;
    @Getter private Info game;
    @Getter private int turn;
    @Getter private Snake you;

    public Game fromJson(String json) {
        return gson().fromJson(json, getClass());
    }

    public String id() {
        return game.id();
    }
}
