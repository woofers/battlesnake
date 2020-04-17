package com.battlesnake.game.snake;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.battlesnake.serialization.JsonObject;

@Accessors(fluent = true)
public final class SnakeConfig extends JsonObject {

    @Getter private final String name = "🚚🦴🐶 𝗪𝗔𝗟𝗧𝗘𝗥 👑 🧑🏻‍🚒";
    @Getter private final String color = "#DEDBCC";
    @Getter private final Head headType = Head.SAFE;
    @Getter private final Tail tailType = Tail.SMALL_RATTLE;
}
