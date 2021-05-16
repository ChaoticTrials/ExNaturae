package de.melanx.exnaturae;

import de.melanx.exnaturae.data.DataGenerators;
import de.melanx.exnaturae.item.ModItems;
import de.melanx.exnaturae.loot.BlockTag;
import io.github.noeppi_noeppi.libx.mod.registration.ModXRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
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

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerMisc);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataGenerators::gatherData);
    }

    // We can not do this in setup as it would not be available for `runData`
    private void registerMisc(RegistryEvent.NewRegistry event) {
        Registry.register(Registry.LOOT_CONDITION_TYPE, BlockTag.ID, BlockTag.TYPE);
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
