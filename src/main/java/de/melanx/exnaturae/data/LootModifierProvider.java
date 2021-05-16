package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.ModItems;
import de.melanx.exnaturae.loot.BlockTag;
import de.melanx.exnaturae.loot.ModLootModifiers;
import de.melanx.exnaturae.loot.SaplingModifier;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class LootModifierProvider extends GlobalLootModifierProvider {
    public LootModifierProvider(DataGenerator generator) {
        super(generator, ExNaturae.getInstance().modid);
    }

    @Override
    protected void start() {
        this.add("sapling_modifier", ModLootModifiers.saplingModifier, new SaplingModifier(new ILootCondition[]{
                BlockTag.builder(BlockTags.LEAVES).build(),
                MatchTool.builder(ItemPredicate.Builder.create().item(ModItems.dreamwoodCrook)).build()
        }));
    }
}
