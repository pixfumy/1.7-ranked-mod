package io.github.marinersfan824.rankedmod.mixin.rng;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.Random;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerSpawnPointMixin {
    @Shadow public abstract ServerWorld getServerWorld();

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private int setSpawnPosX(Random instance, int bound) {
        return new Random(this.getServerWorld().getSeed() ^ 8739208629771456548L).nextInt(bound);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 1))
    private int setSpawnPosY(Random instance, int bound) {
        return new Random(this.getServerWorld().getSeed() ^ 8739208629771456548L).nextInt(bound);
    }
}
