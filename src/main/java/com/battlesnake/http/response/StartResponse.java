package com.battlesnake.http.response;

import com.battlesnake.game.data.HeadType;
import com.battlesnake.game.data.TailType;
import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 * @author Tony
 */
public class StartResponse
{

    private String color;
    private String name;

    @SerializedName("head_url")
    private String headUrl;
    private String taunt;

    @SerializedName("tail_type")
    private TailType tailType;

    @SerializedName("head_type")
    private HeadType headType;

    @SerializedName("secondary_color")
    private String secondaryColor;

    public final String color()
    {
        return color;
    }

    public final HeadType headType()
    {
        return headType;
    }

    public final String headUrl()
    {
        return headUrl;
    }

    public final String name()
    {
        return name;
    }

    public final String secondaryColor()
    {
        return secondaryColor;
    }

    public final void setColor(final String color)
    {
        this.color = color;
    }

    public final void setHeadType(final HeadType headType)
    {
        this.headType = headType;
    }

    public final void setHeadUrl(final String headUrl)
    {
        this.headUrl = headUrl;
    }

    public final void setName(final String name)
    {
        this.name = name;
    }

    public final void setSecondaryColor(final String secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }

    public final void setTailType(final TailType tailType)
    {
        this.tailType = tailType;
    }

    /**
     * @param taunt
     * the taunt to set
     */
    public final void setTaunt(final String taunt)
    {
        this.taunt = taunt;
    }

    public final TailType tailType()
    {
        return tailType;
    }

    public final String taunt()
    {
        return taunt;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "StartResponse [color="
                + color
                + ", name="
                + name
                + ", headUrl="
                + headUrl
                + ", taunt="
                + taunt
                + ", tailType="
                + tailType
                + ", headType="
                + headType
                + ", secondaryColor="
                + secondaryColor
                + "]";
    }
}
