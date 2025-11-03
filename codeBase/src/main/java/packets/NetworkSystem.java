package packets;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


public enum NetworkSystem {


    HANDSHAKE(-1){
        {
            register(VersionPacketClient.class);
            register(VersionPacketServer.class);
        }
    },
    LOGIN(0){
        {
            register(null);
        }
    };

    NetworkSystem(int i) {
        this.state = i;
        packets = HashBiMap.create();
    }

    private final int state;
    private final BiMap<Integer, Class<? extends Packet>> packets;

    public void register(Class<? extends Packet> clazz){
        packets.put(packets.size(),clazz);
    }
    public Class<? extends Packet>  getPacket(Integer id){
        return packets.get(id);
    }
    public Integer getPacketId(Class<? extends Packet> packet) {
        return packets.inverse().get(packet);
    }

}
