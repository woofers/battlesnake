package com.battlesnake.game.math;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@EqualsAndHashCode
public final class Point {

    @Getter private int x;
    @Getter private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point delta(Point point) {
        return new Point(x() - point.x(), y() - point.y());
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x(), y());
    }
}
