package com.irq3.mmo.game.net;

import com.irq3.mmo.Main;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class Client {
    private Main main;
    private String ip;
    private int port;

    public Client(Main main){
        this.main = main;
        ip = main.ip;
        port = main.port;
    }

    public void connect(){
        var eventExecutor = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());

        try {
            var bootstrap = new Bootstrap();
            bootstrap.group(eventExecutor)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .channel(getChannel())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(
                            new LengthFieldBasedFrameDecoder(1048576,0,4,0,4)
                        );
                        socketChannel.pipeline().addLast(
                            new LengthFieldPrepender(4)
                        );

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

}
