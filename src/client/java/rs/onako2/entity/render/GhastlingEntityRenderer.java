package rs.onako2.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.HappyGhastEntity;
import rs.onako2.entity.model.HappyGhastEntityModel;
import rs.onako2.entity.state.HappyGhastRenderState;

public class GhastlingEntityRenderer extends MobEntityRenderer<HappyGhastEntity, HappyGhastRenderState, HappyGhastEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(IWantItEarlier.MOD_ID, "textures/entity/happy_ghast/happy_ghast.png");

    public GhastlingEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HappyGhastEntityModel(context.getPart(EntityModelLayers.GHAST)), 1.5F);
        this.addFeature(new HappyGhastHarnessAdaptRenderer(this));
        this.addFeature(new HappyGhastHarnessStaticRenderer(this));
    }

    @Override
    public Identifier getTexture(HappyGhastRenderState state) {
        return TEXTURE;
    }

    public HappyGhastRenderState createRenderState() {
        return new HappyGhastRenderState();
    }
}
