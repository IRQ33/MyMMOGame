package com.irq3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import packets.NetworkSystem;
import packets.Packet;

class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        NetworkSystem currentState = channelHandlerContext.channel().attr(ConnectionManager.networkSystem).get();

        Integer packetId = currentState.getPacketId(packet.getClass());
        byteBuf.writeByte(packetId);
        packet.encode(byteBuf);
    }
}
