package de.melanx.exnaturae.compat.excompressum;

import de.melanx.exnaturae.ExNaturae;
import de.melanx.exnaturae.client.RenderCompressedItem;
import de.melanx.exnaturae.item.DummyItem;
import de.melanx.exnaturae.item.crook.BotanyCrook;
import de.melanx.exnaturae.item.hammer.BotanyHammer;
import de.melanx.exnaturae.item.hammer.ElementiumHammer;
import de.melanx.exnaturae.item.hammer.TerrasteelHammer;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;

public class ExCompressumCompat {

    public static final String MODID = "excompressum";
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().group(ExNaturae.getInstance().tab).setISTER(() -> RenderCompressedItem::new);

    public static Item makeCompressedCrook(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new BotanyCrook(attackDamage, attackSpeed, tier, mana, true, ITEM_PROPERTIES);
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new BotanyHammer(attackDamage, attackSpeed, tier, mana, true, ITEM_PROPERTIES);
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedElementiumHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new ElementiumHammer(attackDamage, attackSpeed, tier, mana, true, ITEM_PROPERTIES);
        } else {
            return new DummyItem(MODID);
        }
    }

    public static Item makeCompressedTerraHammer(float attackDamage, float attackSpeed, IItemTier tier, int mana) {
        if (isExCompressumLoaded()) {
            return new TerrasteelHammer(attackDamage, attackSpeed, tier, mana, true, ITEM_PROPERTIES);
        } else {
            return new DummyItem(MODID);
        }
    }

    public static boolean isExCompressumLoaded() {
        return ModList.get().isLoaded(MODID);
    }
}
