package net.minecraft.nbt;

import rs.onako2.iwie.IWantItEarlier;

public final class NbtCompound {
    public int getInt(String key, int fallback) {
        ohNo();
        return -1;
    }

    public void putInt(String key, int value) {
    }

    public void putBoolean(String key, boolean value) {
        ohNo();
    }

    public boolean getBoolean(String key, boolean fallback) {
        ohNo();
        return false;
    }

    public void ohNo() {
        IWantItEarlier.LOGGER.error("WHAT THE HELL, WHY IS THIS METHOD CALLED????");
    }
}
