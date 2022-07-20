package com.mystic.eating;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "eating";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        System.out.println("You can now eat in creative :D");
    }
}
