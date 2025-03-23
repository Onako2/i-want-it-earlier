package rs.onako2.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HarnessItem extends AnimalArmorItem {
    public HarnessItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
    
    public HarnessItem(ArmorMaterial material, Type type, RegistryEntry<SoundEvent> equipSound, boolean damageOnHurt, Settings settings) {
        super(material, type, equipSound, damageOnHurt, settings);
    }
    
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.get(DataComponentTypes.DYED_COLOR) == null) {
            itemStack.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(-13083194, false));
        }
        return super.use(world, user, hand);
    }
}
