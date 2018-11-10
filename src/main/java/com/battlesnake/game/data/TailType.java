package com.battlesnake.game.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author Tony
 */
public enum TailType
{
    @SerializedName("block-bum")
    blockBum,

    @SerializedName("fat-rattle")
    fatRattle,

    @SerializedName("round-bum")
    roundBum,

    @SerializedName("small-rattle")
    smallRattle,

    curled,
    freckled,
    pixel,
    regular,
    skinny;
}
