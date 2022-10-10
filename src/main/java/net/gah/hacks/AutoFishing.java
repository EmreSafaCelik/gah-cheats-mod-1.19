package net.gah.hacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class AutoFishing {

    public int recastRod = -1;

    public void tick(MinecraftClient client) {
        gahHacksMod.LOGGER.info("AutoFishing tick ran");
        if(recastRod>0) {
            gahHacksMod.LOGGER.info("AutoFishing tick ran and lowered recastRod by one");
            recastRod--;
            gahHacksMod.getInstance().tick(client);
        }
        if(recastRod==0 && gahHacksMod.autoFishingEnabled) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recastRod=-1;
        }
    }

    public void setRecastRod(int countdown) {
        gahHacksMod.LOGGER.info("setRecastRod ran");
        recastRod = countdown;
    }
}
