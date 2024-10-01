package rs.onako2.iwie.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.ClientInit;

public class CreakingEntityRenderer extends MobEntityRenderer<CreakingEntity, CreakingModel> {

    public CreakingEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CreakingModel(context.getPart(ClientInit.MODEL_CREAKING_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(CreakingEntity entity) {
        return Identifier.of("iwie", "textures/entity/creaking/creaking.png");
    }
}