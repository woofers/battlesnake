package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.http.response.MoveResponse;
import ca.casualt.battlesnake.http.response.StartResponse;

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

    public String taunt()
    {
        return snake.getTaunt();
    }

    public void setTaunt(String taunt)
    {
        snake.setTaunt(taunt);
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

    public StartResponse startResponse()
    {
        StartResponse startResponse = new StartResponse();
        startResponse.setColor(COLOR);
        startResponse.setHeadUrl(IMAGE);
        startResponse.setName(NAME);
        startResponse.setTaunt(START_TAUNT);
        return startResponse;
    }
}
