package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstServer extends AbstractServer
{
	private String ip = "localhost";
	private ServerSocket listerner;

	private ArrayList<ClientHandler> clients = new ArrayList<>();
	private ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public void connect(String ip) {
		try {
			//the server socket is defined only by a port (its IP is localhost)
			listerner = new ServerSocket (4895);
			System.out.println("[SERVER] Server waiting for connection...");
			while (true) {
				Socket socket = listerner.accept();//establishes connection
				System.out.println("[SERVER] Connected as " + ip);
			//ServerThread serverThread = new ServerThread(socket);
			//	clients.add(clientHandler);
			     // pool.execute(serverThread);
				// create a new thread to handle client socket
				new ServerThread(socket).start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			//if IOException close the server socket
			if (listerner != null && !listerner.isClosed()) {
				try {
					listerner.close();
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}