package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.crook.BotanyCrook;
import de.melanx.exnaturae.item.hammer.BotanyHammer;
import io.github.noeppi_noeppi.libx.data.provider.BlockTagProviderBase;
import io.github.noeppi_noeppi.libx.data.provider.ItemTagProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import novamachina.exnihilosequentia.api.ExNihiloTags;

public class ItemTagsProvider extends ItemTagProviderBase {

    private static final ITag.INamedTag<Item> COMPAT_HAMMER = ItemTags.makeWrapperTag("excompressum:hammer");
    private static final ITag.INamedTag<Item> COMPAT_COMPRESSED_HAMMER = ItemTags.makeWrapperTag("excompressum:compressed_hammer");

    public ItemTagsProvider(DataGenerator generator, ExistingFileHelper helper, BlockTagProviderBase blockTags) {
        super(ExNaturae.getInstance(), generator, helper, blockTags);
    }

    @Override
    protected void setup() {
        // No, u
    }

    @Override
    public void defaultItemTags(Item item) {
        this.getOrCreateBuilder(COMPAT_COMPRESSED_HAMMER).add(item);
        if (item instanceof BotanyHammer) {
            if (((BotanyHammer) item).isCompressed()) {
                this.getOrCreateBuilder(COMPAT_HAMMER).add(item);
            }
            this.getOrCreateBuilder(ExNihiloTags.HAMMER).add(item);
        } else if (item instanceof BotanyCrook && !((BotanyCrook) item).isCompressed()) {
            this.getOrCreateBuilder(ExNihiloTags.CROOK).add(item);
        }
    }
}
