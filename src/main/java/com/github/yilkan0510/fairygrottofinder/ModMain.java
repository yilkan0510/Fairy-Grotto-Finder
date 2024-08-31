package com.github.yilkan0510.fairygrottofinder;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModMain.MODID, name = ModMain.NAME, version = ModMain.VERSION)
public class ModMain {
    public static final String MODID = "fairygrottofinder";
    public static final String NAME = "Fairy Grotto Finder";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Pre-Initialization code here
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialization code here
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        // Register commands or event listeners here
    }
}
