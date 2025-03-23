package rs.onako2.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HarnessItem extends AnimalArmorItem {
    public HarnessItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
    
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.get(DataComponentTypes.DYED_COLOR) == null) {
            itemStack.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(11546150, false));
        }
        return super.use(world, user, hand);
    }
}
