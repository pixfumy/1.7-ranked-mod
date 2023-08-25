package net.pixfumy.rankedmod.mixin.worldgen.end;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.FillerBlockFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(FillerBlockFeature.class)
public class StandardizeEndTowerDimensionsMixin {
    @Redirect(method = "method_4028", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private int standardizeTowerHeight(Random instance, int bound, World world, Random alsoInstance, int i, int j, int k) {
        long seed = world.getSeed();
        seed ^= i << 10;
        seed ^= k << 14;
        seed ^= 0xBED | 0xF00D | 0xBABEL;
        return new Random(seed).nextInt(bound);
    }

    @Redirect(method = "method_4028", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 1))
    private int standardizeTowerWidth(Random instance, int bound, World world, Random alsoInstance, int i, int j, int k) {
        long seed = world.getSeed();
        seed ^= i << 20;
        seed ^= k << 14;
        seed ^= 0xDECAF | 0xC0FFEE | 0xDEAL;
        return new Random(seed).nextInt(bound);
    }
}
