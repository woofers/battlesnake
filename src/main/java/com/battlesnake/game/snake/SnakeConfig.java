package com.battlesnake.game.snake;

import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 *
 * @author Tony
 */
public class SnakeConfig extends JsonObject {

    private static final String COLOR = "#f2c55c";
    private static final String NAME = "Liquid Snake";
    private static final String IMAGE = "https://i.imgur.com/TGNk0gd.png";
    private static final String START_TAUNT = "Sleeping late as usual, ...eh Snake?";
    private static final Head HEAD_TYPE = Head.SHADES;
    private static final Tail TAIL_TYPE = Tail.SKINNY;

    private String name;
    private String color;
    private String taunt;
    private Head headType;
    private String headUrl;
    private String secondaryColor;
    private Tail tailType;

    public SnakeConfig() {
        this.name = NAME;
        this.color = COLOR;
        this.headUrl = IMAGE;
        this.taunt = START_TAUNT;
        this.tailType = TAIL_TYPE;
        this.headType = HEAD_TYPE;
    }
}
