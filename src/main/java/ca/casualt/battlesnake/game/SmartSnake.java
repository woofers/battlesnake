package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.http.response.MoveResponse;
import ca.casualt.battlesnake.http.response.StartResponse;
import java.util.List;
import ca.casualt.battlesnake.game.math.Point;
import java.util.Stack;

/**
 * @author Jaxson Van Doorn
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

    private static final String NAME = "SolidSnake";
    private static final String COLOR = "#535F6B";
    private static final String IMAGE = "https://i.imgur.com/FX5ZLYE.png";
    private static final String MOVE_TAUNT = "Kept you waiting, huh?";
    private static final String START_TAUNT = "Metal… Gear?!";

    private static final int HUNGER_ZONE = 50;

    private Snake snake;

    public SmartSnake(Snake snake)
    {
        this.snake = snake;
        setTaunt(MOVE_TAUNT);
    }

    public boolean equals(Object other)
    {
        if (other instanceof SmartSnake) return equals((SmartSnake)other);
        return false;
    }

    public boolean equals(SmartSnake other)
    {
        return id().equals(other.id());
    }

    public String id()
    {
        return snake.getId();
    }

    public void setID(String id)
    {
        snake.setId(id);
    }

    public String name()
    {
        return snake.getName();
    }

    public boolean isFriendly()
    {
        return name().equals(NAME);
    }

    public void setName(String name)
    {
        snake.setName(name);
    }

    public int health()
    {
        return snake.getHealth();
    }

    public void setHealth(int health)
    {
        snake.setHealth(health);
    }

    public String taunt()
    {
        return snake.getTaunt();
    }

    public void setTaunt(String taunt)
    {
        snake.setTaunt(taunt);
    }

    public int length()
    {
        return snake.getLength();
    }

    public void setLength(int length)
    {
        snake.setLength(length);
    }

    public Point head()
    {
        return snake.getHead();
    }

    public List<Point> body()
    {
        return snake.getBody();
    }

    public Move move(Board board)
    {
        switch (mode(board))
        {
            case HUNGRY_STATE:
                return board.findPath(board.findBestFood());
            case ATTACK_STATE:
                return board.findPath(board.findBestAttackPoint());
            case PASSIVE_STATE:
                return board.findPath(board.findSafestPoint());
        }
        return Move.up;
    }

    public Mode mode(Board board)
    {
        if (health() > HUNGER_ZONE)
        {
            return Mode.HUNGRY_STATE;
        }
        else if (length() > board.longestSnakeLength())
        {
            return Mode.ATTACK_STATE;
        }
        return Mode.PASSIVE_STATE;
    }

    public MoveResponse moveResponse(Board board)
    {
        MoveResponse moveResponse = new MoveResponse();
        moveResponse.setMove(move(board));
        moveResponse.setTaunt(taunt());
        return moveResponse;
    }

    public static StartResponse startResponse()
    {
        StartResponse startResponse = new StartResponse();
        startResponse.setColor(COLOR);
        startResponse.setHeadUrl(IMAGE);
        startResponse.setName(NAME);
        startResponse.setTaunt(START_TAUNT);
        return startResponse;
    }
}
