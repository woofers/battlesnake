package com.battlesnake.game.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A point on the board.
 * @author Tony
 */
public final class Point
{

    private int x;
    private int y;

    /**
     * Default constructor.
     */
    public Point()
    {
        this(0, 0);
    }

    /**
     * Convenience constructor.
     * @param xIn
     * @param yIn
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a point from a list.<br>
     * (commonly seen within the standard json format).
     * @param toConvert
     */
    @Deprecated
    private Point(final List<Integer> toConvert)
    {
        this.x = toConvert.get(0);
        this.y = toConvert.get(1);
    }

    /**
     * Copy an existing point.
     * @param toCopy
     */
    public Point(final Point toCopy)
    {
        this.x = toCopy.x;
        this.y = toCopy.y;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof Point)) return false;
        return equals((Point) obj);
    }

    public boolean equals(Point other)
    {
        if (other == null) return false;
        if (x != other.x || y != other.y) return false;
        return true;
    }

    /**
     * @return the x
     */
    public final int getX()
    {
        return x;
    }

    /**
     * @return the y
     */
    public final int getY()
    {
        return y;
    }

    /**
     * @param x
     * the x to set
     */
    public final void setX(int x)
    {
        this.x = x;
    }

    /**
     * @param y
     * the y to set
     */
    public final void setY(int y)
    {
        this.y = y;
    }

    /**
     * @return the point as a list again.
     */
    @Deprecated
    private List<Integer> toList()
    {
        return new ArrayList<>(Arrays.asList(x, y));
    }

    @Override
    public String toString()
    {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}
