package com.battlesnake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

public class Endpoint extends HttpServlet {

    protected static void respond(String body, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.getWriter().println(body);
    }
}
