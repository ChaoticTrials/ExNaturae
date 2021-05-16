package de.melanx.exnaturae.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, helper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ItemTagsProvider(generator, helper, blockTagsProvider));
            generator.addProvider(new LootModifierProvider(generator));
            generator.addProvider(new RecipeProvider(generator));
        }

        if (event.includeClient()) {
//            generator.addProvider(new ItemModelProvider(generator, helper)); TODO add textures
        }
    }
}
