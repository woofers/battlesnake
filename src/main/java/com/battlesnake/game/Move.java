package com.battlesnake.game;

import java.util.HashMap;
import java.util.Map;

import com.battlesnake.game.math.Point;
import com.battlesnake.serialization.JsonObject;

public enum Move {
    DOWN,
    LEFT,
    RIGHT,
    UP;

    private static final class Response extends JsonObject {
        private Move move;

        public Response(Move move) {
            this.move = move;
        }
    }

    public Point translate(Point point) {
        switch (this) {
            case UP:
                return new Point(point.x(), point.y() - 1);
            case DOWN:
                return new Point(point.x(), point.y() + 1);
            case LEFT:
                return new Point(point.x() - 1, point.y());
            default:
                return new Point(point.x() + 1, point.y());
        }
    }

    public static Map<Move, Point> adjacent(Point point) {
        Map<Move, Point> moves = new HashMap<>();
        moves.put(Move.UP, Move.UP.translate(point));
        moves.put(Move.DOWN, Move.DOWN.translate(point));
        moves.put(Move.LEFT, Move.LEFT.translate(point));
        moves.put(Move.RIGHT, Move.RIGHT.translate(point));
        return moves;
    }

    public static Move fallback() {
        return Move.LEFT;
    }

    public String toJson() {
        return new Response(this).toJson();
    }
}
