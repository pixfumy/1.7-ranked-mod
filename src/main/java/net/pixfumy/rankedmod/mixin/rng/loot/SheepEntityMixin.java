package net.pixfumy.rankedmod.mixin.rng.loot;

import net.pixfumy.rankedmod.RNGStreamGenerator;
import net.pixfumy.rankedmod.mixinterface.ILevelProperties;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import java.util.Random;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {
    private RNGStreamGenerator rngStreamGenerator;

    public SheepEntityMixin(World world) {
        super(world);
    }

    @ModifyVariable(method = "method_2537", at = @At(value = "STORE"), ordinal = 0)
    public int dropStandardizedLoot(int original) {
        World overWorld = ((ServerWorld)this.world).getServer().getWorld();
        rngStreamGenerator = ((ILevelProperties)overWorld.getLevelProperties()).getRngStreamGenerator();
        return new Random(rngStreamGenerator.getAndUpdateSeed("woolSeed")).nextInt(3) + 1;
    }
}
