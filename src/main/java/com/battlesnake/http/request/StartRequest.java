package com.battlesnake.http.request;

import com.google.gson.annotations.SerializedName;

/**
 * A start request.
 * @author Tony
 */
public final class StartRequest
{

    @SerializedName("game_id")
    private String gameId;

    /**
     * Default constructor.
     */
    public StartRequest()
    {
    }

    /**
     * @return the gameId
     */
    public final String getId()
    {
        return gameId;
    }

    /**
     * @param gameId
     * the gameId to set
     */
    public final void setGameId(final String gameId)
    {
        this.gameId = gameId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "StartRequest [gameId=" + gameId + "]";
    }
}
