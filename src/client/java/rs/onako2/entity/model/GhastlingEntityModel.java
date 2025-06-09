package rs.onako2.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.util.math.MathHelper;
import rs.onako2.entity.state.HappyGhastRenderState;

public class GhastlingEntityModel extends EntityModel<HappyGhastRenderState> {
    private final ModelPart[] tentacles = new ModelPart[9];

    public GhastlingEntityModel(ModelPart modelPart) {
        super(modelPart);

        for (int i = 0; i < this.tentacles.length; i++) {
            this.tentacles[i] = modelPart.getChild(getTentacleName(i));
        }
    }

    private static String getTentacleName(int index) {
        return "tentacle" + index;
    }

    public static TexturedModelData getTexturedModelData() {
        return GhastEntityModel.getTexturedModelData();
    }

    public void setAngles(HappyGhastRenderState happyGhastRenderState) {
        super.setAngles(happyGhastRenderState);

        for (int i = 0; i < this.tentacles.length; i++) {
            this.tentacles[i].pitch = 0.2F * MathHelper.sin(happyGhastRenderState.age * 0.3F + i) + 0.4F;
        }
    }
}
