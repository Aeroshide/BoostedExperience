package com.aeroshide.boostedexperience.mixin;

import com.aeroshide.boostedexperience.BoostedExperience;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin extends Entity {
    @Shadow protected abstract void setValue(int value);

    public ExperienceOrbEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "onPlayerCollision")
    private void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        int originalExp = ((ExperienceOrbEntity) (Object) this).getValue();
        double newExp = originalExp * BoostedExperience.xpMultiplier;
        this.setValue((int) newExp);
    }
}
