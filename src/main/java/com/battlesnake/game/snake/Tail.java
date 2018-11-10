package com.battlesnake.game.snake;

import com.google.gson.annotations.SerializedName;

/**
 * @author Tony
 */
public enum Tail {
    @SerializedName("block-bum")
    blockBum,

    curled,

    @SerializedName("fat-rattle")
    fatRattle,

    freckled,
    pixel,
    regular,

    @SerializedName("round-bum")
    roundBum,

    skinny,

    @SerializedName("small-rattle")
    smallRattle;
}
