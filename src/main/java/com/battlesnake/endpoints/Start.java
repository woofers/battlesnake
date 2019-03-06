package com.battlesnake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.battlesnake.game.snake.SnakeConfig;

@WebServlet("/start")
public class Start extends Endpoint {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        respond(new SnakeConfig().toJson(), response);
    }
}
