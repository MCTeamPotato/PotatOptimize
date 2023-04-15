package com.teampotato.opotato.mixin.opotato.elenaidodge;

import com.elenai.elenaidodge2.util.PatronRewardHandler;
import com.teampotato.opotato.util.EmptyThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = PatronRewardHandler.class, remap = false)
public class MixinPatronRewardHandler {
    @Shadow
    private static Thread thread;

    @Shadow
    private static boolean doneLoading;

    /**
     * @author Kasualix
     * @reason Remove Elenai Dodge 2 Internet connection behavior
     */
    @Overwrite
    public static void init() {
        if (thread == null || !thread.isAlive()) {
            doneLoading = false;
            thread = new EmptyThread();
        }
    }
}
