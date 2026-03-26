package com.aeroshide.boostedexperience.mixin;

import com.aeroshide.boostedexperience.BoostedExperience;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbEntityMixin extends Entity {
    @Shadow protected abstract void setValue(int value);

    public ExperienceOrbEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "playerTouch")
    private void onPlayerCollision(Player player, CallbackInfo ci) {
        int originalExp = ((ExperienceOrb) (Object) this).getValue();
        double newExp = originalExp * BoostedExperience.xpMultiplier;
        this.setValue((int) newExp);
    }
}
