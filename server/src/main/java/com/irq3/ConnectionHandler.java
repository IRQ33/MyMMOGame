package com.irq3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import packets.NetworkSystem;
import packets.Packet;
import packets.VersionPacketClient;
import packets.VersionPacketServer;

class ConnectionHandler extends SimpleChannelInboundHandler<Packet> {

    private final int version = 1;
    private Server server;

    public ConnectionHandler(Server server) {
        this.server = server;
    }

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("New conenction");
        ctx.channel().attr(ConnectionManager.networkSystem).set(NetworkSystem.HANDSHAKE);
        super.channelActive(ctx);
    }


    @Override protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        System.out.println("new data");
        switch (msg){
            case VersionPacketClient versionPacketClient->{
                processPacketClient(versionPacketClient,ctx);
            }

            default -> {

                System.out.println("Wrong packet");
            }
        }
    }

    private void processPacketClient(VersionPacketClient versionPacketClient, ChannelHandlerContext ctx){
        if(versionPacketClient.getId()==version){
           // ctx.channel().attr(ConnectionManager.networkSystem).set(NetworkSystem.LOGIN);
            ctx.writeAndFlush(new VersionPacketServer(version));
            System.out.println("ok version");
        }
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
