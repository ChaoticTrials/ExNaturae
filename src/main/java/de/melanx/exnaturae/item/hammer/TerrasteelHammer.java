package de.melanx.exnaturae.item.hammer;

import de.melanx.exnaturae.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.ItemThorRing;

import javax.annotation.Nonnull;

public class TerrasteelHammer extends BotanyHammer implements ISequentialBreaker {

    private static final float r = 0.3F;
    private static final float g = 1;
    private static final float b = 0.3F;

    public TerrasteelHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        this(attackDamage, attackSpeed, tier, mana, false);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed) {
        super(attackDamage, attackSpeed, tier, mana, compressed);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, Tier tier, int mana, Item.Properties properties) {
        this(attackDamage, attackSpeed, tier, mana, false, properties);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, mana, compressed, properties);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        BlockHitResult raycast = ToolCommons.raytraceFromEntity(player, 10, false);
        if (!player.level.isClientSide && !player.isCrouching() && raycast.getType() == HitResult.Type.BLOCK) {
            Direction face = raycast.getDirection();
            this.breakOtherBlock(player, stack, pos, pos, face);
        }

        return false;
    }

    // Partially taken from Terrasteel Pickaxe by Botania
    @Override
    public void breakOtherBlock(Player player, ItemStack stack, BlockPos pos, BlockPos originPos, Direction side) {
        Level level = player.level;
        BlockState state = level.getBlockState(pos);

        if (!this.isCorrectToolForDrops(state)) {
            return;
        }

        boolean thor = !ItemThorRing.getThorRing(player).isEmpty();
        boolean doX = thor || side.getStepX() == 0;
        boolean doY = thor || side.getStepY() == 0;
        boolean doZ = thor || side.getStepZ() == 0;
        int range = 1;

        Vec3i beginDiff = new Vec3i(doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0);
        Vec3i endDiff = new Vec3i(doX ? range : 0, doY ? 1 : 0, doZ ? range : 0);

        ToolCommons.removeBlocksInIteration(player, stack, level, originPos, beginDiff, endDiff, this::isCorrectToolForDrops);
    }

    @Override
    public boolean mineBlock(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entity) {
        if (!level.isClientSide) {
            Util.spawnParticle((ServerLevel) level, pos, r, g, b);
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }
}
