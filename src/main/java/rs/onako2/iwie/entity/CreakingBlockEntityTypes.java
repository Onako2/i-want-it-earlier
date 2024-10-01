package rs.onako2.iwie.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rs.onako2.iwie.Init;

public class CreakingBlockEntityTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("iwie", path), blockEntityType);
    }

    public static BlockEntityType<CreakingHeartBlockEntity> CREAKING_HEART_BLOCK = null;

    static {
        CREAKING_HEART_BLOCK = register(
                "creaking_heart",
                BlockEntityType.Builder.create((pos, state) -> new CreakingHeartBlockEntity(CREAKING_HEART_BLOCK, pos, state), Init.CREAKING_HEART).build()
        );
    }

    public static void initialize() {
    }
}
