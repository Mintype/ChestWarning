package org.mintype.chestwarningmod;

import net.fabricmc.api.ModInitializer;

public class Chestwarningmod implements ModInitializer {

    public final String MOD_ID = "ChestWarning";

    @Override
    public void onInitialize() {
        System.out.println("Initializing " + MOD_ID);

        ChestOpenCallback.register();

        System.out.println("Initialized " + MOD_ID);
    }
}
