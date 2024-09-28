package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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

    public static final Block TEST = new Block(AbstractBlock.Settings.create().strength(4.0f));


    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
            })
            .build();


    @Override
    public void onInitialize() {

        LOGGER.info("Initializing items and blocks from 1.22 update!");

        Registry.register(Registries.ITEM_GROUP, Identifier.of("iwie", "main"), IWIE);

        ModRegistry.registerBlocks();
        ModRegistry.registerItems();
    }
}
