package de.melanx.exnaturae.item;

import de.melanx.exnaturae.compat.excompressum.ExCompressumCompat;
import de.melanx.exnaturae.item.crook.BotanyCrook;
import de.melanx.exnaturae.item.hammer.BotanyHammer;
import de.melanx.exnaturae.item.hammer.ElementiumHammer;
import de.melanx.exnaturae.item.hammer.TerrasteelHammer;
import net.minecraft.world.item.Item;
import org.moddingx.libx.annotation.registration.RegisterClass;
import vazkii.botania.api.BotaniaAPI;

@RegisterClass(registry = "ITEM_REGISTRY")
public class ModItems {

    public static final Item livingwoodHammer = new BotanyHammer(1, -3.2F, ItemTiers.LIVINGWOOD_TIER, 40);
    public static final Item livingrockHammer = new BotanyHammer(0, -3.2F, ItemTiers.LIVINGROCK_ITEM_TIER, 50);
    public static final Item manasteelHammer = new BotanyHammer(0.5F, -3.2F, BotaniaAPI.instance().getManasteelItemTier(), 60);
    public static final Item elementiumHammer = new ElementiumHammer(0.5F, -3.2F, BotaniaAPI.instance().getElementiumItemTier(), 60);
    public static final Item terrasteelHammer = new TerrasteelHammer(0.5F, -3.2F, BotaniaAPI.instance().getTerrasteelItemTier(), 80);

    public static final Item livingwoodCrook = new BotanyCrook(1, -3.2F, ItemTiers.LIVINGWOOD_TIER, 40);
    public static final Item dreamwoodCrook = new BotanyCrook(0, -3.2F, ItemTiers.DREAMWOOD_TIER, 40);

    public static final Item compressedLivingwoodHammer = ExCompressumCompat.makeCompressedHammer(1, -3.2F, ItemTiers.LIVINGWOOD_TIER, 50);
    public static final Item compressedLivingrockHammer = ExCompressumCompat.makeCompressedHammer(0, -3.2F, ItemTiers.LIVINGROCK_ITEM_TIER, 60);
    public static final Item compressedManasteelHammer = ExCompressumCompat.makeCompressedHammer(0.5F, -3.2F, BotaniaAPI.instance().getManasteelItemTier(), 70);
    public static final Item compressedElementiumHammer = ExCompressumCompat.makeCompressedElementiumHammer(0.5F, -3.2F, BotaniaAPI.instance().getElementiumItemTier(), 70);
    public static final Item compressedTerrasteelHammer = ExCompressumCompat.makeCompressedTerraHammer(0.5F, -3.2F, BotaniaAPI.instance().getTerrasteelItemTier(), 80);

    public static final Item compressedLivingwoodCrook = ExCompressumCompat.makeCompressedCrook(0, 0, ItemTiers.LIVINGWOOD_TIER, 50);
    public static final Item compressedDreamwoodCrook = ExCompressumCompat.makeCompressedCrook(1, 0, ItemTiers.DREAMWOOD_TIER, 50);
}
