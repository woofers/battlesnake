package com.battlesnake.game;

/**
 * @author Jaxson Van Doorn
 */
public class Taunt
{
    private static final int MAX_LENGTH = 32;

    private String taunt;
    private int turn;

    public Taunt(String taunt, int turn)
    {
        this.turn = turn;
        this.taunt = taunt;
    }

    @Override
    public String toString()
    {
        String cropped = "";
        int start = turn % taunt.length();
        cropped = taunt.substring(start);
        if (cropped.length() > MAX_LENGTH)
            cropped = cropped.substring(0, MAX_LENGTH);
        return cropped;
    }
}
