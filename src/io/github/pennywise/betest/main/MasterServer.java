package io.github.pennywise.betest.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import io.github.pennywise.betest.network.ClientManager;
import io.github.pennywise.betest.network.PacketHandler;
import io.github.pennywise.betest.network.PacketRegistry;
import io.github.pennywise.betest.network.ServerListener;
import io.github.pennywise.betest.utils.ArgsValidator;

public class MasterServer {
	
	public static void main(String[] args) {
		if (args.length <= 1) {
			System.out.println("Por favor, defina o IP e a PORTA para o servidor.");
			System.exit(0);
		} else {
			String ip = args[0];
			String stringPort = args[1];
			// Verifica se a porta é um número inteiro.
			if (!ArgsValidator.checkForInteger(stringPort)) {
				System.out.println("A porta definida precisa ser um número inteiro!");
			} else {
				// Ok, tudo certo, vamos continuar.
				int port = Integer.parseInt(stringPort);
				new MasterServer(ip, port);
			}
		}
	}
	
	private ServerSocket server;
	
	private ServerListener serverListener;
	
	private PacketRegistry registry;
	
	private PacketHandler handler;
	
	private ClientManager clientManager;
	
	// Inicia o servidor
	private MasterServer(String ip, int port) {
		try {
			this.server = new ServerSocket();
			this.server.bind(new InetSocketAddress(ip, port));
			this.serverListener = new ServerListener(this);
			this.serverListener.start();
			API.setDefaultServer(this);
			// Inicia todos os módulos necessários
			this.registry = new PacketRegistry();
			this.clientManager = new ClientManager();
		} catch (Exception e) {
			System.out.println(String.format("Ocorreu um erro ao iniciar o servidor %s.", e));
			System.exit(0);
		}
	}
	
	public PacketHandler getPacketHandler() {
		return this.handler;
	}
	
	public PacketRegistry getRegistry() {
		return this.registry;
	}
	
	public ServerSocket getServerSocket() {
		return this.server;
	}
	
	public ServerListener getServerListener() {
		return this.serverListener;
	}
	
	public ClientManager getClientManager() {
		return this.clientManager;
	}
	
	public void kill() {
		System.out.println("Fechando todas as conexões...");
		this.getClientManager().closeAll();
		System.out.println("Desligando servidor...");
		this.serverListener.setRunning(false);
		System.out.println("Goodbye.");
		System.exit(0);
	}

}
