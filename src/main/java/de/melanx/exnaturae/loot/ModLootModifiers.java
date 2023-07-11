package de.melanx.exnaturae.loot;

import com.mojang.serialization.Codec;
import org.moddingx.libx.annotation.registration.RegisterClass;

@RegisterClass(registry = "GLOBAL_LOOT_MODIFIER_SERIALIZERS")
public class ModLootModifiers {

    public static final Codec<SaplingModifier> saplingModifier = SaplingModifier.CODEC;
}
