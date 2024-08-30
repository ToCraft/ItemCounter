package dev.tocraft.itemcounter.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class Platform {
    @ExpectPlatform
    @NotNull
    public static Path getConfigPath() {
        throw new AssertionError();
    }

    ;
}
