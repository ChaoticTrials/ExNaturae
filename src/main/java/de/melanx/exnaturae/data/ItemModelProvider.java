package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.data.provider.ItemModelProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends ItemModelProviderBase {

    public ItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(ExNaturae.getInstance(), generator, helper);
    }

    @Override
    protected void setup() {
        this.manualModel(ModItems.compressedLivingwoodHammer);
        this.manualModel(ModItems.compressedLivingrockHammer);
        this.manualModel(ModItems.compressedManasteelHammer);
        this.manualModel(ModItems.compressedElementiumHammer);
        this.manualModel(ModItems.compressedTerrasteelHammer);
        this.manualModel(ModItems.compressedLivingwoodCrook);
        this.manualModel(ModItems.compressedDreamwoodCrook);

        this.unreleasedItem(ModItems.compressedLivingrockHammer);
        this.unreleasedItem(ModItems.compressedManasteelHammer);
        this.unreleasedItem(ModItems.compressedElementiumHammer);
        this.unreleasedItem(ModItems.compressedTerrasteelHammer);
        this.unreleasedItem(ModItems.compressedLivingwoodCrook);
        this.unreleasedItem(ModItems.compressedDreamwoodCrook);
    }

    @Override
    protected void defaultItem(ResourceLocation id, Item item) {
        this.withExistingParent(id.getPath(), HANDHELD).texture("layer0", new ResourceLocation(id.getNamespace(), "item/" + id.getPath()));
    }

    private void unreleasedItem(Item item) {
        ResourceLocation id = item.getRegistryName();
        //noinspection ConstantConditions
        this.withExistingParent(id.getPath(), GENERATED).texture("layer0", new ResourceLocation(ExNaturae.getInstance().modid, "item/unreleased"));
    }
}
