package rs.onako2.iwie.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.model.NautilusEntityModel;
import rs.onako2.iwie.model.NautilusSaddleModel;
import rs.onako2.iwie.state.NautilusEntityRenderState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NautilusSaddleRenderer extends FeatureRenderer<NautilusEntityRenderState, NautilusEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/saddle.png");
    private final EntityModel<NautilusEntityRenderState> saddle;

    public NautilusSaddleRenderer(FeatureRendererContext<NautilusEntityRenderState, NautilusEntityModel> context) {
        super(context);
        this.saddle = new NautilusSaddleModel(NautilusSaddleModel.getTexturedModelData().createModel());
    }

    // 1.21.9 and above
    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, NautilusEntityRenderState state, float limbAngle, float limbDistance) {
        if (state.isSaddled) {
            queue.submitModelPart(this.saddle.getRootPart(), matrices, this.saddle.getLayer(TEXTURE), light, 0, null, false, false, -1, null, 0);
        }
    }

    // 1.21.8 and below, render method_4199
    public void method_4199(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, EntityRenderState state, float limbAngle, float limbDistance) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (((NautilusEntityRenderState) state).isSaddled) {
            Method getBufferMethod = VertexConsumerProvider.class.getMethod("getBuffer", RenderLayer.class);
            VertexConsumer vertexConsumer = (VertexConsumer) getBufferMethod.invoke(vertexConsumers, RenderLayer.getEntityAlpha(TEXTURE));
            this.saddle.render(matrices, vertexConsumer, light, light, -1);
        }
    }
}