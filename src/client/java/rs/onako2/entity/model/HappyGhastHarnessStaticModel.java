package rs.onako2.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.state.HappyGhastRenderState;

public class HappyGhastHarnessStaticModel extends EntityModel<HappyGhastRenderState> {

    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of(IWantItEarlier.MOD_ID, "happy_ghast_harness_static"), "main");
    private final ModelPart bone;
    private final ModelPart goggles;

    public HappyGhastHarnessStaticModel(ModelPart modelPart) {
        super(modelPart);
        this.bone = root.getChild("bone");
        this.goggles = root.getChild("goggles");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData goggles = partdefinition.addChild("goggles", ModelPartBuilder.create().uv(0, 34).cuboid(-9.25F, 0.0F, -6.4526F, 18.5F, 8.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.0F, -3.0F));

        ModelPartData bone = partdefinition.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-8.5F, -16.5F, -8.5F, 17.0F, 17.0F, 17.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(HappyGhastRenderState happyGhastRenderState) {
        if (happyGhastRenderState.hasPassenger) {
            goggles.setAngles(0.0F, 0.0F, 0.0F);
        } else {
            goggles.setAngles(-40.0F, 0.0F, 0.0F);
        }
    }
}
