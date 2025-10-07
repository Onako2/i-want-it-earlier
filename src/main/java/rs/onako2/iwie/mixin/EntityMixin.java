package rs.onako2.iwie.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rs.onako2.iwie.IWantItEarlier;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "setAir", at = @At("HEAD"), cancellable = true)
    public void setAir(int air, CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity living) {
            if (living.hasStatusEffect(IWantItEarlier.BREATH_OF_NAUTILUS)) {
                ci.cancel();
            }
        }
    }
}
