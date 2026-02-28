package com.bretzelfresser.justpets.rendering.model;// Made with Blockbench 4.12.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.rendering.animation.BabyJerboaAnimation;
import com.bretzelfresser.justpets.rendering.animation.SmoothAnimationState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class BabyJerboaModel extends HierarchicalModel<Jerboa> {
	private final ModelPart root;
	private final ModelPart jerboa;
	private final ModelPart body;
	private final ModelPart arm;
	private final ModelPart arm2;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart ear;
	private final ModelPart ear2;
	private final ModelPart leg2;
	private final ModelPart lower_leg2;
	private final ModelPart foot2;
	private final ModelPart leg;
	private final ModelPart lower_leg;
	private final ModelPart foot;

    public BabyJerboaModel(ModelPart root) {
		this.root = root.getChild("root");
		this.jerboa = this.root.getChild("jerboa");
		this.body = this.jerboa.getChild("body");
		this.arm = this.body.getChild("arm");
		this.arm2 = this.body.getChild("arm2");
		this.tail = this.body.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.ear = this.body.getChild("ear");
		this.ear2 = this.body.getChild("ear2");
		this.leg2 = this.jerboa.getChild("leg2");
		this.lower_leg2 = this.leg2.getChild("lower_leg2");
		this.foot2 = this.lower_leg2.getChild("foot2");
		this.leg = this.jerboa.getChild("leg");
		this.lower_leg = this.leg.getChild("lower_leg");
		this.foot = this.lower_leg.getChild("foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition jerboa = root.addOrReplaceChild("jerboa", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition body = jerboa.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 3).addBox(-0.5F, 0.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 1.0F));

		body.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -0.5F));

		body.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(2, 14).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -0.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 6).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.5F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		body.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -2.0F, -0.5F));

		body.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(12, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, -0.5F));

		PartDefinition leg2 = jerboa.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(10, 13).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -3.0F, 2.0F));

		PartDefinition lower_leg2 = leg2.addOrReplaceChild("lower_leg2", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		lower_leg2.addOrReplaceChild("foot2", CubeListBuilder.create().texOffs(12, 5).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition leg = jerboa.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(8, 13).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 2.0F));

		PartDefinition lower_leg = leg.addOrReplaceChild("lower_leg", CubeListBuilder.create().texOffs(12, 10).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		lower_leg.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(10, 9).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Jerboa entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	this.root().getAllParts().forEach(ModelPart::resetPose);
    	entity.idleAnimationState.animateIdle(this, BabyJerboaAnimation.IDLE, ageInTicks, limbSwingAmount, 1.0F, entity.runAnimationState, entity.sitAnimationState);
    	entity.sitAnimationState.animate(this, BabyJerboaAnimation.SIT, ageInTicks);
    	
    	SmoothAnimationState.animateWalk(this, BabyJerboaAnimation.WALK, limbSwing, limbSwingAmount, 1.0F, 1.0F, entity.runAnimationState, entity.sitAnimationState);
    	entity.runAnimationState.animateWalkWithFactor(this, BabyJerboaAnimation.RUN, limbSwing, limbSwingAmount, 0.5F, 0.5F);
    }
    
    @Override
    public ModelPart root() {
    	return this.root;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}