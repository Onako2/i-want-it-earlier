package rs.onako2;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.apache.commons.lang3.SystemUtils;
import rs.onako2.entity.HappyGhastEntity;
import rs.onako2.entity.model.HappyGhastEntityModel;
import rs.onako2.entity.model.HappyGhastHarnessAdaptModel;
import rs.onako2.entity.model.HappyGhastHarnessStaticModel;
import rs.onako2.entity.render.HappyGhastEntityRenderer;
import rs.onako2.network.HappyGhastInformationPayload;

import java.util.concurrent.CompletableFuture;

public class IWantItEarlierClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_HAPPY_GHAST_LAYER = new EntityModelLayer(Identifiers.HAPPY_GHAST_ID, "main");
    public static final EntityModelLayer MODEL_HAPPY_GHAST_HARNESS_STATIC = new EntityModelLayer(Identifiers.HAPPY_GHAST_ID, "static");
    public static final EntityModelLayer MODEL_HAPPY_GHAST_HARNESS_ADAPT = new EntityModelLayer(Identifiers.HAPPY_GHAST_ID, "adapt");

    @Override
    public void onInitializeClient() {
        MinecraftClient client = MinecraftClient.getInstance();

        CompletableFuture.runAsync(() -> {
            while (client.getBlockColors() == null || client.getWindow() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (SystemUtils.IS_OS_WINDOWS) {
                client.getWindow().setTitle("https://modrinth.com/mod/i-want-it-earlier");
            }
        });

        EntityRendererRegistry.register(IWantItEarlier.HAPPY_GHAST, HappyGhastEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL_HAPPY_GHAST_LAYER, HappyGhastEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MODEL_HAPPY_GHAST_HARNESS_ADAPT, HappyGhastHarnessAdaptModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MODEL_HAPPY_GHAST_HARNESS_STATIC, HappyGhastHarnessStaticModel::getTexturedModelData);

        ClientPlayNetworking.registerGlobalReceiver(HappyGhastInformationPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                context.client().world.getEntities().forEach(entity -> {
                    if (entity.getUuid().equals(payload.uuid()) && entity instanceof HappyGhastEntity happyGhastEntity) {
                        happyGhastEntity.harnessColor = payload.color();
                        happyGhastEntity.hasPassenger = payload.riding();
                    }
                });
            });
        });
    }
}
