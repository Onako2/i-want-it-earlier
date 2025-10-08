package rs.onako2.iwie.render;

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.entity.NautilusEntity;
import rs.onako2.iwie.model.BabyNautilusEntityModel;
import rs.onako2.iwie.model.NautilusEntityModel;
import rs.onako2.iwie.state.NautilusEntityRenderState;

public class NautilusEntityRenderer extends AgeableMobEntityRenderer<NautilusEntity, NautilusEntityRenderState, NautilusEntityModel> {
    public static final Identifier TEXTURE_ADULT = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/nautilus.png");
    public static final Identifier TEXTURE_CHILD = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/nautilus/baby.png");

    public NautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new NautilusEntityModel(NautilusEntityModel.createBodyLayer().createModel()), new BabyNautilusEntityModel(BabyNautilusEntityModel.createBodyLayer().createModel()), 0.5F);
        this.addFeature(new NautilusSaddleRenderer(this));
    }

    @Override
    public Identifier getTexture(NautilusEntityRenderState state) {
        return state.isBaby ? TEXTURE_CHILD : TEXTURE_ADULT;
    }

    @Override
    public void updateRenderState(NautilusEntity entity, NautilusEntityRenderState state, float f) {
        super.updateRenderState(entity, state, f);

        state.hasPassenger = entity.hasPassengers();
        state.isBaby = entity.isBaby();
        state.isSaddled = entity.isSaddled();
    }

    @Override
    public NautilusEntityRenderState createRenderState() {
        return new NautilusEntityRenderState();
    }
}