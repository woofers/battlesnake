package ca.casualt.battlesnake.game.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A point on the board.
 *
 * @author Tony
 *
 */
public final class Point {

	private int x;
	private int y;

	/**
	 * Default constructor.
	 */
	public Point() {
	}

	/**
	 * Convenience constructor.
	 *
	 * @param xIn
	 * @param yIn
	 */
	public Point(final int xIn, final int yIn) {
		this.x = xIn;
		this.y = yIn;
	}

	/**
	 * Copy an existing point.
	 *
	 * @param toCopy
	 */
	public Point(final Point toCopy) {
		this.x = toCopy.x;
		this.y = toCopy.y;
	}

	/**
	 * Construct a point from a list.<br>
	 * (commonly seen within the standard json format).
	 *
	 * @param toConvert
	 */
	@Deprecated
	private Point(final List<Integer> toConvert) {
		this.x = toConvert.get(0);
		this.y = toConvert.get(1);
	}

	/**
	 *
	 * @return the point as a list again.
	 */
	@Deprecated
	private List<Integer> toList() {
		return new ArrayList<>(Arrays.asList(x, y));
	}

	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public final void setX(final int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public final void setY(final int y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
