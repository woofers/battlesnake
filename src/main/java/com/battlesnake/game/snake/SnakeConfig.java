package com.battlesnake.game.snake;

import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 *
 * @author Tony
 */
public class SnakeConfig extends JsonObject {

    private static final String COLOR = "#8fd628";
    private static final String NAME = "🤮 𝙎𝙐𝙋𝙀𝙍 𝙎𝙇𝙄𝙈𝙀𝙔 🤮";
    private static final String IMAGE = "https://i.imgur.com/7bOdXtX.gif";
    private static final String START_TAUNT = "𝐬𝐥𝐚𝐭𝐭!";
    private static final Head HEAD_TYPE = Head.EVIL;
    private static final Tail TAIL_TYPE = Tail.BOLT;

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

    public String name() {
        return name;
    }

    public String profile() {
        return headUrl;
    }

    public Head head() {
        return headType;
    }

    public Tail tail() {
        return tailType;
    }

    public String taunt() {
        return taunt;
    }

    public String color() {
        return color;
    }
}
