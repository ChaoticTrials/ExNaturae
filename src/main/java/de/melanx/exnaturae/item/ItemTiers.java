package de.melanx.exnaturae.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import vazkii.botania.common.lib.ModTags;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ItemTiers implements IItemTier {
    LIVINGWOOD_TIER(68, 2.0F, 0.5F, 0, 18, () -> Ingredient.fromTag(ModTags.Items.LIVINGWOOD)),
    DREAMWOOD_TIER(LIVINGWOOD_TIER) {
        @Override
        public int getEnchantability() {
            return 28;
        }

        @Override
        public float getEfficiency() {
            return 2.5F;
        }

        @Override
        public int getMaxUses() {
            return (int) (super.getMaxUses() * 1.5F);
        }
    },
    LIVINGROCK_ITEM_TIER(191, 4.5F, 2.5F, 1, 10, () -> Ingredient.fromTag(ModTags.Items.LIVINGROCK));

    private final int durability;
    private final float efficiency;
    private final float attackDamage;
    private final int harvestLevel;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    ItemTiers(int durability, double efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.durability = durability;
        this.efficiency = (float) efficiency;
        this.attackDamage = attackDamage;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    ItemTiers(IItemTier delegate) {
        this.durability = delegate.getMaxUses();
        this.efficiency = delegate.getEfficiency();
        this.attackDamage = delegate.getAttackDamage();
        this.harvestLevel = delegate.getHarvestLevel();
        this.enchantability = delegate.getEnchantability();
        this.repairMaterial = new LazyValue<>(delegate::getRepairMaterial);
    }

    @Override
    public int getMaxUses() {
        return this.durability;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
