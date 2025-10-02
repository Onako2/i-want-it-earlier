package rs.onako2.iwie;

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
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.onako2.iwie.entity.AbstractNautilusEntity;
import rs.onako2.iwie.entity.NautilusEntity;
import rs.onako2.iwie.item.SpearItem;

public class IWantItEarlier implements ModInitializer {
    public static final String MOD_ID = "iwie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<NautilusEntity> NAUTILUS_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifiers.NAUTILUS_ID,
            EntityType.Builder.create(NautilusEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.0F, 1.15F)
                    .eyeHeight(0.4F)
                    .maxTrackingRange(24)
                    .makeFireImmune()
                    .dropsNothing()
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifiers.NAUTILUS_ID))
    );

    public static final Block TEST = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.TEST_ID), AbstractBlock.Settings.create().strength(4.0f));

    public static final Item NAUTILUS_SPAWN_EGG = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.NAUTILUS_SPAWN_EGG), settings -> new SpawnEggItem(NAUTILUS_ENTITY, settings));

    public static final Item WOODEN_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.WOODEN_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.WOOD, 7.0F, -2.4F)));
    public static final Item STONE_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.STONE_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.STONE, 7.0F, -2.4F)));
    public static final Item COPPER_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.COPPER_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.IRON, 7.0F, -2.4F)));
    public static final Item IRON_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.IRON_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.IRON, 7.0F, -2.4F)));
    public static final Item GOLDEN_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.GOLDEN_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.GOLD, 7.0F, -2.4F)));
    public static final Item DIAMOND_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.DIAMOND_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.DIAMOND, 7.0F, -2.4F)));
    public static final Item NETHERITE_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.NETHERITE_SPEAR_ID), settings -> new SpearItem(settings.sword(ToolMaterial.NETHERITE, 7.0F, -2.4F).fireproof()));

    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(NAUTILUS_SPAWN_EGG);
                entries.add(WOODEN_SPEAR);
                entries.add(STONE_SPEAR);
                entries.add(COPPER_SPEAR);
                entries.add(IRON_SPEAR);
                entries.add(GOLDEN_SPEAR);
                entries.add(DIAMOND_SPEAR);
                entries.add(NETHERITE_SPEAR);
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
