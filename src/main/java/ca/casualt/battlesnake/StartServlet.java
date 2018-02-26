package ca.casualt.battlesnake;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.casualt.battlesnake.http.response.StartResponse;
import ca.casualt.battlesnake.http.response.StartResponse;

import ca.casualt.battlesnake.game.SmartSnake;

import com.google.gson.Gson;

/**
 * This is the servlet that is hit when triggering the /start endpoint.
 *
 * @author Tony
 * @author Jaxson Van Doorn
 */
@SuppressWarnings("serial")
@WebServlet("/start")
public class StartServlet extends HttpServlet
{

    /**
     * Used for json serialization/deserialization.
     */
    private final Gson gson = new Gson();

    /**
     * This handles the standard post request, converts the json request body
     * into a java object, and creates a response.
     * @param req The http request.
     * @param resp The http response.
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException
    {

        final StartResponse startResponse = SmartSnake.startResponse();
        final String responseBody = gson.toJson(startResponse);
        resp.getWriter().println(responseBody);
    }
}
