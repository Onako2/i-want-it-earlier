package rs.onako2.iwie;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static rs.onako2.iwie.Init.*;

public class ModRegistry {
    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of("iwie", "test"), new BlockItem(TEST, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_log"), new BlockItem(PALE_OAK_LOG, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_short_grass"), new BlockItem(PALE_SHORT_GRASS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_block"), new BlockItem(PALE_MOSS_BLOCK, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_moss_carpet"), new BlockItem(PALE_MOSS_CARPET, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_planks"), new BlockItem(PALE_OAK_PLANKS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_stairs"), new BlockItem(PALE_OAK_STRAIRS, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_slab"), new BlockItem(PALE_OAK_SLAB, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_fence"), new BlockItem(PALE_OAK_FENCE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_fence_gate"), new BlockItem(PALE_OAK_FENCE_GATE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_sapling"), new BlockItem(PALE_OAK_SAPLING, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_leaves"), new BlockItem(PALE_OAK_LEAVES, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_button"), new BlockItem(PALE_OAK_BUTTON, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_pressure_plate"), new BlockItem(PALE_OAK_PRESSURE_PLATE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_hanging_moss"), new BlockItem(PALE_HANGING_MOSS, new Item.Settings()));
        // no BlockItem for PALE_HANGING_MOSS_PLANT
        Registry.register(Registries.ITEM, Identifier.of("iwie", "stripped_pale_oak_log"), new BlockItem(STRIPPED_PALE_OAK_LOG, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "pale_oak_wood"), new BlockItem(PALE_OAK_WOOD, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "stripped_pale_oak_wood"), new BlockItem(STRIPPED_PALE_OAK_WOOD, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of("iwie", "creaking_spawn_egg"), CREAKING_SPAWN_EGG);
        Registry.register(Registries.ITEM, Identifier.of("iwie", "creaking_heart"), new BlockItem(CREAKING_HEART, new Item.Settings()));
    }

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "test"), TEST);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_log"), PALE_OAK_LOG);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_short_grass"), PALE_SHORT_GRASS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_block"), PALE_MOSS_BLOCK);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_moss_carpet"), PALE_MOSS_CARPET);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_planks"), PALE_OAK_PLANKS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_stairs"), PALE_OAK_STRAIRS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_slab"), PALE_OAK_SLAB);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_fence"), PALE_OAK_FENCE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_fence_gate"), PALE_OAK_FENCE_GATE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_sapling"), PALE_OAK_SAPLING);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_leaves"), PALE_OAK_LEAVES);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_button"), PALE_OAK_BUTTON);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_pressure_plate"), PALE_OAK_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_hanging_moss"), PALE_HANGING_MOSS);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_hanging_moss_plant"), PALE_HANGING_MOSS_PLANT);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "stripped_pale_oak_log"), STRIPPED_PALE_OAK_LOG);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "pale_oak_wood"), PALE_OAK_WOOD);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "stripped_pale_oak_wood"), STRIPPED_PALE_OAK_WOOD);
        Registry.register(Registries.BLOCK, Identifier.of("iwie", "creaking_heart"), CREAKING_HEART);
    }

    public static void registerFuel() {
        FuelRegistry.INSTANCE.add(PALE_OAK_LOG, 300);
        FuelRegistry.INSTANCE.add(PALE_OAK_PLANKS, 300);
        FuelRegistry.INSTANCE.add(PALE_OAK_STRAIRS, 150);
        FuelRegistry.INSTANCE.add(PALE_OAK_SLAB, 150);
        FuelRegistry.INSTANCE.add(PALE_OAK_FENCE, 300);
        FuelRegistry.INSTANCE.add(PALE_OAK_FENCE_GATE, 300);
        FuelRegistry.INSTANCE.add(PALE_OAK_WOOD, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_PALE_OAK_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_PALE_OAK_WOOD, 300);
    }
}
