package packets;

import io.netty.buffer.ByteBuf;

public interface Packet {
    void encode(ByteBuf buf);
    void  decode(ByteBuf buf);
    void process();

}
