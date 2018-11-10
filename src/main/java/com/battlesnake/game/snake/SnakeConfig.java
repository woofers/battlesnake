package com.battlesnake.game.snake;

import com.battlesnake.serialization.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 *
 * @author Tony
 */
public class SnakeConfig extends JsonObject {

    private String color;
    @SerializedName("head_type")
    private Head headType;

    @SerializedName("head_url")
    private String headUrl;
    private String name;

    @SerializedName("secondary_color")
    private String secondaryColor;

    @SerializedName("tail_type")
    private Tail tailType;

    private String taunt;

    public final String color() {
        return color;
    }

    public final Head headType() {
        return headType;
    }

    public final String headUrl() {
        return headUrl;
    }

    public final String name() {
        return name;
    }

    public final String secondaryColor() {
        return secondaryColor;
    }

    public final void setColor(final String color) {
        this.color = color;
    }

    public final void setHeadType(final Head headType) {
        this.headType = headType;
    }

    public final void setHeadUrl(final String headUrl) {
        this.headUrl = headUrl;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setSecondaryColor(final String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public final void setTailType(final Tail tailType) {
        this.tailType = tailType;
    }

    /**
     * @param taunt the taunt to set
     */
    public final void setTaunt(final String taunt) {
        this.taunt = taunt;
    }

    public final Tail tailType() {
        return tailType;
    }

    public final String taunt() {
        return taunt;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StartResponse [color=" + color + ", name=" + name + ", headUrl="
            + headUrl + ", taunt=" + taunt
            + ", tailType=" + tailType + ", headType=" + headType
            + ", secondaryColor=" + secondaryColor + "]";
    }
}
