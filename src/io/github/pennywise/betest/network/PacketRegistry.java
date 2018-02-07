package io.github.pennywise.betest.network;

import java.util.HashMap;
import java.util.Map;

import io.github.pennywise.betest.network.packets.Packet;
import io.github.pennywise.betest.network.packets.PingRequest;
import io.github.pennywise.betest.network.packets.PingResponse;

public class PacketRegistry {
	
	private Map<Byte, Class<? extends Packet>> byID = new HashMap<>();
	
	private Map<Class<? extends Packet>, Byte> byPACKET = new HashMap<>();
	
	public PacketRegistry() {
		register(new PingRequest(), 0);
		register(new PingResponse(), 1);
		/* Aqui vai o que voc� quiser, este � o b�sico, apenas crie novas packets para comunica��o...
		 * Depende mesmo da necessidade...
		*/
	}
	
	// Registra...
	private void register(Packet packet, int id) {
		byID.put((byte) id, packet.getClass());
		byPACKET.put(packet.getClass(), (byte) id);
	}
	
	public Class<? extends Packet> getPACKET(byte id) {
		return byID.get(id);
	}
	
	public byte getID(Class<? extends Packet> packet) {
		return byPACKET.get(packet);
	}

}
