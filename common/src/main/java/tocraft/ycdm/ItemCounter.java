package tocraft.ycdm;

import net.minecraft.resources.ResourceLocation;

public class ItemCounter {

    public static final String MODID = "itemcounter";

    public void initialize() {
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MODID, name);
    }
}
