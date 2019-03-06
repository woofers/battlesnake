package com.battlesnake.game.snake;

import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;

public class SnakeConfig extends JsonObject {

    private static final String COLOR = "#8fd628";
    private static final String NAME = "🐍 ‏‏‎ 𝙎𝙐𝙋𝙀𝙍 𝙎𝙇𝙄𝙈𝙀𝙔 ‏‏‎ 🐍";
    private static final Head HEAD_TYPE = Head.EVIL;
    private static final Tail TAIL_TYPE = Tail.BOLT;

    private String name;
    private String color;
    private Head headType;
    private Tail tailType;

    public SnakeConfig() {
        this.name = NAME;
        this.color = COLOR;
        this.tailType = TAIL_TYPE;
        this.headType = HEAD_TYPE;
    }

    public String name() {
        return name;
    }

    public Head head() {
        return headType;
    }

    public Tail tail() {
        return tailType;
    }

    public String color() {
        return color;
    }
}
