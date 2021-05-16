package de.melanx.exnaturae.item;

import de.melanx.exnaturae.ExNaturae;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DummyItem extends Item {

    private final String modid;

    public DummyItem(String modid) {
        super(new Properties());
        this.modid = modid;
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, @Nonnull BlockState state) {
        return 0.0F;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flag) {
        if (this.modid != null && !ModList.get().isLoaded(this.modid)) {
            tooltip.add(new TranslationTextComponent(ExNaturae.getInstance().modid + ".compat.mod_not_loaded", this.modid).mergeStyle(TextFormatting.RED));
        }
        super.addInformation(stack, world, tooltip, flag);
    }
}
