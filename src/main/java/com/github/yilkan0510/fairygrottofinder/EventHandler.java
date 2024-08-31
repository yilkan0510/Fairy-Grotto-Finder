package com.github.yilkan0510.fairygrottofinder;

import com.github.yilkan0510.fairygrottofinder.modules.WorldScanner;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler {

    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new WorldScanner());
    }
}
