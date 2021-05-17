package de.melanx.exnaturae;

import de.melanx.exnaturae.data.DataGenerators;
import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod("exnaturae")
public class ExNaturae extends ModXRegistration {

    private static ExNaturae instance;

    public ExNaturae() {
        super("exnaturae", new ItemGroup("exnaturae") {
            @Nonnull
            @Override
            public ItemStack createIcon() {
                return new ItemStack(ModItems.elementiumHammer);
            }
        });

        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataGenerators::gatherData);
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
}
