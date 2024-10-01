package rs.onako2.iwie.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class CreakingModel extends EntityModel<CreakingEntity> {


    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart body;

    public CreakingModel(ModelPart root) {
        this.head = root.getChild("head");
        this.rightLeg = root.getChild("rightLeg");
        this.leftLeg = root.getChild("leftLeg");
        this.leftArm = root.getChild("leftArm");
        this.rightArm = root.getChild("rightArm");
        this.body = root.getChild("body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(-3.25F, -5.375F, -0.5F));

        ModelPartData headMain_r1 = head.addChild("headMain_r1", ModelPartBuilder.create().uv(0, 18).cuboid(-3.5F, -19.5F, -5.75F, 5.0F, 12.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 46).cuboid(1.5F, -22.5F, -9.75F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F))
                .uv(46, 0).cuboid(1.5F, -17.5F, 0.25F, 0.0F, 10.0F, 4.0F, new Dilation(0.0F))
                .uv(34, 35).cuboid(-3.5F, -23.5F, -5.75F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(2.75F, 7.875F, 0.25F, 0.0F, 1.5708F, 0.0F));

        ModelPartData rightLeg = modelPartData.addChild("rightLeg", ModelPartBuilder.create(), ModelTransform.pivot(2.75F, 8.0F, -0.75F));

        ModelPartData rightArm_r1 = rightLeg.addChild("rightArm_r1", ModelPartBuilder.create().uv(22, 18).cuboid(-1.5F, 5.5F, 1.25F, 3.0F, 16.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.25F, -5.5F, 0.5F, 0.0F, 1.5708F, 0.0F));

        ModelPartData right_foot_r1 = rightLeg.addChild("right_foot_r1", ModelPartBuilder.create().uv(-1, 41).cuboid(-7.5F, 21.5F, 1.25F, 9.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.25F, -5.5F, -3.5F, 0.0F, 1.5708F, 0.0F));

        ModelPartData leftLeg = modelPartData.addChild("leftLeg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 4.0F, -0.25F));

        ModelPartData left_leg_top_r1 = leftLeg.addChild("left_leg_top_r1", ModelPartBuilder.create().uv(46, 15).cuboid(-1.5F, 0.5F, -2.75F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(22, 18).cuboid(-1.5F, 1.5F, -2.75F, 3.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -1.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData left_foot_r1 = leftLeg.addChild("left_foot_r1", ModelPartBuilder.create().uv(26, 45).cuboid(-5.5F, 21.5F, -4.75F, 7.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, -1.5F, -2.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData leftArm = modelPartData.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(-7.75F, -4.0F, -0.25F));

        ModelPartData arm_left_bottom_r1 = leftArm.addChild("arm_left_bottom_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 10.5F, -8.75F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(7.25F, 6.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(34, 0).cuboid(-1.5F, 0.5F, -1.5F, 3.0F, 19.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData rightArm = modelPartData.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(7.25F, -4.0F, -0.25F));

        ModelPartData arm_right_bottom_r1 = rightArm.addChild("arm_right_bottom_r1", ModelPartBuilder.create().uv(0, 36).cuboid(-1.5F, 9.5F, 6.25F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(8, 46).cuboid(-1.5F, -11.5F, 6.25F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-7.75F, 6.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData rightArm_r2 = rightArm.addChild("rightArm_r2", ModelPartBuilder.create().uv(34, 0).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 18.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(1.25F, 1.25F, 0.5F));

        ModelPartData body_bottom_r1 = body.addChild("body_bottom_r1", ModelPartBuilder.create().uv(34, 22).cuboid(-2.5F, -1.5F, 0.25F, 4.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.5F, -7.5F, -5.75F, 5.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-1.75F, 1.25F, -0.75F, 0.0F, 1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        head.render(matrices, vertices, light, overlay, color);
        rightLeg.render(matrices, vertices, light, overlay, color);
        leftLeg.render(matrices, vertices, light, overlay, color);
        leftArm.render(matrices, vertices, light, overlay, color);
        rightArm.render(matrices, vertices, light, overlay, color);
        body.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public void setAngles(CreakingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // animatie the arms when the entity is moving
        this.rightArm.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.1F * limbDistance;
        this.leftArm.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.1F * limbDistance;
        // animate the legs when the entity is moving
        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.1F * limbDistance;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.1F * limbDistance;

        // animate the head when the entity is moving
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
    }


}
