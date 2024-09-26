package me.neovitalism.neoecobridge.api;

import com.pixelmonmod.pixelmon.api.economy.BankAccountProxy;

import java.math.BigDecimal;
import java.util.UUID;

public interface HookedEconomy {
    String getSuccessMessage();

    default void syncBalance(UUID playerUUID) {
        BankAccountProxy.getBankAccountNow(playerUUID).updatePlayer();
    }

    default void syncBalance(UUID playerUUID, double balance) {
        this.syncBalance(playerUUID, BigDecimal.valueOf(balance));
    }

    default void syncBalance(UUID playerUUID, BigDecimal balance) {
        BankAccountProxy.getBankAccountNow(playerUUID).updatePlayer(balance);
    }
}
