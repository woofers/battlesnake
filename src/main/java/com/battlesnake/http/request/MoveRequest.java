package com.battlesnake.http.request;

import java.util.List;
import java.util.stream.Collectors;
import com.battlesnake.game.Snake;
import com.battlesnake.game.data.BoardSpec;
import com.battlesnake.game.math.Point;

/**
 * A move request.
 * @author Tony
 */
public final class MoveRequest implements BoardSpec
{

    private String id;
    private int turn;

    private Snake you;

    private List<Snake> snakes;

    private int width;
    private int height;

    private List<Point> food;

    /**
     * Default constructor.
     */
    public MoveRequest()
    {
    }

    /**
     * Copy constructor. <br>
     * (deep copy).
     * @param toCopy
     */
    public MoveRequest(final MoveRequest toCopy)
    {
        this.id = toCopy.id;
        this.turn = toCopy.turn;
        this.you = new Snake(toCopy.you);

        this.width = toCopy.width;
        this.height = toCopy.height;

        this.snakes = toCopy.snakes.stream().map(snake ->
        {
            return new Snake(snake);
        }).collect(Collectors.toList());
        this.food = toCopy.food.stream().map(aFood ->
        {
            return new Point(aFood);
        }).collect(Collectors.toList());
    }

    /**
     * @return the food
     */
    public final List<Point> getFood()
    {
        return food;
    }

    /**
     * @return the height
     */
    @Override
    public final int getHeight()
    {
        return height;
    }

    /**
     * @return the gameId
     */
    @Override
    public final String getId()
    {
        return id;
    }

    /**
     * @return the snakes
     */
    public final List<Snake> getSnakes()
    {
        return snakes;
    }

    /**
     * @return the turn
     */
    public final int getTurn()
    {
        return turn;
    }

    /**
     * @return the width
     */
    @Override
    public final int getWidth()
    {
        return width;
    }

    /**
     * @return your snake.
     */
    public final Snake getYou()
    {
        return you;
    }

    /**
     * @param food
     * the food to set
     */
    public final void setFood(final List<Point> food)
    {
        this.food = food;
    }

    /**
     * @param height
     * the height to set
     */
    public final void setHeight(final int height)
    {
        this.height = height;
    }

    /**
     * @param gameId
     * the gameId to set
     */
    public final void setId(final String gameId)
    {
        this.id = gameId;
    }

    /**
     * @param snakes
     * the snakes to set
     */
    public final void setSnakes(final List<Snake> snakes)
    {
        this.snakes = snakes;
    }

    /**
     * @param turn
     * the turn to set
     */
    public final void setTurn(final int turn)
    {
        this.turn = turn;
    }

    /**
     * @param width
     * the width to set
     */
    public final void setWidth(final int width)
    {
        this.width = width;
    }

    /**
     * @param you
     * the you to set
     */
    public final void setYou(final Snake you)
    {
        this.you = you;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "MoveRequest [gameId="
                + id
                + ", width="
                + width
                + ", height="
                + height
                + ", you="
                + you
                + ", turn="
                + turn
                + ", snakes="
                + snakes
                + ", food="
                + food
                + "]";
    }
}
