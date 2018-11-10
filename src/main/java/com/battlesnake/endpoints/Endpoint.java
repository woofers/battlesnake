package com.battlesnake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Endpoint extends HttpServlet {
    protected static void respond(String body, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().println(body);
    }
}
