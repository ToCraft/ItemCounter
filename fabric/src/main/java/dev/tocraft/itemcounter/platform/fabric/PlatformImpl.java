package dev.tocraft.itemcounter.platform.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class PlatformImpl {
    public static @NotNull Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
