package com.vomiter.extradelight.network;

import com.vomiter.extradelight.ExtraDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;


public class NetworkHandler {
    private static final String PROTOCOL = "1";
    public static SimpleChannel CHANNEL;
    private static boolean initialized = false;


    public static void setupPackets() {
        if (initialized) return;
        initialized = true;
        CHANNEL = NetworkRegistry.newSimpleChannel(
                ResourceLocation.fromNamespaceAndPath(ExtraDelight.MOD_ID, "main"),
                () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals
        );
        CHANNEL.messageBuilder(
                StyleableMenuP2CPkt.class,
                StyleableMenuP2CPkt.NETWORK_ID,
                NetworkDirection.PLAY_TO_CLIENT
        )
            .encoder(StyleableMenuP2CPkt::encode)
            .decoder(StyleableMenuP2CPkt::decode)
            .consumerMainThread(StyleableMenuP2CPkt::handle)
            .add();

    }


}