package net.gah.hacks;

import net.fabricmc.api.ClientModInitializer;
import net.gah.hacks.event.KeyInputHandler;

public class gahHacksModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
