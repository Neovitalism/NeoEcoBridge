package me.neovitalism.neoecobridge.economy.hooks;

import me.neovitalism.neoecobridge.NeoEcoBridge;
import me.neovitalism.neoecobridge.api.HookedEconomy;
import net.ess3.api.events.UserBalanceUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EssXEconomy implements HookedEconomy, Listener {
    public EssXEconomy() {
        Bukkit.getPluginManager().registerEvents(this, NeoEcoBridge.inst());
    }

    @Override
    public String getSuccessMessage() {
        return "&aSuccessfully hooked into EssentialX's economy!";
    }

    @EventHandler
    public void onEssXBalanceChange(UserBalanceUpdateEvent event) {
        this.syncBalance(event.getPlayer().getUniqueId(), event.getNewBalance());
    }
}
