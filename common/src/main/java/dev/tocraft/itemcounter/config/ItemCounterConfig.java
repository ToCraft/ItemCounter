package dev.tocraft.itemcounter.config;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ItemCounterConfig {
    @Comment("The items to be watched matched to the color of the corresponding text.")
    public Map<String, Integer> items = new HashMap<>() {
        {
            put("minecraft:emerald", new Color(0, 255, 0).getRGB());
            put("minecraft:diamond", new Color(0, 255, 255).getRGB());
            put("minecraft:emerald_ore", new Color(0, 127, 0).getRGB());
            put("minecraft:diamond_ore", new Color(0, 127, 127).getRGB());
        }
    };
    @Comment("The left side equals 0, the right side 100.")
    public float xPos = 90;
    @Comment("The bottom equals 0, the top 100.")
    public float yPos = 95;
}
