package rs.onako2.iwie.render;

import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.IWantItEarlier;
import rs.onako2.iwie.entity.ZombieNautilusEntity;
import rs.onako2.iwie.model.ZombieNautilusEntityModel;
import rs.onako2.iwie.state.ZombieNautilusEntityRenderState;

public class ZombieNautilusEntityRenderer extends AgeableMobEntityRenderer<ZombieNautilusEntity, ZombieNautilusEntityRenderState, ZombieNautilusEntityModel> {
    public static final Identifier TEXTURE_ADULT = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/zombie_nautilus/zombie_nautilus.png");

    public ZombieNautilusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieNautilusEntityModel(ZombieNautilusEntityModel.getTexturedModelData().createModel()), new ZombieNautilusEntityModel(ZombieNautilusEntityModel.getTexturedModelData().createModel()), 0.5F);
    }

    @Override
    public ZombieNautilusEntityRenderState createRenderState() {
        return new ZombieNautilusEntityRenderState();
    }

    @Override
    public Identifier getTexture(ZombieNautilusEntityRenderState state) {
        return TEXTURE_ADULT;
    }

    @Override
    public void updateRenderState(ZombieNautilusEntity entity, ZombieNautilusEntityRenderState state, float f) {
        super.updateRenderState(entity, state, f);

        state.hasPassenger = entity.hasPassengers();
        state.isBaby = entity.isBaby();
    }
}