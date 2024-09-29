package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
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

public class Init implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("iwie");

    public static final Block TEST = new Block(AbstractBlock.Settings.create().strength(4.0f));

    public static final Block PALE_LOG = Util.createLogBlock(MapColor.PALE_YELLOW, MapColor.GRAY);

    public static final Block PALE_SHORT_GRASS = new ShortPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XYZ).burnable().pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_MOSS_BLOCK = new MossBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_MOSS_CARPET = new CarpetBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_PLANKS = new Block(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_STRAIRS = new StairsBlock(PALE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALE_PLANKS));

    public static final Block PALE_SLAB = new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_FENCE = new FenceBlock(AbstractBlock.Settings.create().mapColor(PALE_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable());

    public static final Block PALE_FENCE_GATE = new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.create().mapColor(PALE_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).burnable());

    public static final Block PALE_SAPLING = new SaplingBlock(PaleSapling.PALE, AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XYZ).burnable().pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_LEAVES = new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never));

    public static final Block PALE_BUTTON = new ButtonBlock(ModBlockSetType.PALE, 30,AbstractBlock.Settings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block PALE_PRESSURE_PLATE = new PressurePlateBlock(ModBlockSetType.PALE, AbstractBlock.Settings.create().mapColor(PALE_PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY));

    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
                entries.add(PALE_LOG);
                entries.add(PALE_SHORT_GRASS);
                entries.add(PALE_MOSS_BLOCK);
                entries.add(PALE_MOSS_CARPET);
                entries.add(PALE_PLANKS);
                entries.add(PALE_STRAIRS);
                entries.add(PALE_SLAB);
                entries.add(PALE_FENCE);
                entries.add(PALE_FENCE_GATE);
                entries.add(PALE_SAPLING);
                entries.add(PALE_LEAVES);
                entries.add(PALE_BUTTON);
                entries.add(PALE_PRESSURE_PLATE);
            })
            .build();
    public static final Identifier Pale_FEATURE_ID = Identifier.of("iwie", "pale_tree");

    public static final PaleTreeFeature Pale_FEATURE = new PaleTreeFeature(PaleFeatureConfig.CODEC);

    @Override
    public void onInitialize() {

        LOGGER.info("Initializing items and blocks from 1.22 update!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of("iwie", "main"), IWIE);


        Registry.register(Registries.FEATURE, Pale_FEATURE_ID, Pale_FEATURE);


        ModRegistry.registerBlocks();
        ModRegistry.registerItems();
    }
}
