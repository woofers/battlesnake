package com.battlesnake.game;

import com.battlesnake.game.snake.Snake;
import com.battlesnake.serialization.JsonObject;
import com.google.gson.interceptors.Intercept;
import com.google.gson.interceptors.JsonPostDeserializer;

@Intercept(postDeserialize = Game.Setup.class)
public final class Game extends JsonObject {

    public static class Setup implements JsonPostDeserializer<Game> {
        @Override
        public void postDeserialize(Game state) {
            state.you().setTurn(state.turn());
            state.board().init(state.you());
        }
    }

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
