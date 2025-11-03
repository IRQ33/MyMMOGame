package com.irq3.mmo.game.net;

import io.netty.util.AttributeKey;
import packets.NetworkSystem;

public class ConnectionManager {
    public static final AttributeKey<NetworkSystem> networkSystem = AttributeKey.newInstance("transport");
}
