package models;

import io.netty.channel.ChannelHandlerContext;
import packets.NetworkSystem;

public class Player {
    ChannelHandlerContext ctx;
    private long id;
    private String name;
    private float x,y;
    private float rotation;
    private NetworkSystem networkSystem;

    public Player(ChannelHandlerContext ctx, long id, String name, float x, float y, float rotation) {
        this.ctx = ctx;
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public NetworkSystem getNetworkSystem() {
        return networkSystem;
    }

    public void setNetworkSystem(NetworkSystem networkSystem) {
        this.networkSystem = networkSystem;
    }
}
