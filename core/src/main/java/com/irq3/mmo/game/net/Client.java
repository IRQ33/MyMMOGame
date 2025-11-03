package com.irq3.mmo.game.net;

import com.irq3.mmo.Main;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.IoHandlerFactory;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollIoHandler;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import models.Player;

public class Client {
    private Main main;
    private String ip;
    private int port;
    private Player player;

    public Client(Main main){
        this.main = main;
        ip = main.ip;
        port = main.port;
    }

    public void connect(){
        var eventExecutor = new MultiThreadIoEventLoopGroup(getFactory());

        try {
            var bootstrap = new Bootstrap();
            bootstrap.group(eventExecutor)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .channel(getChannel())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override protected void initChannel(SocketChannel socketChannel) throws Exception {
                        var pipeline = socketChannel.pipeline();
                        pipeline.addLast(
                            new LengthFieldBasedFrameDecoder(1048576,0,4,0,4)
                        );
                        pipeline.addLast(
                            new LengthFieldPrepender(4)
                        );
                        pipeline.addLast(new PacketDecoder());
                        pipeline.addLast(new PacketEncoder());
                        pipeline.addLast(new ConnectionHandler(player));


                    }
                });
            var future = bootstrap.connect(ip,port).sync();
            future.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            eventExecutor.shutdownGracefully();
        }
    }

    private Class<? extends SocketChannel> getChannel(){
        if(Epoll.isAvailable()){
            return EpollSocketChannel.class;
        }else{
            return NioSocketChannel.class;
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
