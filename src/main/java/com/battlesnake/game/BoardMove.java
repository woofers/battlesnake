package com.battlesnake.game;

import com.battlesnake.serialization.JsonObject;

/**
 * A response to a move request.
 *
 * @author Tony
 */
public final class BoardMove extends JsonObject {
    private Move move;
    private String taunt;

    public BoardMove(Move move, String taunt) {
        this.move = move;
        this.taunt = taunt;
    }
}
