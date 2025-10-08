package rs.onako2.iwie.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.model.NautilusEntityModel;
import rs.onako2.iwie.model.NautilusSaddleModel;
import rs.onako2.iwie.state.NautilusEntityRenderState;

public class NautilusSaddleRenderer extends FeatureRenderer<NautilusEntityRenderState, NautilusEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/saddle.png");
    private final EntityModel<NautilusEntityRenderState> saddle;

    public NautilusSaddleRenderer(FeatureRendererContext<NautilusEntityRenderState, NautilusEntityModel> context) {
        super(context);
        this.saddle = new NautilusSaddleModel(NautilusSaddleModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, NautilusEntityRenderState state, float limbAngle, float limbDistance) {
        if (state.isSaddled) {
            this.saddle.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityAlpha(TEXTURE)), light, light, -1);
        }
    }
}