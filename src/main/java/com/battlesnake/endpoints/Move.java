package com.battlesnake.endpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.battlesnake.game.Board;
import com.battlesnake.game.Game;

@WebServlet("/move")
public class Move extends Endpoint {

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        String body = new BufferedReader(
            new InputStreamReader(request.getInputStream())).lines()
                .collect(Collectors.joining("\n"));

        Board board = new Game().fromJson(body).board();
        respond(board.move().toJson(), response);
    }
}
