package com.bretzelfresser.justpets.rendering.rendering;

import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.registries.ModEntities;
import com.bretzelfresser.justpets.rendering.animation.BabyJerboaAnimation;
import com.bretzelfresser.justpets.rendering.animation.JerboaAnimation;
import com.bretzelfresser.justpets.rendering.model.BabyJerboaModel;
import com.bretzelfresser.justpets.rendering.model.JerboaModel;
import com.bretzelfresser.justpets.rendering.model.ModModelLayerLocations;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class JerboaOnShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
    private final JerboaModel parentModel;
    private final BabyJerboaModel babyJerboaModel;

    public JerboaOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.parentModel = new JerboaModel(modelSet.bakeLayer(ModModelLayerLocations.JERBOA_LAYER_LOCATION));
        this.babyJerboaModel = new BabyJerboaModel(modelSet.bakeLayer(ModModelLayerLocations.BABY_JERBOA_LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, true);
        this.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, false);
    }

    protected void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, boolean leftShoulder) {
        CompoundTag compoundtag = leftShoulder ? livingEntity.getShoulderEntityLeft() : livingEntity.getShoulderEntityRight();
        EntityType.create(compoundtag, livingEntity.level()).filter((entityType) -> entityType.getType() == ModEntities.JERBOA.get()).ifPresent((e) -> {
            poseStack.pushPose();
            poseStack.scale(0.7f, 0.7f, 0.7f);
            poseStack.translate(leftShoulder ? 0.4F : -0.4F, livingEntity.isCrouching() ? -1.3F : -1.5F, 0.0F);
            Jerboa j = (Jerboa) e;
            j.setInSittingPose(true);
            HierarchicalModel<Jerboa> model = j.isBaby() ? this.babyJerboaModel : this.parentModel;
            model.root().getAllParts().forEach(ModelPart::resetPose);
            KeyframeAnimations.animate(model, j.isBaby() ? BabyJerboaAnimation.SIT : JerboaAnimation.SIT, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
            this.renderJerboaModel(model, j, poseStack, buffer, packedLight);
            poseStack.popPose();
        });
    }

    protected void renderJerboaModel(EntityModel<Jerboa> model, Jerboa jerboa, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer vertexconsumer = buffer.getBuffer(model.renderType(jerboa.getTexture()));
        model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
    }
}