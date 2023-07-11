package de.melanx.exnaturae;

import de.melanx.exnaturae.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;

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
        builder.enableRegistryTracking();
    }

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {
        // NO-OP
    }

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {
        // NO-OP
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
