package com.battlesnake.game.snake;

import com.google.gson.annotations.SerializedName;

/**
 * @author Tony
 */
public enum Head {
    bendr,
    dead,
    fang,
    pixel,
    regular,
    safe,

    @SerializedName("sand-worm")
    sandWorm,

    shades,
    smile,
    tongue;
}
