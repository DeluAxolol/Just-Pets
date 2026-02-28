package com.bretzelfresser.justpets.rendering.model;// Made with Blockbench 4.12.1

import com.bretzelfresser.justpets.entity.Jerboa;
import com.bretzelfresser.justpets.rendering.animation.JerboaAnimation;
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

public class JerboaModel extends HierarchicalModel<Jerboa> {

	private final ModelPart root;
	private final ModelPart jerboa;
	private final ModelPart flippity_flop_thingy;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart head;
	private final ModelPart right_ear;
	private final ModelPart left_ear;
	private final ModelPart chest;
	private final ModelPart right_leg;
	private final ModelPart right_lower_leg;
	private final ModelPart right_foot;
	private final ModelPart left_leg;
	private final ModelPart left_lower_leg;
	private final ModelPart left_foot;

    public JerboaModel(ModelPart root) {
		this.root = root.getChild("root");
		this.jerboa = this.root.getChild("jerboa");
		this.flippity_flop_thingy = this.jerboa.getChild("flippity_flop_thingy");
		this.body = this.flippity_flop_thingy.getChild("body");
		this.tail = this.body.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.head = this.body.getChild("head");
		this.right_ear = this.head.getChild("right_ear");
		this.left_ear = this.head.getChild("left_ear");
		this.chest = this.body.getChild("chest");
		this.right_leg = this.flippity_flop_thingy.getChild("right_leg");
		this.right_lower_leg = this.right_leg.getChild("right_lower_leg");
		this.right_foot = this.right_lower_leg.getChild("right_foot");
		this.left_leg = this.flippity_flop_thingy.getChild("left_leg");
		this.left_lower_leg = this.left_leg.getChild("left_lower_leg");
		this.left_foot = this.left_lower_leg.getChild("left_foot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition jerboa = root.addOrReplaceChild("jerboa", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flippity_flop_thingy = jerboa.addOrReplaceChild("flippity_flop_thingy", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition body = flippity_flop_thingy.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 25).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(22, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, -2.0F));

		body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(18, 22).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, -2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 10).addBox(-1.5F, -1.5029F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 8).addBox(-0.5F, 0.4971F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -3.0F));

		head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, -2.9971F, 0.1309F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -1.5029F, -1.1309F));

		head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(20, 4).addBox(-2.0F, -3.0F, 0.1309F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -1.5029F, -1.1309F));

		body.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg = flippity_flop_thingy.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(20, 20).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, 1.0F));

		PartDefinition right_lower_leg = right_leg.addOrReplaceChild("right_lower_leg", CubeListBuilder.create().texOffs(16, 20).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		right_lower_leg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(18, 15).addBox(-1.5F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition left_leg = flippity_flop_thingy.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 20).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 1.0F));

		PartDefinition left_lower_leg = left_leg.addOrReplaceChild("left_lower_leg", CubeListBuilder.create().texOffs(14, 20).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		left_lower_leg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(14, 18).addBox(-0.5F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    
    @Override
    public void setupAnim(Jerboa entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	this.root().getAllParts().forEach(ModelPart::resetPose);
    	entity.idleAnimationState.animateIdle(this, JerboaAnimation.IDLE, ageInTicks, limbSwingAmount, 2.5F, entity.runAnimationState, entity.sitAnimationState);
    	entity.sitAnimationState.animate(this, JerboaAnimation.SIT, ageInTicks);
    	entity.rollingAnimationState.animate(this, JerboaAnimation.ROLLING, ageInTicks);
    	entity.funnyAnimationState.animate(this, JerboaAnimation.FUNNY, ageInTicks);
    	this.animate(entity.flipAnimationState, JerboaAnimation.FLIP, ageInTicks);
    	this.animate(entity.backflipAnimationState, JerboaAnimation.BACKFLIP, ageInTicks);
    	
    	SmoothAnimationState.animateWalk(this, JerboaAnimation.WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F, entity.runAnimationState, entity.sitAnimationState);
    	entity.runAnimationState.animateWalkWithFactor(this, JerboaAnimation.RUN, limbSwing, limbSwingAmount, 1.5F, 1.5F);
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