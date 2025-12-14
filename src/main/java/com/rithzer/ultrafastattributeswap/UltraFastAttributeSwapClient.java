package com.rithzer.ultrafastattributeswap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltraFastAttributeSwapClient implements ClientModInitializer {
    public static final String MOD_ID = "ultra-fast-attribute-swap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Ultra-Fast Attribute Swap mod initialized!");
        
        // Register attack input tracking
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                AttributeSwapHandler.tick(client.player);
            }
        });
    }
}