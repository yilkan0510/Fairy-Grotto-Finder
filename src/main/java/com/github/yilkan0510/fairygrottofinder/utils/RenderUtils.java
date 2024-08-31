package com.github.yilkan0510.fairygrottofinder.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtils {

    public static void renderFairyGrotto(BlockPos pos) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.addChatMessage(
                new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Fairy Grotto found at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ())
        );

        GL11.glPushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GL11.glColor4f(1.0F, 0.0F, 1.0F, 0.5F);
        // Add rendering code for the ESP box here
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GL11.glPopMatrix();
    }
}
