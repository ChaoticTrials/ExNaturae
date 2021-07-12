package de.melanx.exnaturae.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.annotation.Model;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

import javax.annotation.Nonnull;

public class RenderCompressedItem extends ItemStackTileEntityRenderer {

    @Model("item/compressed_livingwood_hammer_gui")
    public static IBakedModel compressedLivingwoodHammerGui = null;
    @Model("item/compressed_livingwood_hammer_hand")
    public static IBakedModel compressedLivingwoodHammerHand = null;
    @Model("item/compressed_livingrock_hammer_gui")
    public static IBakedModel compressedLivingrockHammerGui = null;
    @Model("item/compressed_livingrock_hammer_hand")
    public static IBakedModel compressedLivingrockHammerHand = null;
    @Model("item/compressed_manasteel_hammer_gui")
    public static IBakedModel compressedManasteelHammerGui = null;
    @Model("item/compressed_manasteel_hammer_hand")
    public static IBakedModel compressedManasteelHammerHand = null;
    @Model("item/compressed_elementium_hammer_gui")
    public static IBakedModel compressedElementiumHammerGui = null;
    @Model("item/compressed_elementium_hammer_hand")
    public static IBakedModel compressedElementiumHammerHand = null;
    @Model("item/compressed_terrasteel_hammer_gui")
    public static IBakedModel compressedTerraHammerGui = null;
    @Model("item/compressed_terrasteel_hammer_hand")
    public static IBakedModel compressedTerraHammerHand = null;
    @Model("item/compressed_livingwood_crook_gui")
    public static IBakedModel compressedLivingwoodCrookGui = null;
    @Model("item/compressed_livingwood_crook_hand")
    public static IBakedModel compressedLivingwoodCrookHand = null;
    @Model("item/compressed_dreamwood_crook_gui")
    public static IBakedModel compressedDreamwoodCrookGui = null;
    @Model("item/compressed_dreamwood_crook_hand")
    public static IBakedModel compressedDreamwoodCrookHand = null;

    @Override
    public void render(@Nonnull ItemStack stack, @Nonnull ItemCameraTransforms.TransformType transform, @Nonnull MatrixStack ms, @Nonnull IRenderTypeBuffer buffer, int light, int overlay) {
        Minecraft mc = Minecraft.getInstance();
        IVertexBuilder vertex = buffer.getBuffer(RenderType.getCutout());

        IBakedModel guiModel;
        IBakedModel handModel;
        Item item = stack.getItem();

        if (item == ModItems.compressedLivingwoodHammer) {
            guiModel = compressedLivingwoodHammerGui;
            handModel = compressedLivingwoodHammerHand;
        } else if (item == ModItems.compressedLivingrockHammer) {
            guiModel = compressedLivingrockHammerGui;
            handModel = compressedLivingrockHammerHand;
        } else if (item == ModItems.compressedManasteelHammer) {
            guiModel = compressedManasteelHammerGui;
            handModel = compressedManasteelHammerHand;
        } else if (item == ModItems.compressedElementiumHammer) {
            guiModel = compressedElementiumHammerGui;
            handModel = compressedElementiumHammerHand;
        } else if (item == ModItems.compressedTerrasteelHammer) {
            guiModel = compressedTerraHammerGui;
            handModel = compressedTerraHammerHand;
        } else if (item == ModItems.compressedLivingwoodCrook) {
            guiModel = compressedLivingwoodCrookGui;
            handModel = compressedLivingwoodCrookHand;
        } else {
            guiModel = compressedDreamwoodCrookGui;
            handModel = compressedDreamwoodCrookHand;
        }

        if (transform == ItemCameraTransforms.TransformType.GUI) {
            mc.getItemRenderer().renderModel(guiModel, stack, light, OverlayTexture.NO_OVERLAY, ms, vertex);
        } else if (transform.isFirstPerson()) {
            mc.getItemRenderer().renderModel(guiModel, stack, light / 2, OverlayTexture.NO_OVERLAY, ms, vertex);
        } else {
            ms.push();
            ms.rotate(Vector3f.ZP.rotationDegrees(-20));
            ms.translate(-0.5, -0.35, -0.25);
            ms.scale(2, 2, 1.5F);
            mc.getItemRenderer().renderModel(handModel, stack, light / 2, OverlayTexture.NO_OVERLAY, ms, vertex);
            ms.pop();
        }
    }
}
