package dev.tocraft.itemcounter.forge;

import dev.tocraft.itemcounter.ItemCounter;
import dev.tocraft.itemcounter.gui.CounterRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@SuppressWarnings("unused")
@Mod(ItemCounter.MODID)
public class ItemCounterForge {

    public ItemCounterForge() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.addListener(ItemCounterForge::renderGuiPostEvent);
        }

        new ItemCounter().initialize();
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void renderGuiPostEvent(RenderGuiEvent.Post event) {
        CounterRenderer.renderOverlay(event.getGuiGraphics());
    }
}
