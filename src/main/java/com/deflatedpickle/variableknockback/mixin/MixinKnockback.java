/* Copyright (c) 2021-2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.variableknockback.mixin;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings({"UnusedMixin", "unused", "SpellCheckingInspection"})
@Mixin(LivingEntity.class)
public abstract class MixinKnockback {
  @Shadow
  public abstract Box getBoundingBox(EntityPose pose);

  @ModifyVariable(method = "takeKnockback", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
  public double onTakeKnockback(double strength) {
    var box = getBoundingBox(((Entity) (Object) this).getPose());
    var volume = box.getXLength() * box.getYLength() * box.getZLength();
    return Math.pow(strength, volume / new Random().nextDouble(1.6, 1.8));
  }
}
