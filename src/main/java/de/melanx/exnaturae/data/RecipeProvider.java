package de.melanx.exnaturae.data;

import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.recipe.RecipeProviderBase;
import io.github.noeppi_noeppi.libx.data.provider.recipe.crafting.CompressionExtension;
import io.github.noeppi_noeppi.libx.data.provider.recipe.crafting.CraftingExtension;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;
import vazkii.botania.common.block.ModBlocks;

@Datagen
public class RecipeProvider extends RecipeProviderBase implements CraftingExtension, CompressionExtension {

    public RecipeProvider(DataGenerator generator, ModX mod) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.makeCrook(ModItems.livingwoodCrook, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeCrook(ModItems.dreamwoodCrook, vazkii.botania.common.item.ModItems.dreamwoodTwig);

        this.makeHammer(ModItems.livingwoodHammer, ModBlocks.livingwood, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(ModItems.livingrockHammer, ModBlocks.livingrock, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(ModItems.manasteelHammer, vazkii.botania.common.item.ModItems.manaSteel, vazkii.botania.common.item.ModItems.livingwoodTwig);
        this.makeHammer(ModItems.elementiumHammer, vazkii.botania.common.item.ModItems.elementium, vazkii.botania.common.item.ModItems.dreamwoodTwig);
        this.makeHammer(ModItems.terrasteelHammer, vazkii.botania.common.item.ModItems.terrasteel, vazkii.botania.common.item.ModItems.livingwoodTwig);
    }

    private void makeHammer(ItemLike hammer, ItemLike material, ItemLike rod) {
        this.shaped(hammer,
                " m ",
                " rm",
                "r  ",
                'm', material,
                'r', rod);
    }

    private void makeCrook(ItemLike crook, ItemLike rod) {
        this.shaped(crook,
                "rr",
                " r",
                " r",
                'r', rod);
    }
}
