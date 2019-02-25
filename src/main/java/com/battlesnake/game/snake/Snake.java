package com.battlesnake.game.snake;

import java.util.List;

import com.battlesnake.game.Board;
import com.battlesnake.game.BoardMove;
import com.battlesnake.game.Move;
import com.battlesnake.game.Taunt;
import com.battlesnake.game.math.Point;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author Ben Austin
 * @author Jaxson Van Doorn
 * @author Zak White
 */
public class Snake {
    public static enum Mode {
        ATTACK_STATE,
        HUNGRY_STATE
    }

    private static Logger log = LogManager.getLogger();

    private static final String SLAT_TAUNT = "slat slat slat slat slat";

    private static final int HUNGER_ZONE = 50;
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;

    private List<Point> body;
    private int health;
    private String id;
    private int length;
    private String name;

    private transient String taunt;

    private Snake() {
        this.taunt = SLAT_TAUNT;
    }

    public List<Point> body() {
        return body;
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

    public int health() {
        return health;
    }

    public String id() {
        return id;
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
        return length;
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
        log.info("Moving {}", move);
        return move;
    }

    public BoardMove gameMove(Board board) {
        String taunt = new Taunt(taunt(), board.turn()).toString();
        BoardMove move = new BoardMove(move(board), taunt);
        return move;
    }

    public String name() {
        return name;
    }

    public String taunt() {
        return taunt;
    }
}
