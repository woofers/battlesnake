package com.battlesnake.game.snake;

import lombok.experimental.Accessors;
import lombok.Getter;

import com.battlesnake.serialization.JsonObject;

@Accessors(fluent = true)
public class SnakeConfig extends JsonObject {

    @Getter private String name = "🐍 ‏‏‎ 𝙎𝙐𝙋𝙀𝙍 𝙎𝙇𝙄𝙈𝙀𝙔 ‏‏‎ 🐍";
    @Getter private String color = "#8FD628";
    @Getter private Head headType = Head.EVIL;
    @Getter private Tail tailType = Tail.BOLT;
}
