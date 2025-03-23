package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.onako2.block.DriedGhastBlock;
import rs.onako2.entity.HappyGhastEntity;
import rs.onako2.item.HarnessItem;

public class IWantItEarlier implements ModInitializer {
    public static final String MOD_ID = "iwie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static final EntityType<HappyGhastEntity> HAPPY_GHAST = Registry.register(
            Registries.ENTITY_TYPE,
            Identifiers.HAPPY_GHAST_ID,
            EntityType.Builder.create(HappyGhastEntity::new, SpawnGroup.MONSTER)
                    .dimensions(4.0f, 4.0f)
                    .makeFireImmune()
                    .dropsNothing()
                    .passengerAttachments(new Vec3d(0, 4, 2))
                    .passengerAttachments(new Vec3d(-2, 4, 0))
                    .passengerAttachments(new Vec3d(2, 4, 0))
                    .passengerAttachments(new Vec3d(0, 4, -2))
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifiers.HAPPY_GHAST_ID))
    );
    
    public static final Block TEST = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.TEST_ID), AbstractBlock.Settings.create().strength(4.0f));
    
    public static final Block DRIED_GHAST_BLOCK = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.DRIED_GHAST_ID), DriedGhastBlock::new, AbstractBlock.Settings.create().ticksRandomly().solid());
    
    public static final Item HAPPY_GHAST_SPAWN_EGG = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.HAPPY_GHAST_SPAWN_EGG_ID), settings -> new SpawnEggItem(HAPPY_GHAST, settings));
    public static final Item HARNESS = Items.register(RegistryKey.of(RegistryKeys.ITEM, Identifiers.HARNESS), settings -> new HarnessItem(ArmorMaterials.LEATHER, AnimalArmorItem.Type.EQUESTRIAN, settings));
    private static final ItemStack RED_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack ORANGE_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack YELLOW_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack LIME_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack GREEN_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack LIGHT_BLUE_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack CYAN_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack BLUE_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack PURPLE_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack MAGENTA_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack PINK_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack WHITE_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack LIGHT_GRAY_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack GRAY_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack BLACK_HARNESS = new ItemStack(HARNESS);
    private static final ItemStack BROWN_HARNESS = new ItemStack(HARNESS);
    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(DRIED_GHAST_BLOCK);
                entries.add(HAPPY_GHAST_SPAWN_EGG);
                entries.add(RED_HARNESS);
                entries.add(ORANGE_HARNESS);
                entries.add(YELLOW_HARNESS);
                entries.add(LIME_HARNESS);
                entries.add(GREEN_HARNESS);
                entries.add(LIGHT_BLUE_HARNESS);
                entries.add(CYAN_HARNESS);
                entries.add(BLUE_HARNESS);
                entries.add(PURPLE_HARNESS);
                entries.add(MAGENTA_HARNESS);
                entries.add(PINK_HARNESS);
                entries.add(WHITE_HARNESS);
                entries.add(LIGHT_GRAY_HARNESS);
                entries.add(GRAY_HARNESS);
                entries.add(BLACK_HARNESS);
                entries.add(BROWN_HARNESS);
            })
            .build();
    
    static {
        RED_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(11546150, false));
        ORANGE_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(16351261, false));
        YELLOW_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(16701501, false));
        LIME_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(8439583, false));
        GREEN_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(6192150, false));
        LIGHT_BLUE_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(3847130, false));
        CYAN_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(1481884, false));
        BLUE_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(3949738, false));
        PURPLE_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(8991416, false));
        MAGENTA_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(13061821, false));
        PINK_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(15961002, false));
        WHITE_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(16383998, false));
        LIGHT_GRAY_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(10329495, false));
        GRAY_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(4673362, false));
        BLACK_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(1908001, false));
        BROWN_HARNESS.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(8606770, false));
    }
    
    @Override
    public void onInitialize() {
        
        LOGGER.info("Initializing items and blocks from next update!");
        
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "main"), IWIE);
        
        ModRegistry.registerItems();
        
        FabricDefaultAttributeRegistry.register(HAPPY_GHAST, HappyGhastEntity.createHappyGhastAttributes());
    }
}
