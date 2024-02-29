package com.aeroshide.boostedexperience.mixin;

import net.minecraft.entity.ExperienceOrbEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ExperienceOrbEntity.class)
public interface ExperienceOrbAccessor {
    // Getter for the experience field
    @Accessor("amount")
    int getExperience();

    // Setter for the experience field
    @Accessor("amount")
    void setExperience(int value);
}

