package de.melanx.exnaturae.data;

import com.google.gson.JsonArray;
import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.compat.excompressum.ExCompressumCompat;
import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.recipe.RecipeProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.data.recipes.WrapperResult;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class RecipeProvider extends RecipeProviderBase {

    public RecipeProvider(DataGenerator generator) {
        super(ExNaturae.getInstance(), generator);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        this.makeCrook(consumer, ModItems.livingwoodCrook, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeCrook(consumer, ModItems.dreamwoodCrook, vazkii.botania.common.item.ModItems.dreamwoodTwig);

        this.makeHammer(consumer, ModItems.livingwoodHammer, ModBlocks.livingwood, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(consumer, ModItems.livingrockHammer, ModBlocks.livingrock, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(consumer, ModItems.manasteelHammer, vazkii.botania.common.item.ModItems.manaSteel, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(consumer, ModItems.elementiumHammer, vazkii.botania.common.item.ModItems.elementium, vazkii.botania.common.item.ModItems.dreamwoodTwig);
        this.makeHammer(consumer, ModItems.terrasteelHammer, vazkii.botania.common.item.ModItems.terrasteel, vazkii.botania.common.item.ModItems.livingwoodTwig);

        this.makeCrook(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedLivingwoodCrook, ModItems.livingwoodCrook);
        this.makeCrook(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedDreamwoodCrook, ModItems.dreamwoodCrook);
        this.compress(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedLivingwoodHammer, ModItems.livingwoodHammer);
        this.compress(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedLivingrockHammer, ModItems.livingrockHammer);
        this.compress(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedManasteelHammer, ModItems.manasteelHammer);
        this.compress(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedElementiumHammer, ModItems.elementiumHammer);
        this.compress(this.modLoadedConsumer(consumer, ExCompressumCompat.MODID), ModItems.compressedTerrasteelHammer, ModItems.terrasteelHammer);
    }

    private void makeHammer(Consumer<IFinishedRecipe> consumer, IItemProvider hammer, IItemProvider material, IItemProvider rod) {
        ShapedRecipeBuilder.shapedRecipe(hammer)
                .key('m', material)
                .key('r', rod)
                .patternLine(" m ")
                .patternLine(" rm")
                .patternLine("r  ")
                .setGroup("hammer")
                .addCriterion("has_item", hasItem(material))
                .build(consumer, this.loc(hammer));
    }

    private void makeCrook(Consumer<IFinishedRecipe> consumer, IItemProvider crook, IItemProvider rod) {
        ShapedRecipeBuilder.shapedRecipe(crook)
                .key('r', rod)
                .patternLine("rr")
                .patternLine(" r")
                .patternLine(" r")
                .setGroup("crook")
                .addCriterion("has_item", hasItem(rod))
                .build(consumer, this.loc(crook));
    }

    private void compress(Consumer<IFinishedRecipe> consumer, IItemProvider item, IItemProvider base) {
        ShapedRecipeBuilder.shapedRecipe(item)
                .key('a', base)
                .patternLine("aaa")
                .patternLine("aaa")
                .patternLine("aaa")
                .setGroup("compressed_hammer")
                .addCriterion("has_item", hasItem(base))
                .build(consumer, this.loc(item));
    }

    private Consumer<IFinishedRecipe> modLoadedConsumer(Consumer<IFinishedRecipe> consumer, String modid) {
        if (modid != null) {
            consumer = WrapperResult.transformJson(consumer, json -> {
                JsonArray array = new JsonArray();
                array.add(ModLoadedCondition.Serializer.INSTANCE.getJson(new ModLoadedCondition(modid)));
                json.add("conditions", array);
            });
        }

        return consumer;
    }
}
