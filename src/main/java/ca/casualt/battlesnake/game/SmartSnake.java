package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.http.response.MoveResponse;
import ca.casualt.battlesnake.http.response.StartResponse;
import java.util.List;
import ca.casualt.battlesnake.game.math.Point;
import java.util.Stack;

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

    private static final String NAME = "Solid Snake";
    private static final String COLOR = "#f2c55c";
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

    public void setName(String name)
    {
        snake.setName(name);
    }

    public boolean justAte()
    {
        return health() == 100;
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

    public boolean isDead()
    {
        return health() <= 0;
    }

    public boolean isLongest(Board board)
    {
        return length() > board.longestSnakeLength();
    }

    public boolean longerThan(SmartSnake snake)
    {
        return length() > snake.length();
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
