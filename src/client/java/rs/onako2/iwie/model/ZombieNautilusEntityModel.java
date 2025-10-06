package rs.onako2.iwie.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import rs.onako2.iwie.state.ZombieNautilusEntityRenderState;

public class ZombieNautilusEntityModel extends EntityModel<ZombieNautilusEntityRenderState> {
    private final ModelPart shell;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart tentacles_down;
    private final ModelPart tentacles_up;

    public ZombieNautilusEntityModel(ModelPart root) {
        super(root);
        this.shell = root.getChild("shell");
        this.head = root.getChild("head");
        this.mouth = root.getChild("mouth");
        this.tentacles_down = root.getChild("tentacles_down");
        this.tentacles_up = root.getChild("tentacles_up");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData shell = modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -13.5F, -13.5F, 14.0F, 18.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 58).cuboid(-7.0F, 2.5F, 2.5F, 14.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(-7.0F, -3.5F, -1.5F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(5.0F, -3.5F, -1.5F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 19.5F, 5.5F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(12, 34).cuboid(-5.0F, -4.0F, -7.25F, 10.0F, 8.0F, 11.0F, new Dilation(0.0F))
                .uv(44, 37).cuboid(-5.0F, -4.0F, 1.75F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, 11.25F));

        ModelPartData mouth = modelPartData.addChild("mouth", ModelPartBuilder.create().uv(44, 1).cuboid(-3.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, 15.0F));

        ModelPartData tentacles_down = modelPartData.addChild("tentacles_down", ModelPartBuilder.create().uv(0, 38).cuboid(-5.0F, -2.0F, -1.5F, 10.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 16.5F));

        ModelPartData tentacles_up = modelPartData.addChild("tentacles_up", ModelPartBuilder.create().uv(36, 57).cuboid(-5.0F, -2.0F, -1.5F, 10.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 18.0F, 16.5F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ZombieNautilusEntityRenderState renderState) {
        // TODO: IMPLEMENT
    }
}