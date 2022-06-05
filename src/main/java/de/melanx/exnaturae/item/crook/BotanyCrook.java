package de.melanx.exnaturae.item.crook;

import de.melanx.exnaturae.ExNaturae;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BotanyCrook extends DiggerItem {

    private final int mana;
    private final boolean compressed;

    public BotanyCrook(float attackDamage, float attackSpeed, Tier tier, int mana) {
        this(attackDamage, attackSpeed, tier, mana, false);
    }

    public BotanyCrook(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed) {
        this(attackDamage, attackSpeed, tier, mana, compressed, new Item.Properties().tab(ExNaturae.tab()));
    }

    public BotanyCrook(float attackDamage, float attackSpeed, Tier tier, int mana, Item.Properties properties) {
        this(attackDamage, attackSpeed, tier, mana, false, properties);
    }

    public BotanyCrook(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, BlockTags.LEAVES, properties);
        this.mana = mana;
        this.compressed = compressed;
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        return state.getMaterial() == Material.LEAVES ? this.speed : super.getDestroySpeed(stack, state);
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull Entity player, int slot, boolean isSelected) {
        if (!level.isClientSide && player instanceof Player && stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, (Player) player, this.mana * 2, true)) {
            stack.setDamageValue(stack.getDamageValue() - 1);
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
