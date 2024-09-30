package rs.onako2.iwie;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.onako2.iwie.block.PaleHangingMossBlock;
import rs.onako2.iwie.block.PaleHangingMossPlantBlock;
import rs.onako2.iwie.block.PaleMossBlock;
import rs.onako2.iwie.compat.WilderWildCompat;
import rs.onako2.iwie.feature.pale_moss_ceiling_feature.PaleMossPatchCeilingConfig;
import rs.onako2.iwie.feature.pale_moss_ceiling_feature.PaleMossPatchCeilingFeature;
import rs.onako2.iwie.feature.pale_moss_feature.PaleMossPatchConfig;
import rs.onako2.iwie.feature.pale_moss_feature.PaleMossPatchFeature;
import rs.onako2.iwie.feature.pale_moss_patch_bonemeal.PaleMossPatchBonemealConfig;
import rs.onako2.iwie.feature.pale_moss_patch_bonemeal.PaleMossPatchBonemealFeature;
import rs.onako2.iwie.feature.pale_short_grass_feature.PaleShortGrassFeature;
import rs.onako2.iwie.feature.pale_short_grass_feature.PaleShortGrassPatchConfig;
import rs.onako2.iwie.feature.paletree.PaleFeatureConfig;
import rs.onako2.iwie.feature.paletree.PaleSapling;
import rs.onako2.iwie.feature.paletree.PaleTreeFeature;

public class Init implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("iwie");

    public static final Block TEST = new Block(AbstractBlock.Settings.create().strength(4.0f));

    public static final Block PALE_OAK_LOG = Util.createLogBlock(MapColor.PALE_YELLOW, MapColor.GRAY);

    public static final Block STRIPPED_PALE_OAK_LOG = Util.createLogBlock(MapColor.PALE_YELLOW, MapColor.GRAY);

    public static final Block PALE_OAK_WOOD = new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block STRIPPED_PALE_OAK_WOOD = new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_SHORT_GRASS = new ShortPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XYZ).burnable().pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_MOSS_BLOCK = new PaleMossBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_MOSS_CARPET = new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_OAK_PLANKS = new Block(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_OAK_STRAIRS = new StairsBlock(PALE_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALE_OAK_PLANKS));

    public static final Block PALE_OAK_SLAB = new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_OAK_FENCE = new FenceBlock(AbstractBlock.Settings.create().mapColor(PALE_OAK_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_OAK_FENCE_GATE = new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create().mapColor(PALE_OAK_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).burnable());

    public static final Block PALE_OAK_SAPLING = new SaplingBlock(PaleSapling.PALE, AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XYZ).burnable().pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_OAK_LEAVES = new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never));

    public static final Block PALE_OAK_BUTTON = new ButtonBlock(ModBlockSetType.PALE, 30, AbstractBlock.Settings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_OAK_PRESSURE_PLATE = new PressurePlateBlock(ModBlockSetType.PALE, AbstractBlock.Settings.create().mapColor(PALE_OAK_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_HANGING_MOSS = new PaleHangingMossBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block PALE_HANGING_MOSS_PLANT = new PaleHangingMossPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));
    public static final Identifier PALE_TREE_FEATURE_ID = Identifier.of("iwie", "pale_oak_tree");
    public static final PaleTreeFeature PALE_FEATURE = new PaleTreeFeature(PaleFeatureConfig.CODEC);
    public static final Identifier PALE_MOSS_PATCH_FEATURE_ID = Identifier.of("iwie", "pale_moss_patch");
    public static final PaleMossPatchFeature PALE_MOSS_PATCH_FEATURE = new PaleMossPatchFeature(PaleMossPatchConfig.CODEC);
    public static final Identifier PALE_MOSS_PATCH_CEILING_FEATURE_ID = Identifier.of("iwie", "pale_moss_patch_ceiling");
    public static final PaleMossPatchCeilingFeature PALE_MOSS_PATCH_CEILING_FEATURE = new PaleMossPatchCeilingFeature(PaleMossPatchCeilingConfig.CODEC);
    public static final Identifier PALE_SHORT_GRASS_PATCH_FEATURE_ID = Identifier.of("iwie", "pale_short_grass_patch");
    public static final PaleShortGrassFeature PALE_SHORT_GRASS_FEATURE = new PaleShortGrassFeature(PaleShortGrassPatchConfig.CODEC);
    public static final Identifier PALE_MOSS_PATCH_BONEMEAL_FEATURE_ID = Identifier.of("iwie", "pale_moss_patch_bonemeal");
    public static final PaleMossPatchBonemealFeature PALE_MOSS_PATCH_BONEMEAL_FEATURE = new PaleMossPatchBonemealFeature(PaleMossPatchBonemealConfig.CODEC);

    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(PALE_OAK_LOG);
                entries.add(PALE_SHORT_GRASS);
                entries.add(PALE_MOSS_BLOCK);
                entries.add(PALE_MOSS_CARPET);
                entries.add(PALE_OAK_PLANKS);
                entries.add(PALE_OAK_STRAIRS);
                entries.add(PALE_OAK_SLAB);
                entries.add(PALE_OAK_FENCE);
                entries.add(PALE_OAK_FENCE_GATE);
                entries.add(PALE_OAK_SAPLING);
                entries.add(PALE_OAK_LEAVES);
                entries.add(PALE_OAK_BUTTON);
                entries.add(PALE_OAK_PRESSURE_PLATE);
                entries.add(PALE_HANGING_MOSS);
                entries.add(STRIPPED_PALE_OAK_LOG);
                entries.add(PALE_OAK_WOOD);
                entries.add(STRIPPED_PALE_OAK_WOOD);
            })
            .build();

    @Override
    public void onInitialize() {

        LOGGER.info("Initializing items and blocks from 1.22 update!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of("iwie", "main"), IWIE);

        Registry.register(Registries.FEATURE, PALE_TREE_FEATURE_ID, PALE_FEATURE);

        Registry.register(Registries.FEATURE, PALE_MOSS_PATCH_FEATURE_ID, PALE_MOSS_PATCH_FEATURE);

        Registry.register(Registries.FEATURE, PALE_MOSS_PATCH_CEILING_FEATURE_ID, PALE_MOSS_PATCH_CEILING_FEATURE);

        Registry.register(Registries.FEATURE, PALE_SHORT_GRASS_PATCH_FEATURE_ID, PALE_SHORT_GRASS_FEATURE);

        Registry.register(Registries.FEATURE, PALE_MOSS_PATCH_BONEMEAL_FEATURE_ID, PALE_MOSS_PATCH_BONEMEAL_FEATURE);

        ModRegistry.registerBlocks();
        ModRegistry.registerItems();
        ModRegistry.registerFuel();

        // check if Fabric API is present
        if (FabricLoader.getInstance().isModLoaded("wilderwild")) {
            LOGGER.info("Wilder Wild mod is present, initializing compatibility features!");
            WilderWildCompat.init();
        }

        StrippableBlockRegistry.register(PALE_OAK_LOG, STRIPPED_PALE_OAK_LOG);
        StrippableBlockRegistry.register(PALE_OAK_WOOD, STRIPPED_PALE_OAK_WOOD);
    }
}
