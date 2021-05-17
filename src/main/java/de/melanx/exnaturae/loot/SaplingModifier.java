package de.melanx.exnaturae.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SaplingModifier extends LootModifier {

    public SaplingModifier(ILootCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        BlockState state = context.get(LootParameters.BLOCK_STATE);

        if (state == null) {
            return generatedLoot;
        }

        if (BlockTags.LEAVES.contains(state.getBlock())) {
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
        table.generate(context, list::add);
        for (ItemStack item : list) {
            if (ItemTags.SAPLINGS.contains(item.getItem())) {
                return item.copy();
            }
        }

        return null;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SaplingModifier> {

        @Override
        public SaplingModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
            return new SaplingModifier(conditions);
        }

        @Override
        public JsonObject write(SaplingModifier instance) {
            return this.makeConditions(instance.conditions);
        }
    }
}
