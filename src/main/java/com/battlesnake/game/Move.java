package com.battlesnake.game;

import com.battlesnake.game.math.Point;

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
        Point newPoint = new Point(point);
        switch (this) {
            case up:
                newPoint.setY(point.getY() - 1);
                break;
            case down:
                newPoint.setY(point.getY() + 1);
                break;
            case left:
                newPoint.setX(point.getX() - 1);
                break;
            case right:
                newPoint.setX(point.getX() + 1);
                break;
        }
        return newPoint;
    }
}
