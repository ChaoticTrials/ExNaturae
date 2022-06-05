package de.melanx.exnaturae.loot;

import io.github.noeppi_noeppi.libx.annotation.registration.RegisterClass;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

@RegisterClass
public class ModLootModifiers {

    public static final GlobalLootModifierSerializer<SaplingModifier> saplingModifier = new SaplingModifier.Serializer();
}
