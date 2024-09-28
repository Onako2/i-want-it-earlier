package rs.onako2;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModRegistry {
    public static void registerItems() {
        net.minecraft.registry.Registry.register(Registries.ITEM, Identifier.of("iwie", "test"), new BlockItem(Init.TEST, new Item.Settings()));
    }

    public static void registerBlocks() {
        net.minecraft.registry.Registry.register(Registries.BLOCK, Identifier.of("iwie", "test"), Init.TEST);
    }
}
