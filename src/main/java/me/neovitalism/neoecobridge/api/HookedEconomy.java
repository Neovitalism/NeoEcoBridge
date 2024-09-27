package me.neovitalism.neoecobridge.api;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;
import me.neovitalism.neoecobridge.NeoEcoBridge;

import java.math.BigDecimal;
import java.util.UUID;

public interface HookedEconomy {
    String getSuccessMessage();

    default void syncBalance(UUID playerUUID) {
       BankAccount account = BankAccountProxy.getBankAccountNow(playerUUID);
        this.syncBalance(account, account.getBalance());
    }

    default void syncBalance(UUID playerUUID, double balance) {
        this.syncBalance(playerUUID, BigDecimal.valueOf(balance));
    }

    default void syncBalance(UUID playerUUID, BigDecimal balance) {
        this.syncBalance(BankAccountProxy.getBankAccountNow(playerUUID), balance);
    }

    default void syncBalance(BankAccount account, BigDecimal balance) {
        if (!NeoEcoBridge.inst().shouldShowDecimals()) balance = BigDecimal.valueOf(balance.intValue());
        account.updatePlayer(balance);
    }
}
