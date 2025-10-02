package rs.onako2.iwie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.SystemUtils;
import rs.onako2.iwie.render.NautilusEntityRenderer;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

public class IWantItEarlierClient implements ClientModInitializer {

    public static final MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(IWantItEarlier.NAUTILUS_ENTITY, NautilusEntityRenderer::new);

        CompletableFuture.runAsync(() -> {
            while (client.getWindow() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (SystemUtils.IS_OS_WINDOWS) {
                File config = new File("promotion.iwie");
                boolean mayPromote = true;
                try {
                    if (config.createNewFile()) {
                        Files.writeString(config.toPath(), "true");
                    } else {
                        mayPromote = Boolean.parseBoolean(Files.readString(config.toPath()));
                    }
                } catch (Exception e) {
                    mayPromote = false;
                }

                if (mayPromote) {
                    client.getWindow().setTitle("https://modrinth.com/mod/i-want-it-earlier");
                }
            }

        });
    }
}
