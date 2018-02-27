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
        switch (type)
        {
            case Move.up:
                point.setY(point.getY() - 1);
            case Move.down:
                point.setY(point.getY() + 1);
            case Move.left:
                point.setX(point.getX() - 1);
            default:
                point.setX(point.getX() + 1);
        }
        return point.
    }
}
