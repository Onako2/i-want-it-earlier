package rs.onako2.iwie.feature.pale_moss_feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PaleMossPatchConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<PaleMossPatchConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(PaleMossPatchConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(PaleMossPatchConfig::blockId))
                    .apply(instance, PaleMossPatchConfig::new));
}