package rs.onako2.iwie.feature.pale_oak_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PaleOakTreeFeatureConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<PaleOakTreeFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(PaleOakTreeFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(PaleOakTreeFeatureConfig::blockId))
                    .apply(instance, PaleOakTreeFeatureConfig::new));
}
