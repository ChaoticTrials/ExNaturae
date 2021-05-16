package de.melanx.exnaturae;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import vazkii.botania.client.fx.SparkleParticleData;

public class Util {

    public static void spawnParticle(ServerWorld world, BlockPos pos, float r, float g, float b) {
        SparkleParticleData data = SparkleParticleData.sparkle((float) Math.random() + 0.3F, r, g, b, 3);
        world.spawnParticle(data, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 30, 0.25, 0.25, 0.25, 10);
    }
}
