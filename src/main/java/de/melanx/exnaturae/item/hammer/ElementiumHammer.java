package de.melanx.exnaturae.item.hammer;

import de.melanx.exnaturae.ExConfig;
import de.melanx.exnaturae.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import vazkii.botania.common.entity.EntityPixie;

import javax.annotation.Nonnull;

public class ElementiumHammer extends BotanyHammer {

    private static final int r = 1;
    private static final float g = 0.078F;
    private static final float b = 0.576F;

    public ElementiumHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        super(attackDamage, attackSpeed, tier, mana, false);
    }

    public ElementiumHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana, boolean compressed) {
        super(attackDamage, attackSpeed, tier, mana, compressed);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entity) {
        if (!world.isRemote) {
            Util.spawnParticle((ServerWorld) world, pos, r, g, b);

            if (Math.random() < ExConfig.pixieSpawnChance) {
                MonsterEntity nearest = world.getClosestEntityWithinAABB(MonsterEntity.class, EntityPredicate.DEFAULT, entity, 5, 5, 5, this.getBoundingBox(pos, ExConfig.detectEntityRange));
                EntityPixie pixie = new EntityPixie(world);
                pixie.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                pixie.setProps(nearest, entity, 0, (float) (5 * Math.random()));
                pixie.onInitialSpawn((IServerWorld) world, world.getDifficultyForLocation(pos), SpawnReason.EVENT, null, null);
                world.addEntity(pixie);
            }
        }

        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }

    @SuppressWarnings("SameParameterValue")
    private AxisAlignedBB getBoundingBox(BlockPos pos, double range) {
        double radius = range / 2;
        return new AxisAlignedBB(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius));
    }
}
