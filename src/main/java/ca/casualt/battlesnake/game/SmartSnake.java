package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.http.response.MoveResponse;
import ca.casualt.battlesnake.http.response.StartResponse;
import java.util.List;
import ca.casualt.battlesnake.game.math.Point;


/**
 * @author Jaxson Van Doorn
 */
public class SmartSnake
{
    private static final String NAME = "SolidSnake";
    private static final String COLOR = "#535F6B";
    private static final String IMAGE = "https://i.imgur.com/FX5ZLYE.png";
    private static final String MOVE_TAUNT = "Kept you waiting, huh?";
    private static final String START_TAUNT = "Metal… Gear?!";

    private Snake snake;

    public SmartSnake(Snake snake)
    {
        this.snake = snake;
        setTaunt(MOVE_TAUNT);
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

    protected void grow()
    {

    }

    public Move move()
    {
        return Move.up;
    }

    public MoveResponse moveResponse()
    {
        MoveResponse moveResponse = new MoveResponse();
        moveResponse.setMove(move());
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
