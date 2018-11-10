package com.battlesnake.game;

import com.google.gson.interceptors.JsonPostDeserializer;

public class GameSetup implements JsonPostDeserializer<Game> {

    @Override
    public void postDeserialize(Game state) {
        state.you().setTurn(state.turn());
        state.board().init(state.you());
    }
}
