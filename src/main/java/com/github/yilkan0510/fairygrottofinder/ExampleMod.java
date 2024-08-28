package com.github.yilkan0510.fairygrottofinder;

import com.github.yilkan0510.fairygrottofinder.config.GrottoRadiusConfig;
import com.github.yilkan0510.fairygrottofinder.gui.GuiGrottoRadius;
import com.github.yilkan0510.fairygrottofinder.init.CommandFindGrotto;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod {

    public static final String MODID = "fairygrottofinder";
    public static final String NAME = "Fairy Grotto Finder";
    public static final String VERSION = "1.0";

    private static final String KEY_CATEGORY = "Fairy Grotto Finder";
    private static KeyBinding toggleGrottoKey;
    private static KeyBinding openGrottoGuiKey;

    private CommandFindGrotto commandFindGrotto;

    public ExampleMod() {
        toggleGrottoKey = new KeyBinding("key.togglegrotto", Keyboard.KEY_G, KEY_CATEGORY);
        openGrottoGuiKey = new KeyBinding("key.opengrottogui", Keyboard.KEY_R, KEY_CATEGORY);
        ClientRegistry.registerKeyBinding(toggleGrottoKey);
        ClientRegistry.registerKeyBinding(openGrottoGuiKey);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        commandFindGrotto = new CommandFindGrotto();
        event.registerServerCommand(commandFindGrotto);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer == null) {
            return;
        }

        if (toggleGrottoKey.isPressed()) {
            mc.thePlayer.addChatMessage(new ChatComponentText("Grotto Finder Key Pressed!"));
            if (commandFindGrotto != null) {
                commandFindGrotto.findGrotto(mc.thePlayer, GrottoRadiusConfig.radius);
            }
        }

        if (openGrottoGuiKey.isPressed()) {
            mc.displayGuiScreen(new GuiGrottoRadius());
        }
    }
}
