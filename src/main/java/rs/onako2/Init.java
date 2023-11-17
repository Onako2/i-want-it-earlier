package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Init implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("iwie");

	public static final Block TEST  = new Block(FabricBlockSettings.create().strength(4.0f));

	public static final Block OXIDIZED_COPPER_BULB  = new Block(FabricBlockSettings.create().strength(4.0f).luminance(3).requiresTool());

	public static final Block WEATHERED_COPPER_BULB  = new Block(FabricBlockSettings.create().strength(4.0f).luminance(7).requiresTool());

	public static final Block EXPOSED_COPPER_BULB  = new Block(FabricBlockSettings.create().strength(4.0f).luminance(11).requiresTool());

	public static final Block CHISELED_TUFF_BRICKS  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block COPPER_BULB  = new Block(FabricBlockSettings.create().strength(4.0f).luminance(15).requiresTool());

	public static final Block COPPER_GRID  = new Block(FabricBlockSettings.create().nonOpaque().strength(4.0f).requiresTool());

	public static final Block WEATHERED_COPPER_GRID  = new Block(FabricBlockSettings.create().nonOpaque().strength(4.0f).requiresTool());

	public static final Block EXPOSED_COPPER_GRID  = new Block(FabricBlockSettings.create().nonOpaque().strength(4.0f).requiresTool());

	public static final Block OXIDIZED_COPPER_GRID  = new Block(FabricBlockSettings.create().nonOpaque().strength(4.0f).requiresTool());

	public static final Block TRIAL_SPAWNER  = new Block(FabricBlockSettings.create().nonOpaque().strength(4.0f).luminance(8));

	public static final Block TUFF_BRICKS  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block CHISELED_COPPER  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());
	
	public static final Block EXPOSED_CHISELED_COPPER  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block WEATHERED_CHISELED_COPPER  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block OXIDIZED_CHISELED_COPPER  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block POLISHED_CHISELED_TUFF  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public static final Block POLISHED_TUFF  = new Block(FabricBlockSettings.create().strength(4.0f).requiresTool());


	    private static final ItemGroup IWIE = FabricItemGroup.builder()
    	.icon(() -> new ItemStack(TEST))
    	.displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
    		entries.add(OXIDIZED_COPPER_BULB);
			entries.add(WEATHERED_COPPER_BULB);
			entries.add(EXPOSED_COPPER_BULB);
			entries.add(COPPER_BULB);
			entries.add(OXIDIZED_COPPER_GRID);
			entries.add(WEATHERED_COPPER_GRID);
			entries.add(EXPOSED_COPPER_GRID);
			entries.add(COPPER_GRID);
			entries.add(TUFF_BRICKS);
			entries.add(POLISHED_TUFF);
			entries.add(CHISELED_TUFF_BRICKS);
			entries.add(POLISHED_CHISELED_TUFF);
			entries.add(OXIDIZED_CHISELED_COPPER);
			entries.add(WEATHERED_CHISELED_COPPER);
			entries.add(EXPOSED_CHISELED_COPPER);
			entries.add(CHISELED_COPPER);
			entries.add(TRIAL_SPAWNER);
    	})
    	.build();


	@Override
	public void onInitialize() {

		LOGGER.info("Initializing items and blocks from 1.21 update!");

		Registry.register(Registries.ITEM_GROUP, new Identifier("iwie", "main"), IWIE);

		Registry.register(Registries.BLOCK, new Identifier("iwie", "test"), TEST);
		Registry.register(Registries.ITEM, new Identifier("iwie", "test"), new BlockItem(TEST, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "oxidized_copper_bulb"), OXIDIZED_COPPER_BULB);
		Registry.register(Registries.ITEM, new Identifier("iwie", "oxidized_copper_bulb"), new BlockItem(OXIDIZED_COPPER_BULB, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "weathered_copper_bulb"), WEATHERED_COPPER_BULB);
		Registry.register(Registries.ITEM, new Identifier("iwie", "weathered_copper_bulb"), new BlockItem(WEATHERED_COPPER_BULB, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "exposed_copper_bulb"), EXPOSED_COPPER_BULB);
		Registry.register(Registries.ITEM, new Identifier("iwie", "exposed_copper_bulb"), new BlockItem(EXPOSED_COPPER_BULB, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "chiseled_tuff_bricks"), CHISELED_TUFF_BRICKS);
		Registry.register(Registries.ITEM, new Identifier("iwie", "chiseled_tuff_bricks"), new BlockItem(CHISELED_TUFF_BRICKS, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "copper_bulb"), COPPER_BULB);
		Registry.register(Registries.ITEM, new Identifier("iwie", "copper_bulb"), new BlockItem(COPPER_BULB, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "copper_grid"), COPPER_GRID);
		Registry.register(Registries.ITEM, new Identifier("iwie", "copper_grid"), new BlockItem(COPPER_GRID, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "exposed_copper_grid"), EXPOSED_COPPER_GRID);
		Registry.register(Registries.ITEM, new Identifier("iwie", "exposed_copper_grid"), new BlockItem(EXPOSED_COPPER_GRID, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "oxidized_copper_grid"), OXIDIZED_COPPER_GRID);
		Registry.register(Registries.ITEM, new Identifier("iwie", "oxidized_copper_grid"), new BlockItem(OXIDIZED_COPPER_GRID, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "weathered_copper_grid"), WEATHERED_COPPER_GRID);
		Registry.register(Registries.ITEM, new Identifier("iwie", "weathered_copper_grid"), new BlockItem(WEATHERED_COPPER_GRID, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "tuff_bricks"), TUFF_BRICKS);
		Registry.register(Registries.ITEM, new Identifier("iwie", "tuff_bricks"), new BlockItem(TUFF_BRICKS, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "polished_tuff"), POLISHED_TUFF);
		Registry.register(Registries.ITEM, new Identifier("iwie", "polished_tuff"), new BlockItem(POLISHED_TUFF, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "polished_chiseled_tuff"), POLISHED_CHISELED_TUFF);
		Registry.register(Registries.ITEM, new Identifier("iwie", "polished_chiseled_tuff"), new BlockItem(POLISHED_CHISELED_TUFF, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "chiseled_copper"), CHISELED_COPPER);
		Registry.register(Registries.ITEM, new Identifier("iwie", "chiseled_copper"), new BlockItem(CHISELED_COPPER, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "exposed_chiseled_copper"), EXPOSED_CHISELED_COPPER);
		Registry.register(Registries.ITEM, new Identifier("iwie", "exposed_chiseled_copper"), new BlockItem(EXPOSED_CHISELED_COPPER, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "weathered_chiseled_copper"), WEATHERED_CHISELED_COPPER);
		Registry.register(Registries.ITEM, new Identifier("iwie", "weathered_chiseled_copper"), new BlockItem(WEATHERED_CHISELED_COPPER, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "oxidized_chiseled_copper"), OXIDIZED_CHISELED_COPPER);
		Registry.register(Registries.ITEM, new Identifier("iwie", "oxidized_chiseled_copper"), new BlockItem(OXIDIZED_CHISELED_COPPER, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "trial_spawner"), TRIAL_SPAWNER);
		Registry.register(Registries.ITEM, new Identifier("iwie", "trial_spawner"), new BlockItem(TRIAL_SPAWNER, new FabricItemSettings()));
		
	}
}
