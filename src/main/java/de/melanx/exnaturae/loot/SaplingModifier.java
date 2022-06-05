package de.melanx.exnaturae.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SaplingModifier extends LootModifier {

    public SaplingModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        BlockState state = context.getParam(LootContextParams.BLOCK_STATE);

        if (state.is(BlockTags.LEAVES)) {
            LootTable lootTable = context.getLootTable(state.getBlock().getLootTable());
            ItemStack sapling = getSapling(lootTable, context);
            if (sapling != null) {
                generatedLoot.add(sapling);
            }
        }

        return generatedLoot;
    }

    private static ItemStack getSapling(LootTable table, LootContext context) {
        List<ItemStack> list = Lists.newArrayList();
        //noinspection deprecation
        table.getRandomItems(context, list::add);
        for (ItemStack item : list) {
            if (item.is(ItemTags.SAPLINGS)) {
                return item.copy();
            }
        }

        return null;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SaplingModifier> {

        @Override
        public SaplingModifier read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
            return new SaplingModifier(conditions);
        }

        @Override
        public JsonObject write(SaplingModifier instance) {
            return this.makeConditions(instance.conditions);
        }
    }
}
