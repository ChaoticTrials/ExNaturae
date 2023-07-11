package de.melanx.exnaturae.data;

import de.melanx.exnaturae.compat.excompressum.ExCompressumCompat;
import de.melanx.exnaturae.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CompressionExtension;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;
import org.moddingx.libx.mod.ModX;

import java.util.List;

@Datagen
public class CompressedRecipeProvider extends RecipeProviderBase implements CraftingExtension, CompressionExtension {

    public CompressedRecipeProvider(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.makeCrook(ModItems.compressedLivingwoodCrook, ModItems.livingwoodCrook);
        this.makeCrook(ModItems.compressedDreamwoodCrook, ModItems.dreamwoodCrook);

        this.compress(ModItems.livingwoodHammer, ModItems.compressedLivingwoodHammer, false);
        this.compress(ModItems.livingrockHammer, ModItems.compressedLivingrockHammer, false);
        this.compress(ModItems.manasteelHammer, ModItems.compressedManasteelHammer, false);
        this.compress(ModItems.elementiumHammer, ModItems.compressedElementiumHammer, false);
        this.compress(ModItems.terrasteelHammer, ModItems.compressedTerrasteelHammer, false);
    }

    private void makeCrook(ItemLike crook, ItemLike rod) {
        this.shaped(crook,
                "rr",
                " r",
                " r",
                'r', rod);
    }

    @Override
    protected List<ICondition> conditions() {
        return List.of(new ModLoadedCondition(ExCompressumCompat.MODID));
    }
}
