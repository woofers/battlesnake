package com.battlesnake.endpoints;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet("/end")
public class End extends HttpServlet
{
    private static final class Response {
        private boolean over = true;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        response.getWriter().println(new Gson().toJson(new Response()));
    }
}
