package View;

import Controller.AbstractServer;
import Controller.FirstServer;

public class ServerConnection
{

	public static void main (String[] args) {
		AbstractServer as = new FirstServer();
		String ip = "localhost";
		System.out.println("Serveur");
		as.connect(ip);
		
	}
}