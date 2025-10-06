package rs.onako2.iwie.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class SpearItem extends Item {
    public SpearItem(Item.Settings settings) {
        super(settings);
    }

    private final List<LivingEntity> entities = new java.util.ArrayList<>();

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.willBreakNextUse()) {
            return ActionResult.FAIL;
        } else {
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient) return;
        double velocity;
        boolean hasVehicle = user.hasVehicle();
        if (hasVehicle) {
            velocity = 0.5f;
        } else {
            velocity = user.getVelocity().getHorizontal().length();
            if (velocity < 0.1f) return;
        }

        world.getOtherEntities(user, user.getBoundingBox().expand(2.5, 2.5, 2.5), entity -> (entity.isLiving() && entity.isAlive()) && !entity.getPassengerList().contains(user)).forEach(entity -> {
            if (!entities.contains((LivingEntity) entity)) {
                entities.add((LivingEntity) entity);
                entity.damage((ServerWorld) world, user.getDamageSources().genericKill(), (float) (3.5 * (1 + velocity)));
            }
        });
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        entities.clear();
        return true;
    }
}
