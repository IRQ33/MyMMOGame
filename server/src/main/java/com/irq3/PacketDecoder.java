package com.irq3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import packets.NetworkSystem;
import packets.Packet;

import java.util.List;

class PacketDecoder extends ByteToMessageDecoder {

    @Override protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(!byteBuf.isReadable()){
            return;
        }
        int id = byteBuf.readByte();
        NetworkSystem networkSystem = channelHandlerContext.channel().attr(ConnectionManager.networkSystem).get();

        Class<? extends Packet> packetClass = networkSystem.getPacket(id);
        Packet packet1 = packetClass.getDeclaredConstructor().newInstance();
        packet1.decode(byteBuf);

        list.add(packet1);


    }
}
