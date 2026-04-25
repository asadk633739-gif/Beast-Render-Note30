package com.beast;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BeastA1Core {
    // 1. HARD-CODED PERFORMANCE (Infinix Focus)
    public static void initBeast() {
        MinecraftClient client = MinecraftClient.getInstance();
        client.options.maxFps = 100;
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
    }

    // 2. ANTI-TP & COMBAT SHIELD
    public void onCombat(PlayerEntity target) {
        if (target != null && target.isTeleporting()) {
            MinecraftClient.getInstance().worldRenderer.reload();
        }
    }

    // 3. BEAST HUD (Armor & FPS)
    public String getStats() {
        MinecraftClient client = MinecraftClient.getInstance();
        int fps = client.getCurrentFps();
        StringBuilder s = new StringBuilder("§bBEAST §8| §fFPS: " + fps + " §8| §6Armor: ");
        for (ItemStack armor : client.player.getArmorItems()) {
            int dur = armor.getMaxDamage() - armor.getDamage();
            s.append("§e[").append(dur).append("] ");
        }
        return s.toString();
    }
}
