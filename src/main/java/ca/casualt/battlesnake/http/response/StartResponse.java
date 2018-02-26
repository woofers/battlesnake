package ca.casualt.battlesnake.http.response;

import ca.casualt.battlesnake.game.data.HeadType;
import ca.casualt.battlesnake.game.data.TailType;
import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 *
 * @author Tony
 */
public class StartResponse {

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

    public final void setColor(final String color)
    {
        this.color = color;
    }

    public final String headUrl()
    {
        return headUrl;
    }

    public final void setHeadUrl(final String headUrl) {
        this.headUrl = headUrl;
    }

    public final String name()
    {
        return name;
    }

    public final void setName(final String name)
    {
        this.name = name;
    }

    public final String taunt()
    {
        return taunt;
    }

    /**
     * @param taunt
     *            the taunt to set
     */
    public final void setTaunt(final String taunt) {
        this.taunt = taunt;
    }

    public final TailType tailType() {
        return tailType;
    }

    public final void setTailType(final TailType tailType) {
        this.tailType = tailType;
    }

    public final HeadType headType() {
        return headType;
    }

    public final void setHeadType(final HeadType headType) {
        this.headType = headType;
    }

    public final String secondaryColor()
    {
        return secondaryColor;
    }

    public final void setSecondaryColor(final String secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StartResponse [color=" + color + ", name=" + name + ", headUrl=" + headUrl + ", taunt=" + taunt
                + ", tailType=" + tailType + ", headType=" + headType + ", secondaryColor=" + secondaryColor + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((headType == null) ? 0 : headType.hashCode());
        result = prime * result + ((headUrl == null) ? 0 : headUrl.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((secondaryColor == null) ? 0 : secondaryColor.hashCode());
        result = prime * result + ((tailType == null) ? 0 : tailType.hashCode());
        result = prime * result + ((taunt == null) ? 0 : taunt.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StartResponse other = (StartResponse) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (headType != other.headType)
            return false;
        if (headUrl == null) {
            if (other.headUrl != null)
                return false;
        } else if (!headUrl.equals(other.headUrl))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (secondaryColor == null) {
            if (other.secondaryColor != null)
                return false;
        } else if (!secondaryColor.equals(other.secondaryColor))
            return false;
        if (tailType != other.tailType)
            return false;
        if (taunt == null) {
            if (other.taunt != null)
                return false;
        } else if (!taunt.equals(other.taunt))
            return false;
        return true;
    }

}
