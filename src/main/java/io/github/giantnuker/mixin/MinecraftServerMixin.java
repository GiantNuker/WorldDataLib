package io.github.giantnuker.mixin;

import io.github.giantnuker.WorldDataLib;
import net.minecraft.class_4952;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

/**
 * @author Indigo Amann
 */
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow @Final private File gameDir;

    @Inject(method = "loadWorld", at = @At("RETURN"))
    private void loadNBT(String string, String string2, long l, class_4952 arg, CallbackInfo ci) {
        WorldDataLib.Internals.setGameDir(gameDir);
        WorldDataLib.Internals.setWorldDir(new File(gameDir + "/" + string));
        WorldDataLib.getIOCallbacks().forEach(callback -> callback.onWorldLoad(new File(gameDir + "/" + string), gameDir));
    }
}
