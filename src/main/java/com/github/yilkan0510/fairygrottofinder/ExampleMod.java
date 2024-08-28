package com.github.yilkan0510.fairygrottofinder;

import com.github.yilkan0510.fairygrottofinder.init.CommandFindGrotto;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
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

    public ExampleMod() {
        // Initialize the key binding
        toggleGrottoKey = new KeyBinding("key.togglegrotto", Keyboard.KEY_G, KEY_CATEGORY);
        ClientRegistry.registerKeyBinding(toggleGrottoKey);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandFindGrotto());
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (toggleGrottoKey.isPressed()) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.thePlayer.addChatMessage(new ChatComponentText("Grotto Finder Key Pressed!"));

            // Create an instance of CommandFindGrotto
            CommandFindGrotto commandFindGrotto = new CommandFindGrotto();

            // Call the processCommand method with the player as the sender
            commandFindGrotto.processCommand(mc.thePlayer, new String[]{});
        }
    }

    @Mod.EventHandler
    public void init() {
        // Register event handler for client-side events
        MinecraftForge.EVENT_BUS.register(this);
    }
}
