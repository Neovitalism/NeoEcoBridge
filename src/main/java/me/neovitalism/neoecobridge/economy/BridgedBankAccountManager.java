package me.neovitalism.neoecobridge.economy;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.api.economy.BankAccountManager;
import com.pixelmonmod.pixelmon.api.economy.EconomyEvent;
import me.neovitalism.neoecobridge.utils.HybridUtil;
import net.milkbowl.vault.economy.Economy;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Optional;
import java.util.UUID;

public class BridgedBankAccountManager implements BankAccountManager {
    private final Economy economy;

    public BridgedBankAccountManager(Economy economy) {
        this.economy = economy;
        HybridUtil.registerListener(Pixelmon.EVENT_BUS, this);
    }

    @Override
    public Optional<? extends BankAccount> getBankAccount(UUID uuid) {
        return Optional.of(new BridgedBankAccount(this.economy, uuid));
    }

    @SubscribeEvent
    public void onGetBalance(EconomyEvent.GetBalance event) {
        event.setBalance(this.getBankAccountUnsafe(event.getPlayer()).getBalance());
    }

    @SubscribeEvent
    public void onSetBalance(EconomyEvent.SetBalance event) {
        this.getBankAccountUnsafe(event.getPlayer()).setBalance(event.getBalance());
    }

    @SubscribeEvent
    public void onPostTransaction(EconomyEvent.PostTransaction event) {
        this.getBankAccountUnsafe(event.getPlayer()).setBalance(event.getNewBalance());
    }
}
