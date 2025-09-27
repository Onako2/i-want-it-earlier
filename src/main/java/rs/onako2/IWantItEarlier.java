package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.onako2.entity.AbstractNautilusEntity;

public class IWantItEarlier implements ModInitializer {
    public static final String MOD_ID = "iwie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<AbstractNautilusEntity> NAUTILUS_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifiers.NAUTILUS_ID,
            EntityType.Builder.create(AbstractNautilusEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.8F, 0.8F)
                    .eyeHeight(0.4F)
                    .maxTrackingRange(24)
                    .makeFireImmune()
                    .dropsNothing()
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifiers.NAUTILUS_ID))
    );

    public static final Block TEST = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.TEST_ID), AbstractBlock.Settings.create().strength(4.0f));

    public static final Item NAUTILUS_SPAWN_EGG = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.NAUTILUS_SPAWN_EGG), settings -> new SpawnEggItem(NAUTILUS_ENTITY, settings));

    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(NAUTILUS_SPAWN_EGG);
            })
            .build();

    @Override
    public void onInitialize() {

        LOGGER.info("Initializing items and blocks from next update!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "main"), IWIE);

        ModRegistry.registerItems();

        FabricDefaultAttributeRegistry.register(NAUTILUS_ENTITY, AbstractNautilusEntity.createAbstractNautilusAttributes());
    }
}
