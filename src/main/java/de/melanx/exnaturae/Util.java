package de.melanx.exnaturae;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import vazkii.botania.client.fx.SparkleParticleData;

public class Util {

    public static void spawnParticle(ServerLevel level, BlockPos pos, float r, float g, float b) {
        SparkleParticleData data = SparkleParticleData.sparkle((float) Math.random() + 0.3F, r, g, b, 3);
        level.sendParticles(data, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.25, 0.25, 0.25, 10);
    }
}
