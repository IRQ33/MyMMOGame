package packets;

import io.netty.buffer.ByteBuf;

public class VersionPacketClient implements Packet{

    int id;

    public VersionPacketClient(int id) {
        this.id = id;
    }

    public VersionPacketClient() {
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
