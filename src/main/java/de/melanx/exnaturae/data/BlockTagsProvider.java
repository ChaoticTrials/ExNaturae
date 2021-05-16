package de.melanx.exnaturae.data;

import de.melanx.exnaturae.ExNaturae;
import io.github.noeppi_noeppi.libx.data.provider.BlockTagProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProvider extends BlockTagProviderBase {

    public BlockTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(ExNaturae.getInstance(), generator, helper);
    }

    @Override
    protected void setup() {
        // No, u
    }
}
