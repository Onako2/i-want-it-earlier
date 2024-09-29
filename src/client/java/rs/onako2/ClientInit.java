package rs.onako2;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_MOSS_CARPET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_OAK_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_OAK_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_HANGING_MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_HANGING_MOSS_PLANT, RenderLayer.getCutout());
    }
}