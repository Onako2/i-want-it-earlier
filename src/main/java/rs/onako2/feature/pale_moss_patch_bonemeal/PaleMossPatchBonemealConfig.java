package rs.onako2.feature.pale_moss_patch_bonemeal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PaleMossPatchBonemealConfig(int number, Identifier blockId) implements FeatureConfig {
    public static final Codec<PaleMossPatchBonemealConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            // you can add as many of these as you want, one for each parameter
                            Codecs.POSITIVE_INT.fieldOf("number").forGetter(PaleMossPatchBonemealConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(PaleMossPatchBonemealConfig::blockId))
                    .apply(instance, PaleMossPatchBonemealConfig::new));
}