package rs.onako2.render;

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.AbstractNautilusEntity;
import rs.onako2.model.AbstractNautilusEntityModel;
import rs.onako2.state.AbstractNautilusEntityRenderState;

public class AbstractNautilusEntityRenderer extends AgeableMobEntityRenderer<AbstractNautilusEntity, AbstractNautilusEntityRenderState, AbstractNautilusEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/nautilus.png");

    public AbstractNautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AbstractNautilusEntityModel(context.getPart(EntityModelLayers.SQUID)), new AbstractNautilusEntityModel(context.getPart(EntityModelLayers.SQUID)), 1.5F);
    }

    @Override
    public Identifier getTexture(AbstractNautilusEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public void updateRenderState(AbstractNautilusEntity AbstractNautilusEntity, AbstractNautilusEntityRenderState AbstractNautilusEntityRenderState, float f) {
        super.updateRenderState(AbstractNautilusEntity, AbstractNautilusEntityRenderState, f);

        AbstractNautilusEntityRenderState.hasPassenger = AbstractNautilusEntity.hasPassengers();
    }

    public AbstractNautilusEntityRenderState createRenderState() {
        return new AbstractNautilusEntityRenderState();
    }
}