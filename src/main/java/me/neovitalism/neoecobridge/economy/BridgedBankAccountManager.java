package me.neovitalism.neoecobridge.economy;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountManager;
import com.pixelmonmod.pixelmon.api.economy.EconomyEvent;
import me.neovitalism.neoecobridge.utils.HybridUtil;
import net.milkbowl.vault.economy.Economy;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BridgedBankAccountManager implements BankAccountManager {
    private final Economy economy;

    public BridgedBankAccountManager(Economy economy) {
        this.economy = economy;
        HybridUtil.registerListener(Pixelmon.EVENT_BUS, this);
    }

    @Override
    public CompletableFuture<? extends BankAccount> getBankAccount(UUID uuid) {
        return CompletableFuture.completedFuture(new BridgedBankAccount(this.economy, uuid));
    }

    @SubscribeEvent
    public void onGetBalance(EconomyEvent.GetBalance event) {
        event.setBalance(this.getBankAccountNow(event.getPlayer()).getBalance());
    }

    @SubscribeEvent
    public void onSetBalance(EconomyEvent.SetBalance event) {
        this.getBankAccountNow(event.getPlayer()).setBalance(event.getBalance());
    }

    @SubscribeEvent
    public void onPostTransaction(EconomyEvent.PostTransaction event) {
        this.getBankAccountNow(event.getPlayer()).setBalance(event.getNewBalance());
    }
}
