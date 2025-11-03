package com.irq3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.IoHandlerFactory;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollIoHandler;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import models.Player;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public final int port=2200;

    List<Player> players;
    private Server server;

    public Server() {
        server = this;
    }

    public void init() {
        System.out.println("hello");
        players = new ArrayList<>();

        var eventLoopGroup = new MultiThreadIoEventLoopGroup(getFactory());
        var serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(eventLoopGroup).childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .channel(getChannel()).childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        var p = socketChannel.pipeline();
                        p.addLast(
                            new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4)
                        );
                        p.addLast(
                            new LengthFieldPrepender(4)
                        );
                        p.addLast(new PacketDecoder());
                        p.addLast(new PacketEncoder());
                        p.addLast(new ConnectionHandler(server));
                    }
                });
            var future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Class<? extends ServerSocketChannel> getChannel(){
        if(Epoll.isAvailable()){
            return EpollServerSocketChannel.class;
        }else{
            return NioServerSocketChannel.class;
        }
    }
    private IoHandlerFactory getFactory(){
        if(Epoll.isAvailable()){
            return EpollIoHandler.newFactory();
        }else {
            return NioIoHandler.newFactory();
        }
    }
}
