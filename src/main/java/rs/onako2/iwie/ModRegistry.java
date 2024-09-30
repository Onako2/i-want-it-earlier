package rs.onako2.iwie;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRegistry {
    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of("iwie", "test"), new BlockItem(Init.TEST, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_log"), new BlockItem(Init.PALE_OAK_LOG, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_short_grass"), new BlockItem(Init.PALE_SHORT_GRASS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_block"), new BlockItem(Init.PALE_MOSS_BLOCK, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_carpet"), new BlockItem(Init.PALE_MOSS_CARPET, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_planks"), new BlockItem(Init.PALE_OAK_PLANKS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_stairs"), new BlockItem(Init.PALE_OAK_STRAIRS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_slab"), new BlockItem(Init.PALE_OAK_SLAB, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_fence"), new BlockItem(Init.PALE_OAK_FENCE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_fence_gate"), new BlockItem(Init.PALE_OAK_FENCE_GATE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_sapling"), new BlockItem(Init.PALE_OAK_SAPLING, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_leaves"), new BlockItem(Init.PALE_OAK_LEAVES, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_button"), new BlockItem(Init.PALE_OAK_BUTTON, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_pressure_plate"), new BlockItem(Init.PALE_OAK_PRESSURE_PLATE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_hanging_moss"), new BlockItem(Init.PALE_HANGING_MOSS, new Item.Settings()));
        // no BlockItem for PALE_HANGING_MOSS_PLANT
        Registry.register(Registries.ITEM, Identifier.of("iwie", "stripped_pale_oak_log"), new BlockItem(Init.STRIPPED_PALE_OAK_LOG, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_wood"), new BlockItem(Init.PALE_OAK_WOOD, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "stripped_pale_oak_wood"), new BlockItem(Init.STRIPPED_PALE_OAK_WOOD, new Item.Settings()));
    }

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "test"), Init.TEST);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_log"), Init.PALE_OAK_LOG);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_short_grass"), Init.PALE_SHORT_GRASS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_block"), Init.PALE_MOSS_BLOCK);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_carpet"), Init.PALE_MOSS_CARPET);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_planks"), Init.PALE_OAK_PLANKS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_stairs"), Init.PALE_OAK_STRAIRS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_slab"), Init.PALE_OAK_SLAB);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_fence"), Init.PALE_OAK_FENCE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_fence_gate"), Init.PALE_OAK_FENCE_GATE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_sapling"), Init.PALE_OAK_SAPLING);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_leaves"), Init.PALE_OAK_LEAVES);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_button"), Init.PALE_OAK_BUTTON);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_pressure_plate"), Init.PALE_OAK_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_hanging_moss"), Init.PALE_HANGING_MOSS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_hanging_moss_plant"), Init.PALE_HANGING_MOSS_PLANT);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "stripped_pale_oak_log"), Init.STRIPPED_PALE_OAK_LOG);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_wood"), Init.PALE_OAK_WOOD);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "stripped_pale_oak_wood"), Init.STRIPPED_PALE_OAK_WOOD);
    }

    public static void registerFuel() {
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_LOG, 300);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_PLANKS, 300);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_STRAIRS, 150);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_SLAB, 150);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_FENCE, 300);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_FENCE_GATE, 300);
        FuelRegistry.INSTANCE.add(Init.PALE_OAK_WOOD, 300);
        FuelRegistry.INSTANCE.add(Init.STRIPPED_PALE_OAK_LOG, 300);
        FuelRegistry.INSTANCE.add(Init.STRIPPED_PALE_OAK_WOOD, 300);
    }
}
