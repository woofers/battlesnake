package com.battlesnake.game.math;

import com.battlesnake.game.Move;

public class MovePoint {
    private Move initialMove;
    private Move move;
    private Point point;
    private int length;

    public MovePoint(Move move, Point point, Move initialMove) {
        this.move = move;
        this.point = point;
        this.initialMove = initialMove;
        setLength(0);
    }

    public boolean equals(MovePoint other) {
        return point().equals(other.point());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof MovePoint) return equals((MovePoint) other);
        return false;
    }

    public Move initialMove() {
        return initialMove;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int length() {
        return length;
    }

    public Move move() {
        return move;
    }

    public Point point() {
        return point;
    }

    @Override
    public String toString() {
        String value = "";
        if (move() != null) {
            value += move().toString() + " ";
        }
        if (point() != null) {
            value += "X: " + point().x() + " Y: " + point.y() + " ";
        }
        if (initialMove() != null) {
            value += initialMove().toString() + " ";
        }
        return value;
    }
}
