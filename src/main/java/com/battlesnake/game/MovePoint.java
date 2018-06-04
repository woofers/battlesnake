package com.battlesnake.game;

import com.battlesnake.game.data.Move;
import com.battlesnake.game.math.Point;

public class MovePoint
{
    private Point point;
    private Move move;
    private Move initialMove;

    public MovePoint(Move move, Point point, Move initialMove)
    {
        this.move = move;
        this.point = point;
        this.initialMove = initialMove;
    }

    public boolean equals(MovePoint other)
    {
        return point().equals(other.point());
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof MovePoint) return equals((MovePoint) other);
        return false;
    }

    public Move initialMove()
    {
        return initialMove;
    }

    public Move move()
    {
        return move;
    }

    public Point point()
    {
        return point;
    }

    @Override
    public String toString()
    {
        String value = "";
        if (move() != null) value += move().toString() + " ";
        if (point() != null)
            value += "X: " + point().getX() + " Y: " + point.getY() + " ";
        if (initialMove() != null) value += initialMove().toString() + " ";
        return value;
    }
}
