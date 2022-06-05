package de.melanx.exnaturae.item.hammer;

import de.melanx.exnaturae.ExConfig;
import de.melanx.exnaturae.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.common.entity.EntityPixie;

import javax.annotation.Nonnull;

public class ElementiumHammer extends BotanyHammer {

    private static final int r = 1;
    private static final float g = 0.078F;
    private static final float b = 0.576F;

    public ElementiumHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        this(attackDamage, attackSpeed, tier, mana, false);
    }

    public ElementiumHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed) {
        super(attackDamage, attackSpeed, tier, mana, compressed);
    }

    public ElementiumHammer(float attackDamage, float attackSpeed, Tier tier, int mana, Item.Properties properties) {
        this(attackDamage, attackSpeed, tier, mana, false, properties);
    }

    public ElementiumHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, mana, compressed, properties);
    }

    @Override
    public boolean mineBlock(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entity) {
        if (!level.isClientSide) {
            Util.spawnParticle((ServerLevel) level, pos, r, g, b);

            if (Math.random() < ExConfig.pixieSpawnChance) {
                Monster nearest = level.getNearestEntity(Monster.class, TargetingConditions.DEFAULT, entity, 5, 5, 5, this.getBoundingBox(pos, ExConfig.detectEntityRange));
                EntityPixie pixie = new EntityPixie(level);
                pixie.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                pixie.setProps(nearest, entity, 0, (float) (5 * Math.random()));
                pixie.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
                level.addFreshEntity(pixie);
            }
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }

    @SuppressWarnings("SameParameterValue")
    private AABB getBoundingBox(BlockPos pos, double range) {
        double radius = range / 2;
        return new AABB(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius));
    }
}
