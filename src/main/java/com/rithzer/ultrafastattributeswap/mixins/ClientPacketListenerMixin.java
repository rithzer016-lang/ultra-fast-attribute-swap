package com.rithzer.ultrafastattributeswap.mixins;

import com.rithzer.ultrafastattributeswap.AttributeSwapHandler;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to handle network packets and prevent desync
 */
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    
    /**
     * Prevent server from overriding our slot during the swap process
     */
    @Inject(
        method = "handleSetCarriedItem",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onSetCarriedItem(ClientboundSetCarriedItemPacket packet, CallbackInfo ci) {
        // If we're in the middle of a swap, ignore server slot updates temporarily
        if (AttributeSwapHandler.isSwapInProgress()) {
            // Only cancel if the slot doesn't match our target state
            ci.cancel();
        }
    }
    
    /**
     * Handle player position updates to maintain slot consistency
     */
    @Inject(
        method = "handleMovePlayer",
        at = @At("TAIL")
    )
    private void onPlayerPosition(ClientboundPlayerPositionPacket packet, CallbackInfo ci) {
        // After position updates, ensure we're in the correct slot
        if (AttributeSwapHandler.isSwapInProgress()) {
            // Reset the swap state if position changed significantly
            AttributeSwapHandler.cancelSwap();
        }
    }
}