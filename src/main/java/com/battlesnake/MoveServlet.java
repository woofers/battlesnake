package com.battlesnake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.battlesnake.game.Board;
import com.battlesnake.game.SmartSnake;
import com.battlesnake.http.request.MoveRequest;
import com.battlesnake.http.response.MoveResponse;
import com.battlesnake.http.serialization.CleanJson;
import com.google.gson.Gson;

/**
 * This is the servlet that is hit when triggering the /move endpoint.
 * @author Tony
 * @author Jaxson Van Doorn
 */
@SuppressWarnings("serial")
@WebServlet("/move")
public class MoveServlet extends HttpServlet
{

    /**
     * Used for json serialization/deserialization.
     */
    private final Gson gson = new Gson();

    /**
     * This handles the stnadard post request, converts the json request body
     * into a java object, and creates a random response.
     * @param req The http request.
     * @param resp The http response.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp)
            throws ServletException, IOException
    {
        final String requestBody = new BufferedReader(
                new InputStreamReader(req.getInputStream())).lines()
                        .collect(Collectors.joining("\n"));

        final MoveRequest moveRequest = parseToMoveRequest(requestBody);
        Board board = new Board(moveRequest);
        SmartSnake snake = board.mySnake();

        final MoveResponse moveResponse = snake.moveResponse(board);

        final String responseBody = gson.toJson(moveResponse);
        resp.getWriter().println(responseBody);
    }

    public MoveRequest parseToMoveRequest(final String requestBody)
    {
        return gson
                .fromJson(CleanJson.cleanJson(requestBody), MoveRequest.class);
    }

}
