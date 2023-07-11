package de.melanx.exnaturae.data;

import de.melanx.exnaturae.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.ItemLike;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CompressionExtension;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;
import org.moddingx.libx.mod.ModX;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.item.BotaniaItems;

@Datagen
public class RecipeProvider extends RecipeProviderBase implements CraftingExtension, CompressionExtension {

    public RecipeProvider(DataGenerator generator, ModX mod) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.makeCrook(ModItems.livingwoodCrook, BotaniaItems.livingwoodTwig);
        this.makeCrook(ModItems.dreamwoodCrook, BotaniaItems.dreamwoodTwig);

        this.makeHammer(ModItems.livingwoodHammer, BotaniaBlocks.livingwood, BotaniaItems.livingwoodTwig);
        this.makeHammer(ModItems.livingrockHammer, BotaniaBlocks.livingrock, BotaniaItems.livingwoodTwig);
        this.makeHammer(ModItems.manasteelHammer, BotaniaItems.manaSteel, BotaniaItems.livingwoodTwig);
        this.makeHammer(ModItems.elementiumHammer, BotaniaItems.elementium, BotaniaItems.dreamwoodTwig);
        this.makeHammer(ModItems.terrasteelHammer, BotaniaItems.terrasteel, BotaniaItems.livingwoodTwig);
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
