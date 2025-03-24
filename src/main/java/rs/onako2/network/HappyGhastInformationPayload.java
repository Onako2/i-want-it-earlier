package rs.onako2.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import rs.onako2.Identifiers;

import java.util.UUID;

public record HappyGhastInformationPayload(UUID uuid, boolean riding, int color) implements CustomPayload {
    public static final CustomPayload.Id<HappyGhastInformationPayload> ID = new CustomPayload.Id<>(Identifiers.HAPPY_GHAST_INFORMATION_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, HappyGhastInformationPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, HappyGhastInformationPayload::uuid,
            PacketCodecs.BOOLEAN, HappyGhastInformationPayload::riding,
            PacketCodecs.INTEGER, HappyGhastInformationPayload::color,
            HappyGhastInformationPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
