package rs.onako2.iwie.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import rs.onako2.iwie.state.AbstractNautilusEntityRenderState;

import static rs.onako2.iwie.render.AbstractNautilusEntityRenderer.TEXTURE;

public class AbstractNautilusEntityModel extends EntityModel<AbstractNautilusEntityRenderState> {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(TEXTURE, "main");
    private final ModelPart shell;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart tentaclesDown;
    private final ModelPart tentaclesUp;

    public AbstractNautilusEntityModel(ModelPart root) {
        super(root);
        this.shell = root.getChild("shell");
        this.head = root.getChild("head");
        this.mouth = root.getChild("mouth");
        this.tentaclesDown = root.getChild("tentacles_down");
        this.tentaclesUp = root.getChild("tentacles_up");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData ModelData = new ModelData();
        ModelPartData ModelPartData = ModelData.getRoot();

        ModelPartData shell = ModelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -18.0F, 0.0F, 14.0F, 18.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 58).cuboid(-14.0F, -2.0F, 16.0F, 14.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 66).cuboid(-14.0F, -8.0F, 12.0F, 14.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(8.0F, 24.0F, -8.0F));

        ModelPartData head = ModelPartData.addChild("head", ModelPartBuilder.create().uv(9, 34).cuboid(-5.0F, -8.0F, 4.0F, 12.0F, 8.0F, 11.0F, new Dilation(0.0F))
                .uv(32, 37).cuboid(-5.0F, -8.0F, 13.0F, 12.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData mouth = ModelPartData.addChild("mouth", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -6.0F, 13.0F, 6.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData tentaclesDown = ModelPartData.addChild("tentacles_down", ModelPartBuilder.create().uv(34, 54).cuboid(-5.0F, -4.0F, 15.0F, 12.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData tentaclesUp = ModelPartData.addChild("tentacles_up", ModelPartBuilder.create().uv(34, 62).cuboid(-5.0F, -8.0F, 15.0F, 12.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(ModelData, 128, 128);
    }

	@Override
	public void setAngles(AbstractNautilusEntityRenderState renderState) {

	}
}