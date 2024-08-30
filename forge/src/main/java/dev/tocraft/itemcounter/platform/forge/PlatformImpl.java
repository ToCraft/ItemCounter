package dev.tocraft.itemcounter.platform.forge;

import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class PlatformImpl {
    public static @NotNull Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
