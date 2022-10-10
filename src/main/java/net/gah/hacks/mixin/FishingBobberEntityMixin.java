package net.gah.hacks.mixin;

import net.gah.hacks.gahHacksMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin {

    @Shadow private boolean caughtFish;

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if(caughtFish && gahHacksMod.autoFishingEnabled) {
            gahHacksMod.LOGGER.info("caughtFish && gahHacksMod.autoFishingEnabled");
            gahHacksMod.getInstance().autoFishing.setRecastRod(20);
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            gahHacksMod.getInstance().tick(client);
            gahHacksMod.LOGGER.info("used rod and set recast to 20 and invoked gahHacksMod.getInstance().tick(client)");
        }
    }
}
