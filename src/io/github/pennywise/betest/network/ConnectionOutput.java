package io.github.pennywise.betest.network;

import java.io.DataOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import io.github.pennywise.betest.network.packets.Packet;

public class ConnectionOutput {
	
	private ClientSession session;
	
	private DataOutputStream stream;
	
	private Thread thread;
	
	private BlockingQueue<Packet> queue = new PriorityBlockingQueue<>();
	
	public ConnectionOutput(ClientSession session, DataOutputStream stream) {
		this.session = session;
		this.stream = stream;
		this.thread = new ConnectionOutputSender();
		this.thread.start();
	}
	
	public void kill() {
		this.thread.stop();
	}
	
	public ClientSession getSession() {
		return this.session;
	}
	
	private class ConnectionOutputSender extends Thread {
		
		public void run() {
			try {
			Packet packet = queue.poll();
			stream.writeByte(packet.ID());
			packet.write(stream);
			} catch (Exception ex) {
				// Achar exceções aqui não é necessário.
			}
		}
		
	}
	
	// Adiciona na fila de packets...
	public void send(Packet packet, int priority) {
		packet.setPriority(priority);
		this.queue.add(packet);
	}
	
	// Mesma coisa...
	public void send(Packet packet) {
		this.queue.add(packet);
	}

}
