package me.neovitalism.neoecobridge.api;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;

import java.math.BigDecimal;
import java.util.UUID;

public interface HookedEconomy {
    String getSuccessMessage();

    default void syncBalance(UUID playerUUID) {
        BankAccount account = BankAccountProxy.getBankAccountUnsafe(playerUUID);
        this.syncBalance(account, account.getBalance());
    }

    default void syncBalance(UUID playerUUID, double balance) {
        this.syncBalance(playerUUID, BigDecimal.valueOf(balance));
    }

    default void syncBalance(UUID playerUUID, BigDecimal balance) {
        this.syncBalance(BankAccountProxy.getBankAccountUnsafe(playerUUID), balance);
    }

    default void syncBalance(BankAccount account, BigDecimal balance) {
        account.updatePlayer(balance);
    }
}
