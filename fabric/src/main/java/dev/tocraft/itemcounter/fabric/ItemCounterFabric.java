package dev.tocraft.itemcounter.fabric;

import dev.tocraft.itemcounter.gui.CounterRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;

public class ItemCounterFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            HudRenderCallback.EVENT.register(CounterRenderer::renderOverlay);
        }
    }
}
