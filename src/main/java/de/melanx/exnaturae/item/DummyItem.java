package de.melanx.exnaturae.item;

import de.melanx.exnaturae.ExNaturae;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        if (this.modid != null && !ModList.get().isLoaded(this.modid)) {
            tooltip.add(new TranslatableComponent(ExNaturae.getInstance().modid + ".compat.mod_not_loaded", this.modid).withStyle(ChatFormatting.RED));
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
