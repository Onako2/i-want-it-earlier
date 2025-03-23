package rs.onako2.entity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.PlayerInput;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.ai.FlyingTemptGoal;

import java.util.Objects;

public class HappyGhastEntity extends GhastEntity {
    private int harnessColor = -1;
    
    public HappyGhastEntity(EntityType<? extends GhastEntity> entityType, World world) {
        super(entityType, world);
    }
    
    public static net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createHappyGhastAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 40.0).add(EntityAttributes.FOLLOW_RANGE, 100.0).add(EntityAttributes.TEMPT_RANGE, 100.0);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyingTemptGoal(this, stack -> stack.getItem() == IWantItEarlier.HARNESS && this.harnessColor == -1));
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
        this.moveControl = new GhastMoveControl(this);
        setPersistent();
    }
    
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult actionResult = super.interactMob(player, hand);
        if (actionResult != ActionResult.PASS) {
            return actionResult;
        } else {
            Iterable<ItemStack> handItems = player.getHandItems();
            ItemStack currentItem = handItems.iterator().next();
            if (!this.getWorld().isClient && !this.isHarnessed() && Objects.equals(currentItem.getItem(), IWantItEarlier.HARNESS)) {
                this.harness(currentItem, null);
                return ActionResult.SUCCESS;
            }
            return player.shouldCancelInteraction() || !this.getWorld().isClient && this.isHarnessed() && !player.startRiding(this)
                    ? ActionResult.PASS
                    : ActionResult.SUCCESS;
        }
    }
    
    public boolean canBeHarnessed() {
        return this.isAlive() && !this.isBaby();
    }
    
    public boolean isHarnessed() {
        return harnessColor != -1;
    }
    
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("HarnessColor", harnessColor);
    }
    
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("HarnessColor")) {
            harnessColor = nbt.getInt("HarnessColor");
        } else {
            harnessColor = -1;
        }
    }
    
    public void harness(ItemStack stack, @Nullable SoundCategory soundCategory) {
        DyedColorComponent component = stack.get(DataComponentTypes.DYED_COLOR);
        if (component == null) {
            return;
        }
        stack.withItem(Items.AIR);
        harnessColor = component.rgb();
        if (soundCategory != null) {
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_STRIDER_SADDLE, soundCategory, 0.5F, 1.0F);
        }
        this.moveControl.moveTo(this.getX(), this.getY(), this.getZ(), 1.0f);
        this.moveControl.state = MoveControl.State.WAIT;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.getFirstPassenger() instanceof ServerPlayerEntity player) {
            PlayerInput playerInput = player.getPlayerInput();
            this.rotate(player.headYaw, player.getPitch());
            if (playerInput.forward() && !playerInput.backward()) {
                this.forwardSpeed = 1.0f;
            }
            if (playerInput.backward() && !playerInput.forward()) {
                this.forwardSpeed = -1.0f;
            }
            if (playerInput.left() && !playerInput.right()) {
                this.sidewaysSpeed = 1.0f;
            }
            if (playerInput.right() && !playerInput.left()) {
                this.sidewaysSpeed = -1.0f;
            }
            
            // upward and downward movement
            if (playerInput.sprint() && Math.abs(player.getPitch()) >= 10.0f && (playerInput.forward() || playerInput.backward())) {
                this.setUpwardSpeed(Math.clamp(player.getPitch() / (playerInput.backward() ? 80.0f : -80.0f), -1.0f, 1.0f));
            } else {
                this.setUpwardSpeed(Math.abs(this.upwardSpeed) > 0.1f ? this.upwardSpeed / 1.1f : 0.0f);
            }
        } else {
            this.sidewaysSpeed = 0.0f;
            this.forwardSpeed = 0.0f;
        }
    }
    
    @Override
    public boolean collidesWith(Entity other) {
        return true;
    }
    
    @Override
    public boolean isCollidable() {
        return true;
    }
    
    @Override
    public boolean isPushable() {
        return true;
    }
    
    public static class FlyRandomlyGoal extends GhastEntity.FlyRandomlyGoal {
        private final HappyGhastEntity happyGhast;
        
        public FlyRandomlyGoal(HappyGhastEntity happyGhast) {
            super(happyGhast);
            this.happyGhast = happyGhast;
        }
        
        @Override
        public boolean canStart() {
            return !happyGhast.isHarnessed() && super.canStart();
        }
    }
}
