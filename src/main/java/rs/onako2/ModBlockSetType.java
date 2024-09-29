package rs.onako2;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

import java.util.Map;

public record ModBlockSetType(String name, boolean canOpenByHand, boolean canOpenByWindCharge, boolean canButtonBeActivatedByArrows, BlockSetType.ActivationRule pressurePlateSensitivity, BlockSoundGroup soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
    private static final Map<String, ModBlockSetType> VALUES = new Object2ObjectArrayMap();
    public static final BlockSetType PALE;

    static {
        PALE = register(new BlockSetType("pale"));
    }


    private static BlockSetType register(BlockSetType blockSetType) {
        ModBlockSetType modBlockSetType = new ModBlockSetType(blockSetType.name(), blockSetType.canOpenByHand(), blockSetType.canOpenByWindCharge(), blockSetType.canButtonBeActivatedByArrows(), blockSetType.pressurePlateSensitivity(), blockSetType.soundType(), blockSetType.doorClose(), blockSetType.doorOpen(), blockSetType.trapdoorClose(), blockSetType.trapdoorOpen(), blockSetType.pressurePlateClickOff(), blockSetType.pressurePlateClickOn(), blockSetType.buttonClickOff(), blockSetType.buttonClickOn());
        VALUES.put(blockSetType.name(), modBlockSetType);
        return blockSetType;
    }
}
