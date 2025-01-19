package com.internals.halcyonhorizons.client.model;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.internals.halcyonhorizons.server.entity.living.RuffianSnatcher;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import javax.swing.text.html.parser.Entity;

public class RuffianSnatcherModel<T extends RuffianSnatcher> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("halcyonhorizons", "ruffian_snatcher_1"), "main");
    private final ModelPart ruffian;
    private final ModelPart rz_leg;
    private final ModelPart l_leg;
    private final ModelPart upper_body;
    private final ModelPart torso;
    private final ModelPart cape;
    private final ModelPart r_arm;
    private final ModelPart r_hand;
    private final ModelPart l_arm;
    private final ModelPart l_hand;
    private final ModelPart head;
    private final ModelPart hair;
    private final ModelPart longhair;
    private final ModelPart hair_ends;

    public ruffian_snatcher_1(ModelPart root) {
        this.ruffian = root.getChild("ruffian");
        this.rz_leg = this.ruffian.getChild("rz_leg");
        this.l_leg = this.ruffian.getChild("l_leg");
        this.upper_body = this.ruffian.getChild("upper_body");
        this.torso = this.upper_body.getChild("torso");
        this.cape = this.torso.getChild("cape");
        this.r_arm = this.upper_body.getChild("r_arm");
        this.r_hand = this.r_arm.getChild("r_hand");
        this.l_arm = this.upper_body.getChild("l_arm");
        this.l_hand = this.l_arm.getChild("l_hand");
        this.head = this.upper_body.getChild("head");
        this.hair = this.head.getChild("hair");
        this.longhair = this.hair.getChild("longhair");
        this.hair_ends = this.longhair.getChild("hair_ends");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition ruffian = partdefinition.addOrReplaceChild("ruffian", CubeListBuilder.create(), PartPose.offset(1.0F, 24.0F, 0.0F));

        PartDefinition rz_leg = ruffian.addOrReplaceChild("rz_leg", CubeListBuilder.create().texOffs(39, 0).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -6.0F, 0.0F));

        PartDefinition l_leg = ruffian.addOrReplaceChild("l_leg", CubeListBuilder.create().texOffs(0, 42).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -6.0F, 0.0F));

        PartDefinition upper_body = ruffian.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-1.0F, -9.0F, 0.0F));

        PartDefinition torso = upper_body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(33, 24).addBox(-4.0F, -9.0F, -2.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 0.0F));

        PartDefinition cape = torso.addOrReplaceChild("cape", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 1.0F));

        PartDefinition cube_r1 = cape.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-5.0F, -9.0F, -3.0F, 8.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.4F, 0.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition r_arm = upper_body.addOrReplaceChild("r_arm", CubeListBuilder.create().texOffs(49, 45).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 0.5F));

        PartDefinition r_hand = r_arm.addOrReplaceChild("r_hand", CubeListBuilder.create().texOffs(32, 45).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition l_arm = upper_body.addOrReplaceChild("l_arm", CubeListBuilder.create().texOffs(15, 52).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -5.0F, 0.5F));

        PartDefinition l_hand = l_arm.addOrReplaceChild("l_hand", CubeListBuilder.create().texOffs(15, 42).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, 0.0F));

        PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-5.0F, -7.0F, -4.0F, 9.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -7.0F, 0.5F));

        PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(33, 37).addBox(-13.0F, -7.0F, -3.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 24).addBox(-7.0F, -7.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(54, 0).addBox(-5.0F, -7.0F, -3.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -1.0F, -2.5F));

        PartDefinition longhair = hair.addOrReplaceChild("longhair", CubeListBuilder.create(), PartPose.offset(-6.0F, -5.5F, 2.0F));

        PartDefinition cube_r2 = longhair.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -7.0F, -5.0F, 11.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 4.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition hair_ends = longhair.addOrReplaceChild("hair_ends", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, 0.0F, -2.0F, 13.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(33, 13).addBox(-5.5F, 6.0F, -2.0F, 11.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 5.5F, 1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        ruffian.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
