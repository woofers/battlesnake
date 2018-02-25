package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.data.Move;

public class SmartSnake
{
    private Snake snake;

    public SmartSnake(Snake snake)
    {
        this.snake = snake;
    }

    public Move move()
    {
        return Move.up;
    }
}
