package com.aeroshide.boostedexperience.mixin;

import com.aeroshide.boostedexperience.BoostedExperience;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin extends Entity {
    // Constructor
    public ExperienceOrbEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // Inject into the onPlayerCollision method
    @Inject(at = @At("HEAD"), method = "onPlayerCollision")
    private void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        // Get the original amount of experience
        int originalExp = ((ExperienceOrbEntity) (Object) this).getExperienceAmount();
        // Multiply it by a factor (e.g. 2 for double the experience)
        int newExp = originalExp * BoostedExperience.xpMultiplier;
        // Set the new amount of experience
        ((ExperienceOrbEntityAccessor) this).setExperience(newExp);
    }
}
