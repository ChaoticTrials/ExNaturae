package de.melanx.exnaturae.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.moddingx.libx.util.lazy.LazyValue;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.lib.BotaniaTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum ItemTiers implements Tier {
    LIVINGWOOD_TIER(68, 2.0F, 0.5F, 0, 18, () -> Ingredient.of(BotaniaTags.Items.LIVINGWOOD_LOGS)),
    DREAMWOOD_TIER(LIVINGWOOD_TIER) {
        @Override
        public int getEnchantmentValue() {
            return 28;
        }

        @Override
        public float getSpeed() {
            return 2.5F;
        }

        @Override
        public int getUses() {
            return (int) (super.getUses() * 1.5F);
        }
    },
    LIVINGROCK_ITEM_TIER(191, 4.5F, 2.5F, 1, 10, () -> Ingredient.of(BotaniaBlocks.livingrock));

    private final int durability;
    private final float speed;
    private final float attackDamageBonus;
    private final int harvestLevel;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    ItemTiers(int durability, double speed, float attackDamageBonus, int harvestLevel, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.speed = (float) speed;
        this.attackDamageBonus = attackDamageBonus;
        this.harvestLevel = harvestLevel;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    ItemTiers(Tier delegate) {
        this.durability = delegate.getUses();
        this.speed = delegate.getSpeed();
        this.attackDamageBonus = delegate.getAttackDamageBonus();
        this.harvestLevel = delegate.getLevel();
        this.enchantmentValue = delegate.getEnchantmentValue();
        this.repairIngredient = new LazyValue<>(delegate::getRepairIngredient);
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Nullable
    @Override
    public TagKey<Block> getTag() {
        return Tier.super.getTag();
    }
}
