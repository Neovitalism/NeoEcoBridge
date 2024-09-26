package me.neovitalism.neoecobridge.utils;

import io.izzel.arclight.api.Arclight;
import me.neovitalism.neoecobridge.NeoEcoBridge;
import net.minecraftforge.eventbus.api.IEventBus;

public final class HybridUtil {
    private static boolean arclight = false;
    private static boolean mohist = false;

    public static void checkPlatform() {
        try {
            Class.forName("io.izzel.arclight.api.Arclight");
            arclight = true;
//            return;
        } catch(ClassNotFoundException ignored) {}
//        try {
//            Class.forName("com.mohistmc.MohistMC");
//            mohist = true;
//        } catch(ClassNotFoundException ignored) {}
    }

    public static void registerListener(IEventBus eventBus, Object target) {
        if(arclight) {
            Arclight.registerForgeEvent(NeoEcoBridge.inst(), eventBus, target);
//        } else if(mohist) {
//            NeoEcoBridge.inst().registerForgeEvent(eventBus, target);
        } else eventBus.register(target);
    }

    public static void unregisterListener(IEventBus eventBus, Object target) {
//        if(mohist) {
//            NeoEcoBridge.inst().unregisterForgeEvents(eventBus, target);
//        } else
        eventBus.unregister(target);
    }
}
