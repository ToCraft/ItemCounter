package dev.tocraft.itemcounter;

import dev.tocraft.itemcounter.config.ConfigLoader;
import dev.tocraft.itemcounter.config.ItemCounterConfig;

public class ItemCounter {
    public static final String MODID = "itemcounter";
    public static final ItemCounterConfig CONFIG = ConfigLoader.register();

    public void initialize() {
    }
}
