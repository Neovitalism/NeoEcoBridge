package me.neovitalism.neoecobridge;

import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;
import me.neovitalism.neoecobridge.api.HookedEconomyManager;
import me.neovitalism.neoecobridge.economy.BridgedBankAccountManager;
import me.neovitalism.neoecobridge.utils.ColorUtil;
import me.neovitalism.neoecobridge.utils.HybridUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class NeoEcoBridge extends JavaPlugin {
    private static final String PLUGIN_PREFIX = "&#696969[&#7E50C7N&#7E60C7e&#7F70C6o" +
            "&#7F80C6E&#7F90C6c&#80A0C6o&#80AFC5B&#81BFC5r&#81CFC5i&#81DFC5d&#82EFC4g&#82FFC4e&#696969]&f ";
    private static NeoEcoBridge instance;

    @Override
    public void onEnable() {
        instance = this;
        HybridUtil.checkPlatform();
        RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider == null) {
            this.sendConsoleMessage("&4No economy provider found! This plugin will do nothing.");
            return;
        }
        Economy economy = economyProvider.getProvider();
        this.sendConsoleMessage(HookedEconomyManager.hookEconomy(economy));
        BankAccountProxy.setAccountManager(new BridgedBankAccountManager(economy));
    }

    private void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.parseColour(NeoEcoBridge.PLUGIN_PREFIX + message));
    }

    public static NeoEcoBridge inst() {
        return instance;
    }
}
