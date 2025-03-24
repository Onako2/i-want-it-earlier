package rs.onako2.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import rs.onako2.Identifiers;

import java.util.UUID;

public record HappyGhastInformationRequestPayload(UUID uuid) implements CustomPayload {
    public static final Id<HappyGhastInformationRequestPayload> ID = new Id<>(Identifiers.HAPPY_GHAST_INFORMATION_REQUEST_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, HappyGhastInformationRequestPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, HappyGhastInformationRequestPayload::uuid,
            HappyGhastInformationRequestPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
