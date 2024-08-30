package dev.tocraft.itemcounter.gui;

import dev.tocraft.itemcounter.ItemCounter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class CounterRenderer {
    //#if MC>=1201
    public static void renderOverlay(GuiGraphics graphics) {
    //#else
    //$$ public static void renderOverlay(PoseStack graphics) {
    //#endif
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.level != null) {
            int i = 0;
            for (Map.Entry<String, Integer> entry : ItemCounter.CONFIG.items.entrySet()) {
                Item searchItem = BuiltInRegistries.ITEM.get(new ResourceLocation(entry.getKey()));
                // search in player inventory
                int counter = minecraft.player.getInventory().countItem(searchItem);

                BakedModel itemRenderer = minecraft.getItemRenderer().getItemModelShaper().getItemModel(searchItem);
                if (itemRenderer != null && counter > 0) {
                    i++;
                    graphics.blit((int) (graphics.guiWidth() * ItemCounter.CONFIG.xOffset / 100), (int) (graphics.guiHeight() - graphics.guiHeight() * ItemCounter.CONFIG.yOffset / 100 - graphics.guiHeight() * ItemCounter.CONFIG.size / 100 * i), 0, graphics.guiHeight() / 10, graphics.guiHeight() / 10, itemRenderer.getParticleIcon());
                    graphics.drawString(Minecraft.getInstance().font, Component.literal("" + counter), (int) (graphics.guiWidth() * ItemCounter.CONFIG.xOffset / 100 + graphics.guiHeight() * ItemCounter.CONFIG.size / 100), (int) (graphics.guiHeight() - graphics.guiHeight() * ItemCounter.CONFIG.yOffset / 100 - graphics.guiHeight() * ItemCounter.CONFIG.size / 100 * i), entry.getValue(), false);
                }
            }
        }
    }
}
