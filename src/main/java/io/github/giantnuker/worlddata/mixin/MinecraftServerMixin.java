package io.github.giantnuker.worlddata.mixin;

import io.github.giantnuker.worlddata.WorldDataLib;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelGeneratorOptions;
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
    private void loadNBT(String string, String string2, long l, LevelGeneratorOptions levelGeneratorOptions, CallbackInfo ci) {
        WorldDataLib.Internals.setGameDir(gameDir);
        WorldDataLib.Internals.setWorldDir(new File(gameDir + "/" + string));
        WorldDataLib.getIOCallbacks().forEach(callback -> callback.onWorldLoad(new File(gameDir + "/" + string), gameDir));
    }
}
