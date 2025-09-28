package rs.onako2.iwie.render;

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.entity.AbstractNautilusEntity;
import rs.onako2.iwie.model.AbstractNautilusEntityModel;
import rs.onako2.iwie.state.AbstractNautilusEntityRenderState;

public class AbstractNautilusEntityRenderer extends AgeableMobEntityRenderer<AbstractNautilusEntity, AbstractNautilusEntityRenderState, AbstractNautilusEntityModel> {
    public static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/nautilus.png");

    public AbstractNautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AbstractNautilusEntityModel(AbstractNautilusEntityModel.createBodyLayer().createModel()), new AbstractNautilusEntityModel(AbstractNautilusEntityModel.createBodyLayer().createModel()), 1.5F);
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