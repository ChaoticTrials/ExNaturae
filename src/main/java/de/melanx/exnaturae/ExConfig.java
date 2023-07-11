package de.melanx.exnaturae;

import org.moddingx.libx.annotation.config.RegisterConfig;
import org.moddingx.libx.config.Config;

@RegisterConfig
public class ExConfig {

    @Config
    public static double pixieSpawnChance = 0.1;

    @Config
    public static int detectEntityRange = 30;

}
