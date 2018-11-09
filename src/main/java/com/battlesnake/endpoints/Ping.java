package com.battlesnake.endpoints;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet("/ping")
public class Ping extends HttpServlet
{
    private static final class Response {
        private boolean alive = true;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        response.getWriter().println(new Gson().toJson(new Response()));
    }
}
