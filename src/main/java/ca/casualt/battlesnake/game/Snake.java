package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.game.math.Point;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A snake on the board.
 *
 * @author Tony
 *
 */
public class Snake {

    private String id;
    private String name;
    private int health;
    private int length;
    private List<Point> body;
    private String taunt;

    /**
     * Default constructor.
     */
    public Snake() {
    }

    /**
     * Copy Constructor.<br>
     * (deep copy).
     *
     * @param toCopy
     */
    public Snake(final Snake toCopy) {
        this.id = toCopy.id;
        this.name = toCopy.name;
        this.health = toCopy.health;
        this.length = toCopy.length;
        this.body = toCopy.body.stream().map(pair -> {
            return new Point(pair);
        }).collect(Collectors.toList());
        this.taunt = toCopy.taunt;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the taunt
     */
    public final String getTaunt() {
        return taunt;
    }

    /**
     * @param taunt
     *            the taunt to set
     */
    public final void setTaunt(final String taunt) {
        this.taunt = taunt;
    }

    /**
     * @return the health
     */
    public final int getHealth() {
        return health;
    }

    /**
     * @param health
     *            the health to set
     */
    public final void setHealth(final int health) {
        this.health = health;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(final int length) {
        this.length = length;
    }

    /**
     *
     * @return the head is the first point in the list.
     */
    public final Point getHead() {
        return getBody().get(0);
    }

    /**
     * @return the body
     */
    public final List<Point> getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public final void setBody(final List<Point> body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Snake [id=" + id + ", name=" + name + ", health=" + health + ", length=" + length + ", body=" + body
                + ", taunt=" + taunt + "]";
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
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + health;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + length;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Snake other = (Snake) obj;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (health != other.health)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (length != other.length)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (taunt == null) {
            if (other.taunt != null)
                return false;
        } else if (!taunt.equals(other.taunt))
            return false;
        return true;
    }

}
