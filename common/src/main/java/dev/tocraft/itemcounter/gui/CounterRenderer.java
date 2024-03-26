package dev.tocraft.itemcounter.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class CounterRenderer {
    public static void renderOverlay(GuiGraphics graphics, float tickDelta) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.level != null) {
            Item searchItem = Items.EMERALD;

            int counter = 0;
            for (ItemStack item : minecraft.player.getInventory().items) {
                if (item.getItem() == searchItem) {
                    counter += item.getCount();
                }
            }

            BakedModel itemRenderer = minecraft.getItemRenderer().getItemModelShaper().getItemModel(Items.EMERALD);
            if (itemRenderer != null) {
                graphics.blit(graphics.guiWidth() / 12, graphics.guiHeight() - graphics.guiHeight() / 12 - graphics.guiHeight() / 5, 0, graphics.guiHeight() / 5, graphics.guiHeight() / 5, itemRenderer.getParticleIcon());
                Color color = new Color(0, 255, 0);
                graphics.drawString(minecraft.font, "" + counter, graphics.guiWidth() / 12 + graphics.guiHeight() / 5, graphics.guiHeight() - graphics.guiHeight() / 12, color.getRGB(), false);
            }
        }
    }
}
