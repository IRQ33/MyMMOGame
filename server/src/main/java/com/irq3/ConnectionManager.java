package com.irq3;

import io.netty.util.AttributeKey;
import packets.NetworkSystem;

public class ConnectionManager {
    public static final AttributeKey<NetworkSystem> networkSystem = AttributeKey.newInstance("transport");
}
