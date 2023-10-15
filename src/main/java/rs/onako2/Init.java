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

	public static final Block OXIDIZED_COPPER_LAMP  = new Block(FabricBlockSettings.create().strength(4.0f));

	public static final Block CHISELED_MUDSTONE_BRICKS  = new Block(FabricBlockSettings.create().strength(4.0f));

	public static final Block COPPER_LAMP  = new Block(FabricBlockSettings.create().strength(4.0f));

	public static final Block COPPER_GRID  = new Block(FabricBlockSettings.create().strength(4.0f));

	public static final Block MUDSTONE_BRICKS  = new Block(FabricBlockSettings.create().strength(4.0f));


	    private static final ItemGroup IWIE = FabricItemGroup.builder()
    	.icon(() -> new ItemStack(TEST))
    	.displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
    		entries.add(OXIDIZED_COPPER_LAMP);
			entries.add(CHISELED_MUDSTONE_BRICKS);
			entries.add(COPPER_LAMP);
			entries.add(COPPER_GRID);
			entries.add(MUDSTONE_BRICKS);
    	})
    	.build();

			

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing items and blocks from 1.21 update!");

		Registry.register(Registries.ITEM_GROUP, new Identifier("iwie", "main"), IWIE);

		Registry.register(Registries.BLOCK, new Identifier("iwie", "test"), TEST);
		Registry.register(Registries.ITEM, new Identifier("iwie", "test"), new BlockItem(TEST, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "oxidized_copper_lamp"), OXIDIZED_COPPER_LAMP);
		Registry.register(Registries.ITEM, new Identifier("iwie", "oxidized_copper_lamp"), new BlockItem(OXIDIZED_COPPER_LAMP, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "chiseled_mudstone_bricks"), CHISELED_MUDSTONE_BRICKS);
		Registry.register(Registries.ITEM, new Identifier("iwie", "chiseled_mudstone_bricks"), new BlockItem(CHISELED_MUDSTONE_BRICKS, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "copper_lamp"), COPPER_LAMP);
		Registry.register(Registries.ITEM, new Identifier("iwie", "copper_lamp"), new BlockItem(COPPER_LAMP, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "copper_grid"), COPPER_GRID);
		Registry.register(Registries.ITEM, new Identifier("iwie", "copper_grid"), new BlockItem(COPPER_GRID, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("iwie", "mudstone_bricks"), MUDSTONE_BRICKS);
		Registry.register(Registries.ITEM, new Identifier("iwie", "mudstone_bricks"), new BlockItem(MUDSTONE_BRICKS, new FabricItemSettings()));
	}
}