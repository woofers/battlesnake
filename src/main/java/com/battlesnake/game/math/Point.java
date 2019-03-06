package com.battlesnake.game.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A point on the board.
 *
 * @author Tony
 */
public final class Point {

    private int x;
    private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Point)) return false;
        return equals((Point) obj);
    }

    public boolean equals(Point other) {
        if (other == null) return false;
        if (x != other.x || y != other.y) return false;
        return true;
    }

    public Point delta(Point point) {
        return new Point(getX() - point.getX(), getY() - point.getY());
    }

    /**
     * @return the x
     */
    public final int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public final int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getX(), getY());
    }
}
