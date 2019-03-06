package com.battlesnake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.battlesnake.serialization.JsonObject;

@WebServlet("/end")
public class End extends Endpoint {

    private static final class Response extends JsonObject {
        private boolean over = true;
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        respond(new Response().toJson(), response);
    }
}
