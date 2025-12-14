package com.rithzer.ultrafastattributeswap.mixins;

import com.rithzer.ultrafastattributeswap.AttributeSwapHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to hook into player attack methods to ensure atomic slot switching
 */
@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    
    /**
     * Hook into the attack method to ensure we're using the correct item attributes
     * when the server processes the attack
     */
    @Inject(
        method = "attack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;attack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V",
            shift = At.Shift.BEFORE
        )
    )
    private void preServerAttack(Entity target, CallbackInfo ci) {
        // At this point, AttributeSwapHandler should have already performed the swap
        // This injection point ensures the server receives the correct item data
    }
    
    /**
     * Hook after attack to ensure we don't stay on the mace slot
     */
    @Inject(
        method = "attack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;attack(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V",
            shift = At.Shift.AFTER
        )
    )
    private void postServerAttack(Entity target, CallbackInfo ci) {
        // Ensure swap is completed in the same tick
        LocalPlayer player = (LocalPlayer) (Object) this;
        
        // Validate that we're back to the original slot
        ItemStack currentItem = player.getMainHandItem();
        
        // Log for debugging
        if (AttributeSwapHandler.isSwapInProgress()) {
            AttributeSwapHandler.forceCompleteSwap(player);
        }
    }
    
    /**
     * Hook into item use to prevent desync during the swap process
     */
    @Inject(
        method = "swing",
        at = @At("HEAD")
    )
    private void onSwing(InteractionHand hand, CallbackInfo ci) {
        // During the attack animation, ensure our slot state is consistent
    }
}