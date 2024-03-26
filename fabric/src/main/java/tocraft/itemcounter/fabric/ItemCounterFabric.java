package tocraft.itemcounter.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import tocraft.ycdm.ItemCounter;
import tocraft.ycdm.ItemCounterClient;

public class ItemCounterFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        new ItemCounter().initialize();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            new ItemCounterClient().initialize();
        }
    }
}
