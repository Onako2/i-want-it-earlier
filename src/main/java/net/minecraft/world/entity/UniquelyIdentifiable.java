package net.minecraft.world.entity;

import java.util.UUID;

public interface UniquelyIdentifiable {
    UUID getUuid();

    boolean isRemoved();
}
