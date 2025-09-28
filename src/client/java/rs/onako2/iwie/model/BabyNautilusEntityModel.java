package rs.onako2.iwie.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;

public class BabyNautilusEntityModel extends NautilusEntityModel {
	private final ModelPart shell;
	private final ModelPart head;
	private final ModelPart tentacles_up;
	private final ModelPart tentacles_down;
	private final ModelPart mouth;

	public BabyNautilusEntityModel(ModelPart root) {
        super(root);
        this.shell = root.getChild("shell");
		this.head = root.getChild("head");
		this.tentacles_up = root.getChild("tentacles_up");
		this.tentacles_down = root.getChild("tentacles_down");
		this.mouth = root.getChild("mouth");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData shell = modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5F, -8.0F, -4.0F, 7.0F, 8.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 24).cuboid(-3.5F, -1.0F, 3.0F, 7.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 27).cuboid(-3.5F, -4.0F, 2.0F, 7.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 15).cuboid(-2.5F, -4.0F, 2.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F))
		.uv(22, 28).cuboid(-2.5F, -4.0F, 6.0F, 5.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData tentacles_up = modelPartData.addChild("tentacles_up", ModelPartBuilder.create().uv(18, 16).cuboid(-2.5F, -4.0F, 7.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData tentacles_down = modelPartData.addChild("tentacles_down", ModelPartBuilder.create().uv(18, 22).cuboid(-2.5F, -2.0F, 7.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData mouth = modelPartData.addChild("mouth", ModelPartBuilder.create().uv(22, 0).cuboid(-1.5F, -3.0F, 9.0F, 3.0F, 2.0F, -1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
}