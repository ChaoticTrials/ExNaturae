package de.melanx.exnaturae.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class CompressedItemModel extends Model {

    private final ModelRenderer modelRenderer = new ModelRenderer(32, 32, 0, 6);

    public CompressedItemModel() {
        super(id -> RenderType.getEntitySolid(new ResourceLocation(id.getNamespace(), id.getPath() + "_model")));
    }

    @Override
    public void render(@Nonnull MatrixStack ms, @Nonnull IVertexBuilder buffer, int light, int overlay, float r, float g, float b, float a) {
        this.modelRenderer.render(ms, buffer, light, overlay, r, g, b, a);
    }
}
