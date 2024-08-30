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
        }
    };
    @Comment("The left side equals 0, the right one 100.")
    public double xOffset = 10;
    @Comment("The top equals 0, the bottom 100.")
    public double yOffset = 10;
    @Comment("The size of the widget per item in percent.")
    public double size = 10;
}
