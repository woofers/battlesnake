package com.battlesnake.game.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return new Point(x() - point.x(), y() - point.y());
    }

    public int x() {
        return x;
    }


    public int y() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x(), y());
    }
}
