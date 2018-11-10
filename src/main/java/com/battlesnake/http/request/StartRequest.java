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
     * @return the gameId
     */
    public final String getId()
    {
        return gameId;
    }
}
