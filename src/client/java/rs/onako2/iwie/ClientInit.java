package rs.onako2.iwie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.entity.CreakingEntityRenderer;
import rs.onako2.iwie.entity.CreakingModel;

import java.util.concurrent.CompletableFuture;

public class ClientInit implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CREAKING_LAYER = new EntityModelLayer(Identifier.of("iwie", "creaking"), "main");


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_SHORT_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_MOSS_CARPET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_OAK_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_OAK_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_HANGING_MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Init.PALE_HANGING_MOSS_PLANT, RenderLayer.getCutout());


        MinecraftClient client = MinecraftClient.getInstance();


        CompletableFuture.runAsync(() -> {
            while (client.getBlockColors() == null || client.getWindow() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            BlockColors blockColors = client.getBlockColors();
            blockColors.registerColorProvider(new PaleLeavesColorProvider(), Init.PALE_OAK_LEAVES);

            client.getWindow().setTitle("https://modrinth.com/mod/i-want-it-earlier");
        });

        EntityRendererRegistry.register(Init.CREAKING, CreakingEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_CREAKING_LAYER, CreakingModel::getTexturedModelData);

    }
}