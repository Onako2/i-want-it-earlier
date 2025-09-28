package rs.onako2.iwie.render;

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.entity.AbstractNautilusEntity;
import rs.onako2.iwie.model.BabyNautilusEntityModel;
import rs.onako2.iwie.model.NautilusEntityModel;
import rs.onako2.iwie.state.NautilusEntityRenderState;

public class NautilusEntityRenderer extends AgeableMobEntityRenderer<AbstractNautilusEntity, NautilusEntityRenderState, NautilusEntityModel> {
    public static final Identifier TEXTURE_ADULT = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/nautilus.png");
    public static final Identifier TEXTURE_CHILD = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/baby.png");

    public NautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new NautilusEntityModel(NautilusEntityModel.createBodyLayer().createModel()), new BabyNautilusEntityModel(BabyNautilusEntityModel.createBodyLayer().createModel()), 1.5F);
    }

    @Override
    public Identifier getTexture(NautilusEntityRenderState state) {
        return state.isBaby ? TEXTURE_CHILD : TEXTURE_ADULT;
    }

    @Override
    public void updateRenderState(AbstractNautilusEntity entity, NautilusEntityRenderState state, float f) {
        super.updateRenderState(entity, state, f);

        state.hasPassenger = entity.hasPassengers();
        state.isBaby = entity.isBaby();
    }

    public NautilusEntityRenderState createRenderState() {
        return new NautilusEntityRenderState();
    }
}