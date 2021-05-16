package de.melanx.exnaturae;

import io.github.noeppi_noeppi.libx.annotation.RegisterConfig;
import io.github.noeppi_noeppi.libx.config.Config;

@RegisterConfig
public class ExConfig {

    @Config
    public static double pixieSpawnChance = 0.1;

    @Config
    public static int detectEntityRange = 30;

}
