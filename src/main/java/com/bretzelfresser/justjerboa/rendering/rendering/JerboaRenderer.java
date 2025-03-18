package com.bretzelfresser.justjerboa.rendering.rendering;

import com.bretzelfresser.justjerboa.entity.Jerboa;
import com.bretzelfresser.justjerboa.rendering.model.BabyJerboaModel;
import com.bretzelfresser.justjerboa.rendering.model.JerboaModel;
import com.bretzelfresser.justjerboa.rendering.model.ModModelLayerLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JerboaRenderer extends MobRenderer<Jerboa, EntityModel<Jerboa>> {

    protected final JerboaModel parentModel;
    protected final BabyJerboaModel babyModel;

    public JerboaRenderer(EntityRendererProvider.Context context) {
        super(context, new JerboaModel(context.bakeLayer(ModModelLayerLocations.JERBOA_LAYER_LOCATION)), 0.2f);
        parentModel = new JerboaModel(context.bakeLayer(ModModelLayerLocations.JERBOA_LAYER_LOCATION));
        babyModel = new BabyJerboaModel(context.bakeLayer(ModModelLayerLocations.BABY_JERBOA_LAYER_LOCATION));
        this.model = parentModel;
    }

    @Override
    public void render(Jerboa p_entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        this.model = p_entity.isBaby() ? babyModel : parentModel;
        super.render(p_entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Jerboa jerboa) {
        return jerboa.getTexture();
    }
}
