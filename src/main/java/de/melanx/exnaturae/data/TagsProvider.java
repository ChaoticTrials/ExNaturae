package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.crook.BotanyCrook;
import de.melanx.exnaturae.item.hammer.BotanyHammer;
import io.github.noeppi_noeppi.libx.annotation.data.Datagen;
import io.github.noeppi_noeppi.libx.data.provider.CommonTagsProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.tag.ExNihiloTags;

@Datagen
public class TagsProvider extends CommonTagsProviderBase {

    private static final TagKey<Item> COMPAT_HAMMER = ItemTags.create(new ResourceLocation("excompressum", "hammer"));
    private static final TagKey<Item> COMPAT_COMPRESSED_HAMMER = ItemTags.create(new ResourceLocation("excompressum", "compressed_hammer"));

    public TagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(ExNaturae.getInstance(), generator, helper);
    }

    @Override
    public void setup() {
        // NO-OP
    }

    @Override
    public void defaultItemTags(Item item) {
        this.item(COMPAT_HAMMER).add(item);
        if (item instanceof BotanyHammer) {
            if (((BotanyHammer) item).isCompressed()) {
                this.item(COMPAT_COMPRESSED_HAMMER).add(item);
            } else {
                this.item(ExNihiloTags.HAMMER).add(item);
            }
        } else if (item instanceof BotanyCrook && !((BotanyCrook) item).isCompressed()) {
            this.item(ExNihiloTags.CROOK).add(item);
        }
    }
}
