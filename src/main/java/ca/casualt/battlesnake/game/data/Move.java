package ca.casualt.battlesnake.game.data;

import ca.casualt.battlesnake.game.math.Point;

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
 *
 */
public enum Move {

    /**
     * Move up.
     */
    up,
    /**
     * Move left.
     */
    left,
    /**
     * Move down.
     */
    down,
    /**
     * Move right.
     */
    right;

    public Point translate(Point point)
    {
        Point newPoint = new Point(point);
        switch (this)
        {
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
        if (newPoint.getX() < 0) return null;
        if (newPoint.getY() < 0) return null;
        return newPoint;
    }
}
