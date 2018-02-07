package io.github.pennywise.betest.network;

import io.github.pennywise.betest.network.packets.Packet;
import io.github.pennywise.betest.network.packets.PingRequest;
import io.github.pennywise.betest.network.packets.PingResponse;

public class ServerSideHandler extends PacketHandler {

	@Override
	public void handle(Packet packet, ClientSession session) {
		// Recebeu PING_REQUEST, envia PING_RESPONSE diretamente, sem hesitar...
		// LEMBRANDO QUE VOC� PODE SETAR PRIORIDADE NA PACKET, CASO VOC� PRECISA MANDAR ELA COM MUITA URG�NCIA
		if (packet instanceof PingRequest) {
			session.send(new PingResponse());
		}
	}

}
