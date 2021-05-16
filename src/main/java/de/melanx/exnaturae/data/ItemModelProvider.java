package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
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
        // No, u
    }

    @Override
    protected void defaultItem(ResourceLocation id, Item item) {
        this.handheld(item);
    }
}
