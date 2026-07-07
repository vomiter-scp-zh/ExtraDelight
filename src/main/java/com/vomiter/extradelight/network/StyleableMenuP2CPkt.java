package com.vomiter.extradelight.network;

import com.vomiter.extradelight.gui.StyleableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record StyleableMenuP2CPkt(int containerId, BlockPos pos){
    public static final int NETWORK_ID = 1;

    public static void encode(StyleableMenuP2CPkt pkt, FriendlyByteBuf buf) {
        buf.writeInt(pkt.containerId());
        buf.writeBlockPos(pkt.pos());
    }


    public static StyleableMenuP2CPkt decode(FriendlyByteBuf buf){
        var containerId = buf.readInt();
        var pos = buf.readBlockPos();
        return new StyleableMenuP2CPkt(containerId, pos);
    }

    public static void handle(StyleableMenuP2CPkt message, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().equals(NetworkDirection.PLAY_TO_CLIENT)) {
			ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().screen != null
                        && Minecraft.getInstance().screen instanceof StyleableScreen screen) {
                    screen.setPos(message.pos());
                }
            });
		}
	}

}