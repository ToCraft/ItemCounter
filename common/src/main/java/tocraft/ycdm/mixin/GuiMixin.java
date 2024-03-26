package tocraft.ycdm.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(Gui.class)
@Environment(EnvType.CLIENT)
public class GuiMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    private int screenWidth;

    @Shadow
    private int screenHeight;

    @Inject(method = "render", at = @At("RETURN"))
    private void renderOverlay(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        if (this.minecraft.player != null) {
            int counter = 0;
            for (ItemStack item : this.minecraft.player.getInventory().items) {
                if (item.getItem() == Items.EMERALD) {
                    counter += item.getCount();
                }
            }

            BakedModel itemRenderer = this.minecraft.getItemRenderer().getItemModelShaper().getItemModel(Items.EMERALD);
            if (itemRenderer != null) {
                guiGraphics.blit(0, 0, 0, screenHeight / 5, screenHeight / 5, itemRenderer.getParticleIcon());
                Color color = new Color(0, 255, 0);
                guiGraphics.drawString(this.minecraft.font, "" + counter, screenHeight / 5, screenHeight / 5,color.getRGB(), true);
            }
        }
    }
}
