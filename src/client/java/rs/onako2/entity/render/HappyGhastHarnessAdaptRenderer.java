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
import rs.onako2.entity.model.HappyGhastHarnessAdaptModel;
import rs.onako2.entity.state.HappyGhastRenderState;

public class HappyGhastHarnessAdaptRenderer extends FeatureRenderer<HappyGhastRenderState, HappyGhastEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/happy_ghast/harness_adapt.png");
    private final EntityModel<HappyGhastRenderState> harness;

    public HappyGhastHarnessAdaptRenderer(FeatureRendererContext<HappyGhastRenderState, HappyGhastEntityModel> context) {
        super(context);
        this.harness = new HappyGhastHarnessAdaptModel(HappyGhastHarnessAdaptModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HappyGhastRenderState state, float limbAngle, float limbDistance) {
        int color = state.harnessColor; // Assuming harnessColor is an integer representing the color
        if (color == -1) {
            return;
        }
        matrices.scale(4.5f, 4.5f, 4.5f);
        matrices.translate(0.0f, -1.05f, 0.0f);
        this.harness.setAngles(state);
        this.harness.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityAlpha(TEXTURE)), light, light, color);
    }
}
