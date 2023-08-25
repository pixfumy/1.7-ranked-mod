package net.pixfumy.rankedmod.mixin.worldgen.end;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.EndBiomeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(EndBiomeDecorator.class)
public class StandardizeEndTowerLocationsMixin extends BiomeDecorator {

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private int standardizeWhetherTowerGenerates(Random instance, int bound) {
        long seed = world.getSeed();
        seed ^= this.field_4689 << 16;
        seed ^= this.field_4690 << 32;
        seed ^= 0xD1CE;
        return new Random(seed).nextInt(bound);
    }

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 1))
    private int standardizeTowerXOffset(Random instance, int bound) {
        long seed = world.getSeed();
        seed ^= this.field_4689 << 10;
        seed ^= this.field_4690 << 14;
        seed ^= 0x5CAFF01D;
        return new Random(seed).nextInt(bound);
    }

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 2))
    private int standardizeTowerZOffset(Random instance, int bound) {
        long seed = world.getSeed();
        seed ^= this.field_4689 << 20;
        seed ^= this.field_4690 << 8;
        seed ^= 0xDEADDEAL;
        return new Random(seed).nextInt(bound);
    }
}
