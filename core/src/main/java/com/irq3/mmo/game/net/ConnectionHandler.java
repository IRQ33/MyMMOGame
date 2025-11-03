package com.irq3.mmo.game.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import models.Player;
import packets.NetworkSystem;
import packets.Packet;
import packets.VersionPacketClient;
import packets.VersionPacketServer;

class ConnectionHandler extends SimpleChannelInboundHandler<Packet> {

    Player player;

    public ConnectionHandler(Player player) {
        this.player = player;
    }

    public final int version=1;
    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().attr(ConnectionManager.networkSystem).set(NetworkSystem.HANDSHAKE);

        ctx.writeAndFlush(new VersionPacketClient(version));

        super.channelActive(ctx);
    }


    @Override protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        switch (msg){
            case VersionPacketServer packet->{
                handleVersionPacket(packet,ctx);
            }
            // later             ctx.channel().attr(ConnectionManager.networkSystem).set(NetworkSystem.LOGIN);
            default -> throw new IllegalStateException("Unexpected value: " + msg);
        }

    }

    private void handleVersionPacket(VersionPacketServer packetServer,ChannelHandlerContext ctx){
        System.out.println(packetServer.getId());
        if(version ==packetServer.getId()){
            System.out.println("Loginning");
            //ctx.writeAndFlush(null); //TODO: Player packet sent
        }
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
