package rs.onako2.iwie.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import rs.onako2.iwie.state.NautilusEntityRenderState;

public class NautilusSaddleModel extends EntityModel<NautilusEntityRenderState> {
    private final ModelPart shell;

    public NautilusSaddleModel(ModelPart root) {
        super(root);
        this.shell = root.getChild("shell");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData shell = modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -13.5F, -13.5F, 14.0F, 18.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 19.5F, 5.5F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}