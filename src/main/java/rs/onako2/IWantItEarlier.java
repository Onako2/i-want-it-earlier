package rs.onako2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IWantItEarlier implements ModInitializer {
    public static final String MOD_ID = "iwie";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static final Block TEST = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifiers.TEST_ID), AbstractBlock.Settings.create().strength(4.0f));
    
    private static final ItemGroup IWIE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TEST))
            .displayName(Text.translatable("itemGroup.iwie.main"))
            .entries((context, entries) -> {
                entries.add(TEST);
            })
            .build();
    
    @Override
    public void onInitialize() {
        
        LOGGER.info("Initializing items and blocks from next update!");
        
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "main"), IWIE);
        
        ModRegistry.registerItems();
    }
}
