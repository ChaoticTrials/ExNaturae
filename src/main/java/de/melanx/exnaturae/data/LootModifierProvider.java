package de.melanx.exnaturae.data;

import de.melanx.exnaturae.item.ModItems;
import de.melanx.exnaturae.loot.SaplingModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.mod.ModX;

@Datagen
public class LootModifierProvider extends GlobalLootModifierProvider {

    public LootModifierProvider(DataGenerator generator, ModX mod) {
        super(generator, mod.modid);
    }

    @Override
    protected void start() {
        this.add("sapling_modifier", new SaplingModifier(new LootItemCondition[]{
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.dreamwoodCrook)).build()
        }));
    }
}
