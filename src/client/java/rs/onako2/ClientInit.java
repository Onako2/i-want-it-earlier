package rs.onako2;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientInit implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(Init.COPPER_GRID, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Init.EXPOSED_COPPER_GRID, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Init.OXIDIZED_COPPER_GRID, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Init.WEATHERED_COPPER_GRID, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Init.TRIAL_SPAWNER, RenderLayer.getCutout());
	}
}