package rs.onako2;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRegistry {
    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of("iwie", "test"), new BlockItem(Init.TEST, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_log"), new BlockItem(Init.PALE_LOG, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_short_grass"), new BlockItem(Init.PALE_SHORT_GRASS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_block"), new BlockItem(Init.PALE_MOSS_BLOCK, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_carpet"), new BlockItem(Init.PALE_MOSS_CARPET, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_planks"), new BlockItem(Init.PALE_PLANKS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_stairs"), new BlockItem(Init.PALE_STRAIRS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_slab"), new BlockItem(Init.PALE_SLAB, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_fence"), new BlockItem(Init.PALE_FENCE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_fence_gate"), new BlockItem(Init.PALE_FENCE_GATE, new Item.Settings()));
    }

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "test"), Init.TEST);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_log"), Init.PALE_LOG);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_short_grass"), Init.PALE_SHORT_GRASS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_block"), Init.PALE_MOSS_BLOCK);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_carpet"), Init.PALE_MOSS_CARPET);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_planks"), Init.PALE_PLANKS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_stairs"), Init.PALE_STRAIRS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_slab"), Init.PALE_SLAB);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_fence"), Init.PALE_FENCE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_fence_gate"), Init.PALE_FENCE_GATE);
    }
}
