package net.pixfumy.rankedmod.mixin.rng.mobspawn;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.class)
public abstract class ModifyAnimalWeightsMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/SpawnEntry;<init>(Ljava/lang/Class;III)V", ordinal = 0), index = 1)
    private int decreaseSheepSpawning(int weight) {
        return 2;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/SpawnEntry;<init>(Ljava/lang/Class;III)V", ordinal = 2), index = 1)
    private int increaseChickenSpawning(int weight) {
        return 20;
    }
}
