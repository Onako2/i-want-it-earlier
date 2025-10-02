package rs.onako2.iwie.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpearItem extends Item {
    public SpearItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.sendMessage(Text.of("This hasn't been implemented yet!"), true);
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.willBreakNextUse()) {
            return ActionResult.FAIL;
        } else {
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            playerEntity.sendMessage(Text.of("This hasn't been implemented yet!"), true);
            return true;
        }
        return false;
    }
}
