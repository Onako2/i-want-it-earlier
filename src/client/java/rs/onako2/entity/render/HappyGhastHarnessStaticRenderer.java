package rs.onako2.entity.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.model.HappyGhastEntityModel;
import rs.onako2.entity.model.HappyGhastHarnessStaticModel;
import rs.onako2.entity.state.HappyGhastRenderState;

public class HappyGhastHarnessStaticRenderer extends FeatureRenderer<HappyGhastRenderState, HappyGhastEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/happy_static/harness_static.png");
    private final EntityModel<HappyGhastRenderState> harness;

    public HappyGhastHarnessStaticRenderer(FeatureRendererContext<HappyGhastRenderState, HappyGhastEntityModel> context) {
        super(context);
        this.harness = new HappyGhastHarnessStaticModel(HappyGhastHarnessStaticModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HappyGhastRenderState state, float limbAngle, float limbDistance) {
        int color = state.harnessColor; // Assuming harnessColor is an integer representing the color
        if (color == -1) {
            return;
        }
        this.harness.setAngles(state);
        this.harness.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityAlpha(TEXTURE)), light, light, color);
    }
}
