package rs.onako2.iwie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import rs.onako2.iwie.render.NautilusEntityRenderer;

public class IWantItEarlierClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(IWantItEarlier.NAUTILUS_ENTITY, NautilusEntityRenderer::new);
    }
}
