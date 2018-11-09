package com.battlesnake.endpoints;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.battlesnake.game.SmartSnake;
import com.battlesnake.http.response.StartResponse;
import com.google.gson.Gson;

/**
 * This is the servlet that is hit when triggering the /start endpoint.
 * @author Tony
 * @author Jaxson Van Doorn
 */
@SuppressWarnings("serial")
@WebServlet("/start")
public class Start extends HttpServlet
{
    /**
     * This handles the standard post request, converts the json request body
     * into a java object, and creates a response.
     * @param req The http request.
     * @param resp The http response.
     */
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
                          throws ServletException, IOException
    {
        resp.getWriter().println(SmartSnake.startResponse().toJson());
    }
}