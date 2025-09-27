package rs.onako2.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import rs.onako2.state.AbstractNautilusEntityRenderState;

public class AbstractNautilusEntityModel extends EntityModel<AbstractNautilusEntityRenderState> {

    public AbstractNautilusEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        return SquidEntityModel.getTexturedModelData();
    }

    public void setAngles(AbstractNautilusEntityRenderState renderState) {
        super.setAngles(renderState);
    }
}