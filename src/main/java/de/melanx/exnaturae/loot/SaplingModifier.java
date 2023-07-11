package de.melanx.exnaturae.loot;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class SaplingModifier extends LootModifier {

    public static final Codec<SaplingModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).apply(instance, SaplingModifier::new));

    public SaplingModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
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

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
