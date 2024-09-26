package me.neovitalism.neoecobridge.economy.hooks;

import com.Zrips.CMI.events.CMIUserBalanceChangeEvent;
import me.neovitalism.neoecobridge.NeoEcoBridge;
import me.neovitalism.neoecobridge.api.HookedEconomy;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CMIEconomy implements HookedEconomy, Listener {
    public CMIEconomy() {
        Bukkit.getPluginManager().registerEvents(this, NeoEcoBridge.inst());
    }

    @Override
    public String getSuccessMessage() {
        return "&aSuccessfully hooked into CMI's economy!";
    }

    @EventHandler
    public void onCMIBalanceChange(CMIUserBalanceChangeEvent event) {
        this.syncBalance(event.getUser().getUniqueId(), event.getTo());
    }
}
