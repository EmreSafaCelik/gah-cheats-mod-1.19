package net.gah.hacks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class gahHacksMod implements ModInitializer {
	public static final String MOD_ID = "gahhacksmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final gahHacksMod instance = new gahHacksMod();

	public static boolean autoFishingEnabled = false;
	public AutoFishing autoFishing = new AutoFishing();

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}

	public void tick(MinecraftClient client) {
		LOGGER.info("gahHacksMod tick ran | autoFishing.recastRod: " + gahHacksMod.getInstance().autoFishing.recastRod);
		autoFishing.tick(client);
	}

	public static gahHacksMod getInstance() {
		return instance;
	}
}
