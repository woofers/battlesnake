package com.battlesnake.game.math;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.battlesnake.game.Move;

@ToString
@Accessors(fluent = true)
public class MovePoint {
    @Getter private Move initialMove;
    @Getter private Move move;
    @Getter private Point point;
    @Getter @Setter private int length;

    public MovePoint(Move move, Point point, Move initialMove) {
        this.move = move;
        this.point = point;
        this.initialMove = initialMove;
        length(0);
    }

    public boolean equals(MovePoint other) {
        return point().equals(other.point());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof MovePoint) return equals((MovePoint) other);
        return false;
    }
}
