package io.github.pennywise.betest.network;

import java.io.IOException;
import java.net.Socket;

import io.github.pennywise.betest.main.API;
import io.github.pennywise.betest.main.MasterServer;

public class ServerListener extends Thread {
	
	private boolean running;
	
	private MasterServer server;
	
	public ServerListener(MasterServer server) {
		this.server = server;
	}
	
	public void run() {
		while (this.running) {
			try {
				Socket socket = this.server.getServerSocket().accept();
				ClientSession newClient = new ClientSession(socket);
				if (API.getClientManager().isConnected(newClient)) {
					// Tentou conectar duas vezes (BUG), para resolver, desconecte a nova sess�o
					socket.close();
					return;
				}
				// Adicionado! Recebendo novas conex�es e HandShake!
				API.getClientManager().addClient(newClient);
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao aceitar uma conex�o!");
				e.printStackTrace();
			}
		}
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public MasterServer getServer() {
		return this.server;
	}

}
