package io.github.pennywise.betest.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PingResponse extends Packet {

	@Override
	public void read(DataInputStream stream) throws IOException {
		// O SERVIDOR NÃO aceita PING RESPONSE do CLIENTE, apenas o SERVIDOR pode mandar isto...
	}

	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.writeLong(System.currentTimeMillis());
		// Enviado o tempo do servidor para ele ver o tempo que demora entre o CLIENTE e o SERVIDOR!
	}
	
	

}
