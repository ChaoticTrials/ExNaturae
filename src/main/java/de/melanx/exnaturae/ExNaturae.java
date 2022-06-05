package de.melanx.exnaturae;

import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import io.github.noeppi_noeppi.libx.mod.registration.RegistrationBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nonnull;

@Mod("exnaturae")
public final class ExNaturae extends ModXRegistration {

    private static ExNaturae instance;

    public ExNaturae() {
        super(new CreativeModeTab("exnaturae") {
            @Nonnull
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.elementiumHammer);
            }
        });
        instance = this;
    }

    @Override
    protected void initRegistration(RegistrationBuilder builder) {
        builder.setVersion(1);
    }

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {

    }

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {

    }

    public static ExNaturae getInstance() {
        return instance;
    }

    @Nonnull
    public static CreativeModeTab tab() {
        //noinspection ConstantConditions
        return ExNaturae.getInstance().tab;
    }
}
