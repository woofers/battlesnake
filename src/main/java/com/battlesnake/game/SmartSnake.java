package com.battlesnake.game;

import java.util.List;
import com.battlesnake.game.data.Move;
import com.battlesnake.game.math.Point;
import com.battlesnake.http.response.MoveResponse;
import com.battlesnake.http.response.StartResponse;

/**
 * @author Ben Austin
 * @author Jaxson Van Doorn
 * @author Zak White
 */
public class SmartSnake
{
    public static enum Mode
    {
        HUNGRY_STATE,
        ATTACK_STATE,
        PASSIVE_STATE,
        NO_BEST_MOVE_STATE,
        BAIT_STATE
    }

    private static final String NAME = "Liquid Snake";
    private static final String COLOR = "#f2c55c";
    private static final String IMAGE = "https://i.imgur.com/FX5ZLYE.png";
    private static final String START_TAUNT
            = "Sleeping late as usual, ...eh Snake?";
    private static final String HOLE_TAUNT
            = "So, the Snake's finally come out of his hole! Are you ready now, my brother!";
    private static final String DUST_TAUNT = "Snake, I'll crush you into dust!";
    private static final String GEAR_TAUNT
            = "Good. Then no one can stop Metal Gear now.";

    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;
    private static final int HUNGER_ZONE = 50;

    public static StartResponse startResponse()
    {
        StartResponse startResponse = new StartResponse();
        startResponse.setColor(COLOR);
        startResponse.setHeadUrl(IMAGE);
        startResponse.setName(NAME);
        startResponse.setTaunt(START_TAUNT);
        return startResponse;
    }

    private Snake snake;

    private int turn;

    public SmartSnake(Snake snake, int turn)
    {
        this.snake = snake;
        this.turn = turn;
        setTaunt(HOLE_TAUNT);
    }

    public List<Point> body()
    {
        return snake.getBody();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof SmartSnake) return equals((SmartSnake) other);
        return false;
    }

    public boolean equals(SmartSnake other)
    {
        return id().equals(other.id());
    }

    public Point head()
    {
        return snake.getHead();
    }

    public int health()
    {
        return snake.getHealth();
    }

    public String id()
    {
        return snake.getId();
    }

    public boolean isDead()
    {
        return health() < MIN_HEALTH;
    }

    public boolean isLongest(Board board)
    {
        return longerThan(board.longestSnakeLength());
    }

    public boolean justAte()
    {
        return health() == MAX_HEALTH;
    }

    public int length()
    {
        return snake.getLength();
    }

    private boolean longerThan(int length)
    {
        return length() > length;
    }

    public boolean longerThan(SmartSnake snake)
    {
        return longerThan(snake.length());
    }

    public Mode mode(Board board)
    {
        if (health() <= HUNGER_ZONE)
        {
            return Mode.HUNGRY_STATE;
        }
        else if (length() > board.longestSnakeLength())
        {
            return Mode.ATTACK_STATE;
        }
        return Mode.HUNGRY_STATE;
    }

    public Move move(Board board)
    {
        Move move = null;
        switch (mode(board))
        {
            case HUNGRY_STATE:
                move = board.goToFood(head());
                if (move == null) move = board.goToAttack(head());
                if (move == null) move = board.goToTail(head());
                break;
            case PASSIVE_STATE:
                move = board.goToTail(head());
                if (move == null) move = board.goToFood(head());
                if (move == null) move = board.goToAttack(head());
                break;
            case ATTACK_STATE:
                move = board.goToAttack(head());
                if (move == null) move = board.goToFood(head());
                if (move == null) move = board.goToTail(head());
                break;
        }
        if (move == null) move = Move.left;
        return move;
    }

    public MoveResponse moveResponse(Board board)
    {
        MoveResponse moveResponse = new MoveResponse();
        moveResponse.setMove(move(board));
        moveResponse.setTaunt(new Taunt(taunt(), turn).toString());
        return moveResponse;
    }

    public String name()
    {
        return snake.getName();
    }

    public void setHealth(int health)
    {
        snake.setHealth(health);
    }

    public void setID(String id)
    {
        snake.setId(id);
    }

    public void setLength(int length)
    {
        snake.setLength(length);
    }

    public void setName(String name)
    {
        snake.setName(name);
    }

    public void setTaunt(String taunt)
    {
        snake.setTaunt(taunt);
    }

    public String taunt()
    {
        return snake.getTaunt();
    }
}
