package com.battlesnake.game;

import com.battlesnake.game.math.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * The four valid moves.
 * <ul>
 * <li>{@link #up}</li>
 * <li>{@link #left}</li>
 * <li>{@link #down}</li>
 * <li>{@link #right}</li>
 * </ul>
 *
 * @author Tony
 */
public enum Move {
    down,
    left,
    right,
    up;

    public Point translate(Point point) {
        switch (this) {
            case up:
                return new Point(point.x(), point.y() - 1);
            case down:
                return new Point(point.x(), point.y() + 1);
            case left:
                return new Point(point.x() - 1, point.y());
            default:
                return new Point(point.x() + 1, point.y());
        }
    }

    public static Map<Move, Point> adjacent(Point point) {
        Map<Move, Point> moves = new HashMap<>();
        moves.put(Move.up, Move.up.translate(point));
        moves.put(Move.down, Move.down.translate(point));
        moves.put(Move.left, Move.left.translate(point));
        moves.put(Move.right, Move.right.translate(point));
        return moves;
    }
}
