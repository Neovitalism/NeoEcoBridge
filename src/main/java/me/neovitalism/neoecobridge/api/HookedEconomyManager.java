package me.neovitalism.neoecobridge.api;

import me.neovitalism.neoecobridge.economy.hooks.CMIEconomy;
import me.neovitalism.neoecobridge.economy.hooks.EssXEconomy;
import me.neovitalism.neoecobridge.economy.hooks.VaultWrappedEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class HookedEconomyManager {
    private static final Map<String, Class<? extends HookedEconomy>> REGISTERED_HOOKS = new HashMap<>();
    private static HookedEconomy hookedEconomy = null;

    public static void registerHookedEconomy(String id, Class<? extends HookedEconomy> economyClass) {
        REGISTERED_HOOKS.put(id, economyClass);
    }

    public static String hookEconomy(Economy economy) {
        Class<? extends HookedEconomy> clazz = REGISTERED_HOOKS.get(economy.getName());
        if (clazz != null) {
            try {
                hookedEconomy = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ignored) {}
        }
        if (hookedEconomy == null) hookedEconomy = new VaultWrappedEconomy(economy);
        return hookedEconomy.getSuccessMessage();
    }

    public static void syncAll() {
        if (hookedEconomy == null) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            hookedEconomy.syncBalance(player.getUniqueId());
        }
    }

    static {
        HookedEconomyManager.registerHookedEconomy("CMIEconomy", CMIEconomy.class);
        HookedEconomyManager.registerHookedEconomy("EssentialsX Economy", EssXEconomy.class);
    }
}
