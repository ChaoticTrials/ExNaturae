package de.melanx.exnaturae.item.hammer;

import de.melanx.exnaturae.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.ItemThorRing;

import javax.annotation.Nonnull;

public class TerrasteelHammer extends BotanyHammer implements ISequentialBreaker {

    private static final float r = 0.3F;
    private static final float g = 1;
    private static final float b = 0.3F;

    public TerrasteelHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        this(attackDamage, attackSpeed, tier, mana, false);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana, boolean compressed) {
        super(attackDamage, attackSpeed, tier, mana, compressed);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana, Item.Properties properties) {
        this(attackDamage, attackSpeed, tier, mana, false, properties);
    }

    public TerrasteelHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana, boolean compressed, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, mana, compressed, properties);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, PlayerEntity player) {
        BlockRayTraceResult raycast = ToolCommons.raytraceFromEntity(player, 10, false);
        if (!player.world.isRemote && !player.isSneaking() && raycast.getType() == RayTraceResult.Type.BLOCK) {
            Direction face = raycast.getFace();
            this.breakOtherBlock(player, stack, pos, pos, face);
        }

        return false;
    }

    // Partially taken from Terrasteel Pickaxe by Botania
    @Override
    public void breakOtherBlock(PlayerEntity player, ItemStack stack, BlockPos pos, BlockPos originPos, Direction side) {
        World world = player.world;
        BlockState state = world.getBlockState(pos);

        if (!this.canHarvestBlock(state)) {
            return;
        }

        boolean thor = !ItemThorRing.getThorRing(player).isEmpty();
        boolean doX = thor || side.getXOffset() == 0;
        boolean doY = thor || side.getYOffset() == 0;
        boolean doZ = thor || side.getZOffset() == 0;
        int range = 1;

        Vector3i beginDiff = new Vector3i(doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0);
        Vector3i endDiff = new Vector3i(doX ? range : 0, doY ? 1 : 0, doZ ? range : 0);

        ToolCommons.removeBlocksInIteration(player, stack, world, originPos, beginDiff, endDiff, this::canHarvestBlock);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entity) {
        if (!world.isRemote) {
            Util.spawnParticle((ServerWorld) world, pos, r, g, b);
        }
        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }
}
