package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.http.request.MoveRequest;
import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.game.math.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Jaxson Van Doorn
 */
public class Board
{
    private String id;
    private int turn;

    private SmartSnake you;

    private List<SmartSnake> snakes;

    private int width;
    private int height;

    private List<Point> food;

    private int[][] board;

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int ME = 2;
    private static final int HEADS = 3;
    private static final int FOOD = 4;

    public Board(MoveRequest request)
    {
        this.id = request.getId();
        this.turn = request.getTurn();
        this.you = new SmartSnake(request.getYou());
        this.snakes = new ArrayList<SmartSnake>();
        List<Snake> oldSnakes = request.getSnake();
        for (Snake snake: oldSnakes)
        {
            this.snakes.add(new SmartSnake(snake));
        }
        this.width = request.getWidth();
        this.height = request.getHeight();
        this.food = request.getFood();
    }

    private void toGrid()
    {
        for (Point snack: food)
        {
            board[snack.getX()][snack.getY()] = FOOD;
        }
        for (SmartSnake snake: Snakes)
        {
            List<Point> body = snake.body();
            for (Point bodyPart: body)
            {
                board[bodyPart.getX()][bodyPart.getY()] = WALL;
            }
        }

    }

    private void playTurn(Move move)
    {

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
