package tocraft.itemcounter.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tocraft.ycdm.ItemCounter;
import tocraft.ycdm.ItemCounterClient;

@Mod(ItemCounter.MODID)
public class ItemCounterForge {

    public ItemCounterForge() {
        new ItemCounter().initialize();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            new ItemCounterClient().initialize();
        }
    }
}
