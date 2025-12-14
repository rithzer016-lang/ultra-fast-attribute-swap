package com.rithzer.ultrafastattributeswap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * Handles the ultra-fast attribute swapping logic
 * Tracks attack inputs and performs slot switching within the same tick
 */
public class AttributeSwapHandler {
    private static boolean wasAttacking = false;
    private static int originalSlot = -1;
    private static boolean swapInProgress = false;
    private static int swapCooldown = 0;
    
    // Constants
    private static final int SWAP_COOLDOWN_TICKS = 2; // Small cooldown to prevent double-swaps
    
    public static void tick(LocalPlayer player) {
        if (swapCooldown > 0) {
            swapCooldown--;
            return;
        }
        
        Minecraft minecraft = Minecraft.getInstance();
        boolean isAttacking = minecraft.options.keyAttack.isDown();
        
        // Detect attack press (transition from not attacking to attacking)
        if (isAttacking && !wasAttacking) {
            handleAttackStart(player);
        } 
        // Detect attack release
        else if (!isAttacking && wasAttacking) {
            handleAttackEnd(player);
        }
        
        wasAttacking = isAttacking;
    }
    
    private static void handleAttackStart(LocalPlayer player) {
        if (swapInProgress) return;
        
        ItemStack heldItem = player.getMainHandItem();
        
        // Only trigger if holding a sword
        if (isSword(heldItem)) {
            Inventory inventory = player.getInventory();
            int maceSlot = findBestMaceSlot(inventory);
            
            if (maceSlot != -1 && maceSlot < 9) { // Ensure it's in hotbar
                originalSlot = inventory.selected;
                swapInProgress = true;
                
                // Perform the swap
                inventory.selected = maceSlot;
                
                UltraFastAttributeSwapClient.LOGGER.debug("Swapped to mace in slot {} from sword in slot {}", 
                    maceSlot, originalSlot);
            }
        }
    }
    
    private static void handleAttackEnd(LocalPlayer player) {
        if (!swapInProgress) return;
        
        // Swap back to original slot
        if (originalSlot != -1) {
            player.getInventory().selected = originalSlot;
            originalSlot = -1;
            swapInProgress = false;
            swapCooldown = SWAP_COOLDOWN_TICKS;
            
            UltraFastAttributeSwapClient.LOGGER.debug("Swapped back to original slot");
        }
    }
    
    // Public helper methods for mixins
    
    public static boolean isSwapInProgress() {
        return swapInProgress;
    }
    
    public static void cancelSwap() {
        swapInProgress = false;
        originalSlot = -1;
        swapCooldown = 0;
    }
    
    public static void forceCompleteSwap(LocalPlayer player) {
        if (swapInProgress && originalSlot != -1) {
            player.getInventory().selected = originalSlot;
            originalSlot = -1;
            swapInProgress = false;
            swapCooldown = SWAP_COOLDOWN_TICKS;
        }
    }
    
    /**
     * Finds the best mace in the hotbar, prioritizing Wind Burst enchantment
     */
    private static int findBestMaceSlot(Inventory inventory) {
        int bestMaceSlot = -1;
        boolean bestHasWindBurst = false;
        float bestDamage = 0.0f;
        
        // Check hotbar slots (0-8)
        for (int i = 0; i < 9; i++) {
            ItemStack item = inventory.getItem(i);
            
            if (isMace(item)) {
                boolean hasWindBurst = EnchantmentHelper.getItemEnchantmentLevel(
                    Enchantments.WIND_BURST, item) > 0;
                float damage = getAttackDamage(item);
                
                // Prioritize Wind Burst maces
                if (hasWindBurst && !bestHasWindBurst) {
                    bestMaceSlot = i;
                    bestHasWindBurst = true;
                    bestDamage = damage;
                } 
                // If both have/haven't Wind Burst, choose higher damage
                else if (hasWindBurst == bestHasWindBurst && damage > bestDamage) {
                    bestMaceSlot = i;
                    bestDamage = damage;
                }
                // First mace found
                else if (bestMaceSlot == -1) {
                    bestMaceSlot = i;
                    bestHasWindBurst = hasWindBurst;
                    bestDamage = damage;
                }
            }
        }
        
        return bestMaceSlot;
    }
    
    private static boolean isSword(ItemStack item) {
        if (item.isEmpty()) return false;
        
        return item.is(Items.IRON_SWORD) ||
               item.is(Items.DIAMOND_SWORD) ||
               item.is(Items.NETHERITE_SWORD) ||
               item.is(Items.GOLDEN_SWORD) ||
               item.is(Items.WOODEN_SWORD);
    }
    
    private static boolean isMace(ItemStack item) {
        return item.is(Items.MACE);
    }
    
    private static float getAttackDamage(ItemStack item) {
        ItemAttributeModifiers modifiers = item.get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (modifiers == null) return 0.0f;
        
        return (float) modifiers.modifiers().stream()
            .filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE))
            .mapToDouble(modifier -> modifier.amount())
            .sum();
    }
}