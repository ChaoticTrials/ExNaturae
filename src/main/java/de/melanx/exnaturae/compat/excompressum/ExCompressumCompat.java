package de.melanx.exnaturae.compat.excompressum;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.item.DummyItem;
import de.melanx.exnaturae.item.crook.BotanyCrook;
import de.melanx.exnaturae.item.hammer.BotanyHammer;
import de.melanx.exnaturae.item.hammer.ElementiumHammer;
import de.melanx.exnaturae.item.hammer.TerrasteelHammer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraftforge.fml.ModList;

public class ExCompressumCompat {

    public static final String MODID = "excompressum";

    public static Item makeCompressedCrook(float attackDamage, float attackSpeed, Tier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new BotanyCrook(attackDamage, attackSpeed, tier, mana, true, ExCompressumCompat.itemProperties());
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new BotanyHammer(attackDamage, attackSpeed, tier, mana, true, ExCompressumCompat.itemProperties());
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedElementiumHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new ElementiumHammer(attackDamage, attackSpeed, tier, mana, true, ExCompressumCompat.itemProperties());
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedTerraHammer(float attackDamage, float attackSpeed, Tier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new TerrasteelHammer(attackDamage, attackSpeed, tier, mana, true, ExCompressumCompat.itemProperties());
        } else {
            return new DummyItem(MODID);
        }
    }

    public static boolean isExCompressumLoaded() {
        return ModList.get().isLoaded(MODID);
    }

    private static Item.Properties itemProperties() {
        return new Item.Properties().tab(ExNaturae.tab());
    }
}
