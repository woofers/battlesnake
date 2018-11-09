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
    private static final class Game {
        private String id;

        public String id() {
            return id;
        }
    }

    private static final class Board {
        private List<Snake> snakes;
        private List<Point> food;
        private int width;
        private int height;

        private List<Snake> snakes() {
            return snakes;
        }

        private List<Point> food() {
            return food;
        }

        private int width() {
            return width;
        }

        private int height() {
            return height;
        }
    }


    private Snake you;
    private Game game;
    private Board board;
    private int turn;

    /**
     * @return the food
     */
    public final List<Point> getFood()
    {
        return board.food();
    }

    /**
     * @return the height
     */
    @Override
    public final int getHeight()
    {
        return board.height();
    }

    /**
     * @return the gameId
     */
    @Override
    public final String getId()
    {
        return game.id();
    }

    /**
     * @return the snakes
     */
    public final List<Snake> getSnakes()
    {
        return board.snakes();
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
        return board.width();
    }

    /**
     * @return your snake.
     */
    public final Snake getYou()
    {
        return you;
    }
}
