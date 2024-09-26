package me.neovitalism.neoecobridge.economy;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.math.BigDecimal;
import java.util.UUID;

public class BridgedBankAccount implements BankAccount {
    private final Economy economy;
    private final OfflinePlayer player;

    public BridgedBankAccount(Economy economy, UUID playerUUID) {
        this.economy = economy;
        this.player = Bukkit.getOfflinePlayer(playerUUID);
        if (!this.economy.hasAccount(this.player)) this.economy.createPlayerAccount(this.player);
    }

    @Override
    public UUID getIdentifier() {
        return this.player.getUniqueId();
    }

    @Override
    public BigDecimal getBalance() {
        return BigDecimal.valueOf(this.economy.getBalance(this.player));
    }

    @Override
    public void setBalance(BigDecimal newBalance) {
        this.economy.withdrawPlayer(this.player, this.getBalance().doubleValue());
        this.economy.depositPlayer(this.player, newBalance.doubleValue());
    }

    @Override
    public boolean hasBalance(BigDecimal bigDecimal) {
        return this.getBalance().doubleValue() >= bigDecimal.doubleValue();
    }

    @Override
    public boolean take(BigDecimal bigDecimal) {
        return this.economy.withdrawPlayer(this.player, bigDecimal.doubleValue()).transactionSuccess();
    }

    @Override
    public boolean add(BigDecimal bigDecimal) {
        return this.economy.depositPlayer(this.player, bigDecimal.doubleValue()).transactionSuccess();
    }
}
