package rs.onako2.iwie;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModRegistry {
    public static void registerItems() {
        Items.register(IWantItEarlier.TEST);
    }

    private static Block registerStairsBlock(Identifier id, Block base) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, id), settings -> new StairsBlock(base.getDefaultState(), settings), AbstractBlock.Settings.copy(base));
    }
}
