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
    private static final String IMAGE = "https://i.imgur.com/FX5ZLYE.png";
    private static final String START_TAUNT = "Sleeping late as usual, ...eh Snake?";
    private static final Head HEAD_TYPE = Head.shades;
    private static final Tail TAIL_TYPE = Tail.skinny;

    private String name;
    private String color;
    private String taunt;

    @SerializedName("head_type")
    private Head headType;

    @SerializedName("head_url")
    private String headUrl;

    @SerializedName("secondary_color")
    private String secondaryColor;

    @SerializedName("tail_type")
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
