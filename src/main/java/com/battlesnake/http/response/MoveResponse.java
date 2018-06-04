package com.battlesnake.http.response;

import com.battlesnake.game.data.Move;

/**
 * A response to a move request.
 * @author Tony
 */
public final class MoveResponse
{

    private Move move;
    private String taunt;

    /**
     * Default constructor.
     */
    public MoveResponse()
    {
    }

    /**
     * @return the move
     */
    public final Move getMove()
    {
        return move;
    }

    /**
     * @return the taunt
     */
    public final String getTaunt()
    {
        return taunt;
    }

    /**
     * @param move
     * the move to set
     */
    public final void setMove(final Move move)
    {
        this.move = move;
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
        return "MoveResponse [move=" + move + ", taunt=" + taunt + "]";
    }
}
