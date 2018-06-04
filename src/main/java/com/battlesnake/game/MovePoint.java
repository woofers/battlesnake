package com.battlesnake.game;

import java.util.List;
import java.util.stream.Collectors;
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

    public Point point()
    {
        return point;
    }

    public Move move()
    {
        return move;
    }

    public Move initialMove()
    {
        return initialMove;
    }

    public boolean equals(Object other)
    {
        if (other instanceof MovePoint) return equals((MovePoint)other);
        return false;
    }

    public boolean equals(MovePoint other)
    {
        return point().equals(other.point());
    }

    public String toString()
    {
        String value = "";
        if (move() != null) value += move().toString() + " ";
        if (point() != null) value += "X: " + point().getX() + " Y: " + point.getY() + " ";
        if (initialMove() != null) value += initialMove().toString() + " ";
        return value;
    }
}
