package ca.casualt.battlesnake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.casualt.battlesnake.http.request.MoveRequest;
import ca.casualt.battlesnake.http.response.MoveResponse;
import ca.casualt.battlesnake.http.serialization.TwentyEighteenJsonHelper;
import com.google.gson.Gson;

import ca.casualt.battlesnake.game.move.Mover;
import ca.casualt.battlesnake.game.Board;
import ca.casualt.battlesnake.game.SmartSnake;
import ca.casualt.battlesnake.game.move.RandomMover;

/**
 * This is the servlet that is hit when triggering the /move endpoint.
 *
 * @author Tony
 * @author Jaxson Van Doorn
 */
@SuppressWarnings("serial")
@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    /**
     * Used for json serialization/deserialization.
     */
    private final Gson gson = new Gson();

    /**
     * For returning a response.
     */
    private final Mover mover = new RandomMover();

    /**
     * This handles the stnadard post request, converts the json request body
     * into a java object, and creates a random response.
     * @param req The http request.
     * @param resp The http response.
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
                          throws ServletException, IOException
    {
        final String requestBody = new BufferedReader(
            new InputStreamReader(req.getInputStream())).lines()
                .collect(Collectors.joining("\n"));
        //System.out.println("Current Game Statey: [" + requestBody + "]");

        final MoveRequest moveRequest = parseToMoveRequest(requestBody);
        Board board = new Board(moveRequest);
        SmartSnake snake = board.mySnake();

        final MoveResponse moveResponse = snake.moveResponse(board);
        //System.out.println("Next Move " + moveResponse.getMove());

        final String responseBody = gson.toJson(moveResponse);
        resp.getWriter().println(responseBody);
    }

    public MoveRequest parseToMoveRequest(final String requestBody)
    {
        return gson.fromJson(TwentyEighteenJsonHelper.cleanJson(requestBody), MoveRequest.class);
    }

}
