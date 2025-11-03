package packets;

import io.netty.buffer.ByteBuf;

public class VersionPacketServer implements Packet{
    int id;

    public VersionPacketServer(int id) {
        this.id = id;
    }

    public VersionPacketServer() {
    }

    @Override public void encode(ByteBuf buf) {
        buf.writeByte(id);
    }

    @Override public void decode(ByteBuf buf) {
      this.id = buf.readByte();
    }

    @Override public void process() {

    }

    public int getId() {
        return id;
    }
}
