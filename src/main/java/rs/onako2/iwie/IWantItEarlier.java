package rs.onako2.iwie;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.MobEntity;
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
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.onako2.iwie.effect.BreathOfTheNautilusEffect;
import rs.onako2.iwie.entity.AbstractNautilusEntity;
import rs.onako2.iwie.entity.NautilusEntity;
import rs.onako2.iwie.entity.ZombieNautilusEntity;
import rs.onako2.iwie.item.SpearItem;

public class IWantItEarlier implements ModInitializer {
    public static final String MOD_ID = "iwie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @NotNull
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

    @NotNull
    public static final EntityType<ZombieNautilusEntity> ZOMBIE_NAUTILUS_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifiers.ZOMBIE_NAUTILUS_ID,
            EntityType.Builder.create(ZombieNautilusEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.0F, 1.15F)
                    .eyeHeight(0.4F)
                    .maxTrackingRange(24)
                    .makeFireImmune()
                    .dropsNothing()
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifiers.ZOMBIE_NAUTILUS_ID))
    );

    public static final Block TEST = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.TEST_ID), AbstractBlock.Settings.create().strength(4.0f));

    private static SpawnEggItem getEggItem(EntityType<? extends MobEntity> type, Item.Settings settings, int primaryColor, int secondaryColor) {
        try {
            // 1.21.3 and below
            try {
                java.lang.reflect.Constructor<SpawnEggItem> constructor = SpawnEggItem.class.getDeclaredConstructor(EntityType.class, int.class, int.class, Item.Settings.class);
                constructor.setAccessible(true);
                return constructor.newInstance(type, primaryColor, secondaryColor, settings);
            } catch (ReflectiveOperationException ex) {
                throw new RuntimeException("Failed to create SpawnEggItem", ex);
            }
        } catch (Exception e) {
            try {
                java.lang.reflect.Constructor<SpawnEggItem> constructor = SpawnEggItem.class.getDeclaredConstructor(EntityType.class, Item.Settings.class);
                constructor.setAccessible(true);
                return constructor.newInstance(type, settings);
            } catch (Exception ex) {
                return new SpawnEggItem(settings.spawnEgg(type));
            }
        }
    }

    public static final Item NAUTILUS_SPAWN_EGG = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.NAUTILUS_SPAWN_EGG), settings -> getEggItem(NAUTILUS_ENTITY, settings, -1, -1));
    public static final Item ZOMBIE_NAUTILUS_SPAWN_EGG = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.ZOMBIE_NAUTILUS_SPAWN_EGG), settings -> getEggItem(ZOMBIE_NAUTILUS_ENTITY, settings, -1, -1));

    public static final Item WOODEN_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.WOODEN_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.WOOD, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item STONE_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.STONE_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.STONE, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item COPPER_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.COPPER_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.IRON, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item IRON_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.IRON_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.IRON, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item GOLDEN_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.GOLDEN_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.GOLD, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item DIAMOND_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.DIAMOND_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.DIAMOND, 7.0F, -2.4F).useCooldown(2.0f)));
    public static final Item NETHERITE_SPEAR = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.NETHERITE_SPEAR_ID), settings -> new SpearItem(sword(settings, ToolMaterial.NETHERITE, 7.0F, -2.4F).fireproof().useCooldown(2.0f)));

    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(NAUTILUS_SPAWN_EGG))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(NAUTILUS_SPAWN_EGG);
                entries.add(ZOMBIE_NAUTILUS_SPAWN_EGG);
                entries.add(WOODEN_SPEAR);
                entries.add(STONE_SPEAR);
                entries.add(COPPER_SPEAR);
                entries.add(IRON_SPEAR);
                entries.add(GOLDEN_SPEAR);
                entries.add(DIAMOND_SPEAR);
                entries.add(NETHERITE_SPEAR);
            })
            .build();

    public static final RegistryEntry<StatusEffect> BREATH_OF_NAUTILUS = Registry.registerReference(Registries.STATUS_EFFECT, Identifiers.BREATH_OF_THE_NAUTILUS_ID, new BreathOfTheNautilusEffect());

    @Override
    public void onInitialize() {

        LOGGER.info("Initializing items and blocks from next update!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "main"), IWIE);

        ModRegistry.registerItems();

        FabricDefaultAttributeRegistry.register(NAUTILUS_ENTITY, AbstractNautilusEntity.createAbstractNautilusAttributes());
        FabricDefaultAttributeRegistry.register(ZOMBIE_NAUTILUS_ENTITY, AbstractNautilusEntity.createAbstractNautilusAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.SAVANNA), SpawnGroup.MONSTER, EntityType.ZOMBIE_HORSE, 1, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.WARM_OCEAN, BiomeKeys.FROZEN_OCEAN), SpawnGroup.UNDERGROUND_WATER_CREATURE, NAUTILUS_ENTITY, 1, 1, 2);
    }

    public static Item.Settings sword(Item.Settings settings, ToolMaterial material, float attackDamage, float attackSpeed) {
        return material.applySwordSettings(settings, attackDamage, attackSpeed);
    }
}
