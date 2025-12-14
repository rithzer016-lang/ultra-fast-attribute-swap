package com.rithzer.ultrafastattributeswap.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to hook into attack sequence to ensure proper timing
 */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public MultiPlayerGameMode gameMode;
    
    @Shadow
    public HitResult hitResult;
    
    @Inject(
        method = "startAttack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;startDestroyBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z",
            shift = At.Shift.BEFORE
        ),
        cancellable = false
    )
    private void onStartAttack(CallbackInfo ci) {
        Minecraft minecraft = (Minecraft) (Object) this;
        
        if (minecraft.player != null && hitResult instanceof EntityHitResult entityHitResult) {
            Entity target = entityHitResult.getEntity();
            
            // Pre-attack hook for attribute swap validation
            if (gameMode != null && gameMode.hasMissTime()) {
                gameMode.missTime = 0; // Ensure no miss penalty
            }
        }
    }
}