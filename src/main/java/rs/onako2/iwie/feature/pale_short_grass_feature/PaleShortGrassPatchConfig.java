package rs.onako2.iwie.feature.pale_short_grass_feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PaleShortGrassPatchConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<PaleShortGrassPatchConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(PaleShortGrassPatchConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(PaleShortGrassPatchConfig::blockId))
                    .apply(instance, PaleShortGrassPatchConfig::new));
}