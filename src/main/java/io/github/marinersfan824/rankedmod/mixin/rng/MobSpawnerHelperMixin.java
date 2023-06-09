package io.github.marinersfan824.rankedmod.mixin.rng;

import net.minecraft.entity.MobSpawnerHelper;
import net.minecraft.entity.SpawnEntry;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.Random;

@Mixin(MobSpawnerHelper.class)
public class MobSpawnerHelperMixin {
    @Redirect(method = "method_3800", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;getMaxSpawnLimit()F"))
    private static float increaseAnimalSpawns(Biome instance) {
        return 0.1f;
    }

    /**
     * @reason using the world's random returns the same seed for subsequent calls because the seed gets reset by
     * temple and village structure shouldStartAt() methods
     */
    @Redirect(method = "method_3800", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/Weighting;getRandom(Ljava/util/Random;Ljava/util/Collection;)Lnet/minecraft/util/collection/Weight;"))
    private static Weight stopAnimalDuplication(Random random, Collection pool) {
        return Weighting.getRandom(new Random(), pool);
    }
}
