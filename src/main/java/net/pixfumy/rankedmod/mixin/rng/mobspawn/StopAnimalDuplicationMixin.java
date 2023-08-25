package net.pixfumy.rankedmod.mixin.rng.mobspawn;

import net.pixfumy.rankedmod.mixinterface.ILevelProperties;
import net.minecraft.entity.MobSpawnerHelper;
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
public class StopAnimalDuplicationMixin {
    /**
     * Increase this to a higher value between 0.0f and 1.0f to increase the total animal mob-cap.
     */
    @Redirect(method = "spawnMobs", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;getMaxSpawnLimit()F"))
    private static float increaseAnimalSpawns(Biome instance) {
        return 0.1f;
    }

    /**
     * @reason using the world's random returns the same seed for subsequent calls because the seed gets reset by
     * temple and village structure shouldStartAt() methods. Instead, choose the animal type based on the coords.
     */
    @Redirect(method = "spawnMobs", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/Weighting;getRandom(Ljava/util/Random;Ljava/util/Collection;)Lnet/minecraft/util/collection/Weight;"))
    private static Weight stopAnimalDuplication(Random random, Collection pool, World world, Biome biome, int i, int j, int k, int l, Random random2) {
        // no need to do world.getServer().getWorld() nonsense, this is always the overworld, and it's always of type ServerWorld
        long seed = world.getSeed();
        seed ^= i << 10;
        seed ^= j << 20;
        seed ^= -1798895202606485916L; // infume wr seed, of course
        return Weighting.getRandom(new Random(seed), pool);
    }
}
