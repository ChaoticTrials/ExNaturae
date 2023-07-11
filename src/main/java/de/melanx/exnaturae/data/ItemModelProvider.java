package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.ItemModelProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class ItemModelProvider extends ItemModelProviderBase {

    private static final ResourceLocation LARGE_TOOL = new ResourceLocation(ExNaturae.getInstance().modid, "item/large_tool");

    public ItemModelProvider(DataGenerator generator, ModX mod, ExistingFileHelper helper) {
        super(mod, generator, helper);
    }

    @Override
    protected void setup() {
        this.itemWithCustomModel(ModItems.compressedLivingwoodHammer);
        this.itemWithCustomModel(ModItems.compressedLivingrockHammer);
        this.itemWithCustomModel(ModItems.compressedManasteelHammer);
        this.itemWithCustomModel(ModItems.compressedElementiumHammer);
        this.itemWithCustomModel(ModItems.compressedTerrasteelHammer);
        this.itemWithCustomModel(ModItems.compressedLivingwoodCrook);
        this.itemWithCustomModel(ModItems.compressedDreamwoodCrook);
    }

    @Override
    protected void defaultItem(ResourceLocation id, Item item) {
        this.withExistingParent(id.getPath(), HANDHELD).texture("layer0", new ResourceLocation(id.getNamespace(), "item/" + id.getPath()));
    }

    private void itemWithCustomModel(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        this.manualModel(item);
        //noinspection ConstantConditions
        this.withExistingParent(id.getPath(), LARGE_TOOL);
        this.withExistingParent(id.getPath() + "_gui", HANDHELD).texture("layer0", new ResourceLocation(id.getNamespace(), "item/" + id.getPath()));
        this.withExistingParent(id.getPath() + "_hand", HANDHELD).texture("layer0", new ResourceLocation(id.getNamespace(), "item/" + id.getPath() + "_model"));
    }

    private void unreleasedItem(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        //noinspection ConstantConditions
        this.withExistingParent(id.getPath(), GENERATED).texture("layer0", new ResourceLocation(ExNaturae.getInstance().modid, "item/unreleased"));
    }
}
