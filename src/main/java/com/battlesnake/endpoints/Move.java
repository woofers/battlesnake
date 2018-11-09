package com.battlesnake.endpoints;

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
import com.google.gson.Gson;

/**
 * This is the servlet that is hit when triggering the /move endpoint.
 * @author Tony
 * @author Jaxson Van Doorn
 */
@SuppressWarnings("serial")
@WebServlet("/move")
public class Move extends HttpServlet
{
    /**
     * This handles the stnadard post request, converts the json request body
     * into a java object, and creates a random response.
     * @param req The http request.
     * @param resp The http response.
     */
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                          throws ServletException, IOException
    {
        String requestBody
            = new BufferedReader(
                new InputStreamReader(req.getInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));

        Board board = new Board(parseToMoveRequest(requestBody));
        resp.getWriter().println(board.moveResponse().toJson());
    }

    public MoveRequest parseToMoveRequest(String requestBody)
    {
        return new Gson().fromJson(requestBody, MoveRequest.class);
    }

}