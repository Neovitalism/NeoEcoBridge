package me.neovitalism.neoecobridge.economy.hooks;

import me.neovitalism.neoecobridge.NeoEcoBridge;
import me.neovitalism.neoecobridge.api.HookedEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.ServicePriority;

import java.util.List;

public class VaultWrappedEconomy implements HookedEconomy, Economy {
    private final Economy original;

    public VaultWrappedEconomy(Economy original) {
        this.original = original;
        NeoEcoBridge.inst().getServer().getServicesManager().register(Economy.class, this, NeoEcoBridge.inst(), ServicePriority.Highest);
    }

    @Override
    public String getSuccessMessage() {
        return "&aFound no explicit economy registration, so wrapped vault's instead!";
    }

    @Override
    public boolean isEnabled() {
        return this.original.isEnabled();
    }

    @Override
    public String getName() {
        return this.original.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return this.original.hasBankSupport();
    }

    @Override
    public int fractionalDigits() {
        return this.original.fractionalDigits();
    }

    @Override
    public String format(double v) {
        return this.original.format(v);
    }

    @Override
    public String currencyNamePlural() {
        return this.original.currencyNamePlural();
    }

    @Override
    public String currencyNameSingular() {
        return this.original.currencyNamePlural();
    }

    @Override
    public boolean hasAccount(String s) {
        return this.original.hasAccount(s);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return this.original.hasAccount(offlinePlayer);
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return this.original.hasAccount(s, s1);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return this.original.hasAccount(offlinePlayer, s);
    }

    @Override
    public double getBalance(String s) {
        return this.original.getBalance(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return this.original.getBalance(offlinePlayer);
    }

    @Override
    public double getBalance(String s, String s1) {
        return this.original.getBalance(s, s1);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return this.original.getBalance(offlinePlayer, s);
    }

    @Override
    public boolean has(String s, double v) {
        return this.original.has(s, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return this.original.has(offlinePlayer, v);
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return this.original.has(s, s1, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return this.original.has(offlinePlayer, s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        EconomyResponse response = this.original.withdrawPlayer(s, v);
        this.syncBalance(Bukkit.getOfflinePlayer(s).getUniqueId());
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        EconomyResponse response = this.original.withdrawPlayer(offlinePlayer, v);
        this.syncBalance(offlinePlayer.getUniqueId());
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return this.original.withdrawPlayer(s, s1, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.original.withdrawPlayer(offlinePlayer, s, v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        EconomyResponse response = this.original.depositPlayer(s, v);
        this.syncBalance(Bukkit.getOfflinePlayer(s).getUniqueId());
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        EconomyResponse response = this.original.depositPlayer(offlinePlayer, v);
        this.syncBalance(offlinePlayer.getUniqueId());
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return this.original.depositPlayer(s, s1, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.original.depositPlayer(offlinePlayer, s, v);
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return this.original.createBank(s, s1);
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return this.original.createBank(s, offlinePlayer);
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return this.original.deleteBank(s);
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return this.original.bankBalance(s);
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return this.original.bankHas(s, v);
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return this.original.bankWithdraw(s, v);
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return this.original.bankDeposit(s, v);
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return this.original.isBankOwner(s, s1);
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return this.original.isBankOwner(s, offlinePlayer);
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return this.original.isBankMember(s, s1);
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return this.original.isBankMember(s, offlinePlayer);
    }

    @Override
    public List<String> getBanks() {
        return this.original.getBanks();
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return this.original.createPlayerAccount(s);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return this.original.createPlayerAccount(offlinePlayer);
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return this.original.createPlayerAccount(s, s1);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return this.original.createPlayerAccount(offlinePlayer, s);
    }
}
