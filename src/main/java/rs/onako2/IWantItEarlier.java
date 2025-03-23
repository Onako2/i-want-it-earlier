package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(DRIED_GHAST_BLOCK);
                entries.add(HAPPY_GHAST_SPAWN_EGG);
                entries.add(HARNESS);
            })
            .build();
    
    @Override
    public void onInitialize() {
        
        LOGGER.info("Initializing item and block from next update!");
        
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "main"), IWIE);
        
        ModRegistry.registerItems();
        
        FabricDefaultAttributeRegistry.register(HAPPY_GHAST, HappyGhastEntity.createHappyGhastAttributes());
    }
}
