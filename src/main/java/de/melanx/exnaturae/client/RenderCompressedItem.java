package de.melanx.exnaturae.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import de.melanx.exnaturae.item.ModItems;
import io.github.noeppi_noeppi.libx.annotation.model.Model;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class RenderCompressedItem extends BlockEntityWithoutLevelRenderer {

    @Model("item/compressed_livingwood_hammer_gui")
    public static BakedModel compressedLivingwoodHammerGui = null;
    @Model("item/compressed_livingwood_hammer_hand")
    public static BakedModel compressedLivingwoodHammerHand = null;
    @Model("item/compressed_livingrock_hammer_gui")
    public static BakedModel compressedLivingrockHammerGui = null;
    @Model("item/compressed_livingrock_hammer_hand")
    public static BakedModel compressedLivingrockHammerHand = null;
    @Model("item/compressed_manasteel_hammer_gui")
    public static BakedModel compressedManasteelHammerGui = null;
    @Model("item/compressed_manasteel_hammer_hand")
    public static BakedModel compressedManasteelHammerHand = null;
    @Model("item/compressed_elementium_hammer_gui")
    public static BakedModel compressedElementiumHammerGui = null;
    @Model("item/compressed_elementium_hammer_hand")
    public static BakedModel compressedElementiumHammerHand = null;
    @Model("item/compressed_terrasteel_hammer_gui")
    public static BakedModel compressedTerraHammerGui = null;
    @Model("item/compressed_terrasteel_hammer_hand")
    public static BakedModel compressedTerraHammerHand = null;
    @Model("item/compressed_livingwood_crook_gui")
    public static BakedModel compressedLivingwoodCrookGui = null;
    @Model("item/compressed_livingwood_crook_hand")
    public static BakedModel compressedLivingwoodCrookHand = null;
    @Model("item/compressed_dreamwood_crook_gui")
    public static BakedModel compressedDreamwoodCrookGui = null;
    @Model("item/compressed_dreamwood_crook_hand")
    public static BakedModel compressedDreamwoodCrookHand = null;

    public RenderCompressedItem(BlockEntityRendererProvider.Context context) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType transform, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int light, int overlay) {
        Minecraft mc = Minecraft.getInstance();
        VertexConsumer vertex = buffer.getBuffer(RenderType.cutout());

        BakedModel guiModel;
        BakedModel handModel;
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

        if (transform == ItemTransforms.TransformType.GUI) {
            mc.getItemRenderer().renderModelLists(guiModel, stack, light, OverlayTexture.NO_OVERLAY, poseStack, vertex);
        } else if (transform.firstPerson()) {
            mc.getItemRenderer().renderModelLists(guiModel, stack, light / 2, OverlayTexture.NO_OVERLAY, poseStack, vertex);
        } else {
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-20));
            poseStack.translate(-0.5, -0.35, -0.25);
            poseStack.scale(2, 2, 1.5F);
            mc.getItemRenderer().renderModelLists(handModel, stack, light / 2, OverlayTexture.NO_OVERLAY, poseStack, vertex);
            poseStack.popPose();
        }
    }
}
