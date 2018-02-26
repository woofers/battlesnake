package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.http.request.MoveRequest;
import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.game.math.Point;
import java.util.List;

/**
 * @author Jaxson Van Doorn
 */
public class Board
{
    private String id;
    private int turn;

    private SmartSnake you;

    private List<Snake> snakes;

    private int width;
    private int height;

    private List<Point> food;

    public Board(MoveRequest request)
    {
        this.id = request.getId();
        this.turn = request.getTurn();
        this.you = new SmartSnake(request.getYou());
        this.snakes = request.getSnakes();
        this.width = request.getWidth();
        this.height = request.getHeight();
        this.food = request.getFood();
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public SmartSnake mySnake()
    {
        return you;
    }
}
