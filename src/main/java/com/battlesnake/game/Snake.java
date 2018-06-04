package com.battlesnake.game;

import java.util.List;
import java.util.stream.Collectors;
import com.battlesnake.game.math.Point;

/**
 * A snake on the board.
 * @author Tony
 */
public class Snake
{

    private String id;
    private String name;
    private int health;
    private int length;
    private List<Point> body;
    private String taunt;

    /**
     * Default constructor.
     */
    public Snake()
    {
    }

    /**
     * Copy Constructor.<br>
     * (deep copy).
     * @param toCopy
     */
    public Snake(final Snake toCopy)
    {
        this.id = toCopy.id;
        this.name = toCopy.name;
        this.health = toCopy.health;
        this.length = toCopy.length;
        this.body = toCopy.body.stream().map(pair ->
        {
            return new Point(pair);
        }).collect(Collectors.toList());
        this.taunt = toCopy.taunt;
    }

    /**
     * @return the body
     */
    public final List<Point> getBody()
    {
        return body;
    }

    /**
     * @return the head is the first point in the list.
     */
    public final Point getHead()
    {
        return getBody().get(0);
    }

    /**
     * @return the health
     */
    public final int getHealth()
    {
        return health;
    }

    /**
     * @return the id
     */
    public final String getId()
    {
        return id;
    }

    /**
     * @return the length
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @return the name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @return the taunt
     */
    public final String getTaunt()
    {
        return taunt;
    }

    /**
     * @param body
     * the body to set
     */
    public final void setBody(final List<Point> body)
    {
        this.body = body;
    }

    /**
     * @param health
     * the health to set
     */
    public final void setHealth(final int health)
    {
        this.health = health;
    }

    /**
     * @param id
     * the id to set
     */
    public final void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @param length
     * the length to set
     */
    public void setLength(final int length)
    {
        this.length = length;
    }

    /**
     * @param name
     * the name to set
     */
    public final void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @param taunt
     * the taunt to set
     */
    public final void setTaunt(final String taunt)
    {
        this.taunt = taunt;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Snake [id="
                + id
                + ", name="
                + name
                + ", health="
                + health
                + ", length="
                + length
                + ", body="
                + body
                + ", taunt="
                + taunt
                + "]";
    }
}
