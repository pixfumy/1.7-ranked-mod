package net.pixfumy.rankedmod.mixin.rng.playerspawnstandardization;

import net.pixfumy.rankedmod.Main;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "prepareWorlds", at = @At("HEAD"))
    private void shouldSetPlayerSpawn(CallbackInfo ci) {
        Main.shouldSetPlayerSpawn = true;
    }
}
