package de.melanx.exnaturae.item.crook;

import com.google.common.collect.Sets;
import de.melanx.exnaturae.ExNaturae;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BotanyCrook extends ToolItem implements IManaUsingItem {

    private final int mana;
    private final boolean compressed;

    public BotanyCrook(float attackDamageIn, float attackSpeedIn, IItemTier tier, int mana) {
        this(attackDamageIn, attackSpeedIn, tier, mana, false);
    }

    public BotanyCrook(float attackDamageIn, float attackSpeedIn, IItemTier tier, int mana, boolean compressed) {
        super(attackDamageIn, attackSpeedIn, tier, Sets.newHashSet(), new Item.Properties().group(ExNaturae.getInstance().tab));
        this.mana = mana;
        this.compressed = compressed;
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        return state.getMaterial() == Material.LEAVES ? this.efficiency : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull Entity player, int slot, boolean isSelected) {
        if (!world.isRemote && player instanceof PlayerEntity && stack.getDamage() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, (PlayerEntity) player, this.mana * 2, true)) {
            stack.setDamage(stack.getDamage() - 1);
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, this.mana);
    }

    public boolean isCompressed() {
        return this.compressed;
    }
}
