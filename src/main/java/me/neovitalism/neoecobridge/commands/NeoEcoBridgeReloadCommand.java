package me.neovitalism.neoecobridge.commands;

import me.neovitalism.neoecobridge.NeoEcoBridge;
import me.neovitalism.neoecobridge.utils.ColorUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;

public class NeoEcoBridgeReloadCommand {
    public NeoEcoBridgeReloadCommand() {
        PluginCommand neoEcoBridgeCommand = NeoEcoBridge.inst().getCommand("neoecobridge");
        assert neoEcoBridgeCommand != null;
        neoEcoBridgeCommand.setExecutor(this.getExecutor());
        neoEcoBridgeCommand.setTabCompleter(this.getTabCompleter());
    }

    private CommandExecutor getExecutor() {
        return (sender, command, label, args) -> {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                NeoEcoBridge.inst().configManager();
                sender.sendMessage(ColorUtil.parseColour(NeoEcoBridge.PLUGIN_PREFIX + "&aReloaded Config!"));
                return true;
            }
            sender.sendMessage(ColorUtil.parseColour(NeoEcoBridge.PLUGIN_PREFIX + "&cInvalid args."));
            return false;
        };
    }

    private TabCompleter getTabCompleter() {
        return (sender, command, alias, args) -> {
            if (args.length == 1) return StringUtil.copyPartialMatches(args[0], Collections.singleton("reload"), new ArrayList<>());
            return Collections.emptyList();
        };
    }
}
