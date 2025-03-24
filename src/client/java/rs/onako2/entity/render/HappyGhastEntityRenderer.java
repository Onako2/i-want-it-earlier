package rs.onako2.entity.render;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.HappyGhastEntity;
import rs.onako2.entity.model.HappyGhastEntityModel;
import rs.onako2.entity.state.HappyGhastRenderState;
import rs.onako2.network.HappyGhastInformationRequestPayload;

public class HappyGhastEntityRenderer extends MobEntityRenderer<HappyGhastEntity, HappyGhastRenderState, HappyGhastEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/happy_ghast/happy_ghast.png");

    public HappyGhastEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HappyGhastEntityModel(context.getPart(EntityModelLayers.GHAST)), 1.5F);
        this.addFeature(new HappyGhastHarnessAdaptRenderer(this));
        this.addFeature(new HappyGhastHarnessStaticRenderer(this));
    }

    @Override
    public Identifier getTexture(HappyGhastRenderState state) {
        return TEXTURE;
    }

    @Override
    public void updateRenderState(HappyGhastEntity happyGhastEntity, HappyGhastRenderState happyGhastRenderState, float f) {
        super.updateRenderState(happyGhastEntity, happyGhastRenderState, f);

        if (happyGhastEntity.getHarnessColor() == -2) {
            ClientPlayNetworking.send(new HappyGhastInformationRequestPayload(happyGhastEntity.getUuid()));
            happyGhastEntity.harnessColor = -1;
        }

        happyGhastRenderState.harnessColor = happyGhastEntity.getHarnessColor();
        happyGhastRenderState.hasPassenger = happyGhastEntity.hasPassengers();
    }

    public HappyGhastRenderState createRenderState() {
        return new HappyGhastRenderState();
    }
}
