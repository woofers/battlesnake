package com.battlesnake.game.snake;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import com.battlesnake.game.Board;
import com.battlesnake.game.Move;
import com.battlesnake.game.math.Point;

@Log4j2
@Accessors(fluent = true)
public class Snake {
    private static enum Mode {
        ATTACK_STATE,
        HUNGRY_STATE
    }

    private static final int HUNGER_ZONE = 50;
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;

    @Getter private List<Point> body;
    @Getter private int health;
    @Getter private String id;
    @Getter private String name;

    private Snake() {
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Snake) return equals((Snake) other);
        return false;
    }

    public boolean equals(Snake other) {
        return id().equals(other.id());
    }

    public Point head() {
        return body.get(0);
    }

    public boolean isDead() {
        return health() < MIN_HEALTH;
    }

    public boolean isLongest(Board board) {
        return longerThan(board.longestSnakeLength());
    }

    public boolean justAte() {
        return health() == MAX_HEALTH;
    }

    public int length() {
        return body.size();
    }

    private boolean longerThan(int length) {
        return length() > length;
    }

    public boolean longerThan(Snake snake) {
        return longerThan(snake.length());
    }

    public Mode mode(Board board) {
        if (health() <= HUNGER_ZONE) {
            return Mode.HUNGRY_STATE;
        }
        else if (length() > board.longestSnakeLength()) {
            return Mode.ATTACK_STATE;
        }
        return Mode.HUNGRY_STATE;
    }

    public Move move(Board board) {
        Move move = null;
        Mode mode = mode(board);
        log.info("State {}", mode);
        switch (mode) {
            case HUNGRY_STATE:
                move = board.goToFood(head());
                if (move == null) {
                    move = board.goToAttack(head());
                }
                if (move == null) {
                    move = board.goToTail(head());
                }
                break;
            case ATTACK_STATE:
                move = board.goToAttack(head());
                if (move == null) {
                    move = board.goToFood(head());
                }
                if (move == null) {
                    move = board.goToTail(head());
                }
                break;
        }
        if (move == null) {
            move = board.goToFallback(head());
        }
        board.print();
        log.info("(Region {})", board.toRegionString());
        log.info("Moving {}", move);
        return move;
    }
}
