package dev.tocraft.itemcounter.gui;

import dev.tocraft.itemcounter.ItemCounter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;


//#if MC>=1201
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
//#else
//$$ import com.mojang.blaze3d.vertex.PoseStack;
//$$ import net.minecraft.client.gui.GuiComponent;
//$$ import net.minecraft.world.item.ItemStack;
//#endif

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
                Item searchItem = itemRegistry().get(new ResourceLocation(entry.getKey()));
                // search in player inventory
                int counter = minecraft.player.getInventory().countItem(searchItem);

                BakedModel itemRenderer = minecraft.getItemRenderer().getItemModelShaper().getItemModel(searchItem);
                if (itemRenderer != null && counter > 0) {
                    i++;
                    renderItem(graphics, searchItem, (int) (guiWidth() * ItemCounter.CONFIG.xPos / 100), (int) (guiHeight() * ItemCounter.CONFIG.yPos / 100 - 16 * i));
                    drawString(graphics, Component.literal("" + counter), (int) (guiWidth() * ItemCounter.CONFIG.xPos / 100 + 20), (int) (guiHeight() * ItemCounter.CONFIG.yPos / 100 - 16 * i) + 4, entry.getValue());
                }
            }
        }
    }

    private static int guiWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    private static int guiHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    //#if MC>=1201
    private static void renderItem(GuiGraphics graphics, Item item, int x, int y) {
        graphics.renderItem(new ItemStack(item), x, y);
    }
    private static Registry<Item> itemRegistry() {
        return BuiltInRegistries.ITEM;
    }
        private static void drawString(GuiGraphics graphics, MutableComponent text, int x, int y, int color) {
           graphics.drawString(Minecraft.getInstance().font, text, x, y, color, false);
       }
    //#else
    //$$ private static void renderItem(PoseStack graphics, Item item, int x, int y) {
    //$$     Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(new ItemStack(item), x, y);
    //$$ }
    //$$ private static Registry<Item> itemRegistry() {
    //$$     return Registry.ITEM;
    //$$ }
    //$$ private static void drawString(PoseStack graphics, MutableComponent text, int x, int y, int color) {
    //$$     GuiComponent.drawString(graphics, Minecraft.getInstance().font, text, x, y, color);
    //$$ }
    //#endif
}
