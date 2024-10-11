package rs.onako2.iwie.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rs.onako2.iwie.entity.CreakingEntity;

@Mixin(IllagerEntity.class)
public abstract class IllagerEntityMixin extends RaiderEntity {
    protected IllagerEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    public void initGoals(CallbackInfo ci) {
        this.goalSelector.add(3, new FleeEntityGoal<>(this, CreakingEntity.class, 8.0F, 1.0, 1.2));
    }
}
