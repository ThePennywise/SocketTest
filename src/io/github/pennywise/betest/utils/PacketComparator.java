package io.github.pennywise.betest.utils;

import java.util.Comparator;

import io.github.pennywise.betest.network.packets.Packet;

public class PacketComparator implements Comparator<Packet> {

	// Esta classe v� qual packet tem a maior prioridade
	
	@Override
	public int compare(Packet o1, Packet o2) {
		return o1.getPriority() - o2.getPriority();
	}

}
