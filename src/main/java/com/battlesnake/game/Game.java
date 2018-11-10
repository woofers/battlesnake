package com.battlesnake.game;

import com.battlesnake.game.snake.Snake;
import com.battlesnake.serialization.JsonObject;
import com.google.gson.interceptors.Intercept;

@Intercept(postDeserialize = GameSetup.class)
public final class Game extends JsonObject {
    private Board board;
    private GameInfo game;
    private int turn;
    private Snake you;

    public Board board() {
        return board;
    }

    public Game fromJson(String json) {
        return gson().fromJson(json, getClass());
    }

    public int turn() {
        return turn;
    }

    public Snake you() {
        return you;
    }
}
