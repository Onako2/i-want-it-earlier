package rs.onako2.iwie.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BreathOfTheNautilusEffect extends StatusEffect {

    public BreathOfTheNautilusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
