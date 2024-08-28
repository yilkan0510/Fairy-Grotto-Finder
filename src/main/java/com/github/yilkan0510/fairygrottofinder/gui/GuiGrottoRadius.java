package com.github.yilkan0510.fairygrottofinder.gui;

import com.github.yilkan0510.fairygrottofinder.config.GrottoRadiusConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;

public class GuiGrottoRadius extends GuiScreen {

    private GuiTextField radiusTextField;
    private final int minRadius = 10;
    private final int maxRadius = 500;

    @Override
    public void initGui() {
        int width = this.width / 2;
        int height = this.height / 2;

        radiusTextField = new GuiTextField(0, fontRendererObj, width - 100, height - 10, 200, 20);
        radiusTextField.setText(String.valueOf(GrottoRadiusConfig.radius)); // Initialize with the current radius
        radiusTextField.setFocused(true);

        this.buttonList.add(new GuiButton(1, width - 100, height + 30, 200, 20, "Set Radius"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            try {
                int newRadius = Integer.parseInt(radiusTextField.getText());

                if (newRadius >= minRadius && newRadius <= maxRadius) {
                    GrottoRadiusConfig.radius = newRadius;
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Search radius set to " + newRadius));
                    mc.displayGuiScreen(null); // Close the GUI
                } else {
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please enter a value between " + minRadius + " and " + maxRadius));
                }
            } catch (NumberFormatException e) {
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid input. Please enter a valid number."));
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        radiusTextField.textboxKeyTyped(typedChar, keyCode);
        if (keyCode == 1) { // ESC key
            mc.displayGuiScreen(null); // Close the GUI
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        radiusTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Set Search Radius", this.width / 2, this.height / 2 - 40, 0xFFFFFF);
        radiusTextField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
