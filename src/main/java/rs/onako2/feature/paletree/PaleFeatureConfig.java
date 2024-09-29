package rs.onako2.feature.paletree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PaleFeatureConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<PaleFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(PaleFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(PaleFeatureConfig::blockId))
                    .apply(instance, PaleFeatureConfig::new));
}
