package com.battlesnake.game;

import com.battlesnake.game.snake.Snake;
import com.battlesnake.serialization.JsonObject;
import com.google.gson.interceptors.Intercept;
import com.google.gson.interceptors.JsonPostDeserializer;
import com.google.gson.annotations.SerializedName;

@Intercept(postDeserialize = Game.Setup.class)
public final class Game extends JsonObject {

    public static final class Setup implements JsonPostDeserializer<Game> {
        @Override
        public void postDeserialize(Game state) {
            state.board().init(state);
        }
    }

    private static final class Info {
        @SerializedName(value = "id", alternate = { "game_id" })
        private String id;

        String id() {
            return id;
        }
    }

    private Board board;
    private Info game;
    private int turn;
    private Snake you;

    public Board board() {
        return board;
    }

    public Game fromJson(String json) {
        return gson().fromJson(json, getClass());
    }

    public String id() {
        return game.id();
    }

    public int turn() {
        return turn;
    }

    public Snake you() {
        return you;
    }
}
