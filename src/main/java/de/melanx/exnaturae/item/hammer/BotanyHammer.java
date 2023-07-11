package de.melanx.exnaturae.item.hammer;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.client.RenderCompressedItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
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
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BotanyHammer extends DiggerItem {

    private final int mana;
    private final boolean compressed;

    public BotanyHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        this(attackDamage, attackSpeed, tier, mana, false);
    }

    public BotanyHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed) {
        this(attackDamage, attackSpeed, tier, mana, compressed, new Item.Properties().tab(ExNaturae.tab()));
    }

    public BotanyHammer(float attackDamage, float attackSpeed, Tier tier, int mana, Item.Properties properties) {
        this(attackDamage, attackSpeed, tier, mana, false, properties);
    }

    public BotanyHammer(float attackDamage, float attackSpeed, Tier tier, int mana, boolean compressed, Item.Properties properties) {
        super(attackDamage, attackSpeed, tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
        this.mana = mana;
        this.compressed = compressed;
    }

    @Override
    public void initializeClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (!BotanyHammer.this.compressed) {
                    return Minecraft.getInstance().getItemRenderer().getBlockEntityRenderer();
                }

                return new RenderCompressedItem();
            }
        });
    }

    @Override
    public boolean isCorrectToolForDrops(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        return ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(state.getBlock()) || super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        if (ExNihiloRegistries.HAMMER_REGISTRY.isHammerable(state.getBlock())) {
            return this.speed * 0.75F;
        }

        return 0.8F;
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
