package com.battlesnake.game.snake;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.battlesnake.serialization.JsonObject;

@Accessors(fluent = true)
public final class SnakeConfig extends JsonObject {

    @Getter private final String name = "SLIME LANGUAGE";
    @Getter private final String color = "#8FD628";
    @Getter private final Head headType = Head.SAND_WORM
    @Getter private final Tail tailType = Tail.SHARP;
}
