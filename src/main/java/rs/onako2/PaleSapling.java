package rs.onako2;

import net.minecraft.block.SaplingGenerator;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

import java.util.Optional;

public class PaleSapling {
    public static final SaplingGenerator PALE;

    static {
        PALE = new SaplingGenerator("pale", Optional.empty(), Optional.of(TreeConfiguredFeatures.OAK), Optional.empty());

    }
}
