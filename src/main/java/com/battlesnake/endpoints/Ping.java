package com.battlesnake.endpoints;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.battlesnake.http.serialization.JsonObject;

@WebServlet("/ping")
public class Ping extends Endpoint {

    private static final class Response extends JsonObject {
        private boolean alive = true;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        respond(new Gson().toJson(new Response()), response);
    }
}
