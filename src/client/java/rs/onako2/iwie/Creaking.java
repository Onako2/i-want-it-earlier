package rs.onako2.iwie;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
// TODO: Implement the Creaking entity
public class Creaking extends EntityModel<LivingEntity> {
    private final ModelPart legs;
    private final ModelPart arms;
    private final ModelPart body;
    private final ModelPart part2D;

    public Creaking(ModelPart root) {
        this.legs = root.getChild("legs");
        this.arms = root.getChild("arms");
        this.body = root.getChild("body");
        this.part2D = root.getChild("part2D");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData legs = modelPartData.addChild("legs", ModelPartBuilder.create().uv(22, 18).cuboid(-7.0F, -19.0F, -2.0F, 3.0F, 20.0F, 3.0F, new Dilation(0.0F))
                .uv(22, 18).cuboid(-7.0F, -19.0F, 2.0F, 3.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 23.0F, 0.0F));

        ModelPartData arms = modelPartData.addChild("arms", ModelPartBuilder.create().uv(34, 0).cuboid(-1.0F, -22.0F, -12.0F, 3.0F, 19.0F, 3.0F, new Dilation(0.0F))
                .uv(34, 0).cuboid(-1.0F, -22.0F, 3.0F, 3.0F, 18.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 16.0F, 4.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -21.0F, -14.0F, 5.0F, 6.0F, 12.0F, new Dilation(0.0F))
                .uv(34, 22).cuboid(-2.0F, -15.0F, -8.0F, 4.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(-2.0F, -31.0F, -14.0F, 5.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 16.0F, 9.0F));

        ModelPartData part2D = modelPartData.addChild("part2D", ModelPartBuilder.create().uv(26, 45).cuboid(-1.0F, 0.0F, -4.0F, 7.0F, 0.0F, 5.0F, new Dilation(0.0F))
                .uv(-1, 41).cuboid(-2.0F, 0.0F, 2.0F, 9.0F, 0.0F, 5.0F, new Dilation(0.0F))
                .uv(34, 35).cuboid(0.0F, -43.0F, -5.0F, 5.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(46, 0).cuboid(2.0F, -39.0F, 1.0F, 0.0F, 10.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 46).cuboid(3.0F, -44.0F, -9.0F, 0.0F, 13.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 46).cuboid(1.0F, -33.0F, 7.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 36).cuboid(1.0F, -12.0F, 7.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(46, 15).cuboid(1.0F, -21.0F, -2.0F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(1.0F, -11.0F, -8.0F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        legs.render(matrices, vertices, light, overlay, color);
        arms.render(matrices, vertices, light, overlay, color);
        body.render(matrices, vertices, light, overlay, color);
        part2D.render(matrices, vertices, light, overlay, color);
    }
}
