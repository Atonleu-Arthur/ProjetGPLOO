package server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Atonleu A
 * Cette classe a pour rôle de capturer une connexion client et
 * d'en établir la communication client-server via les websockets
 */

public class ClientHandler implements Runnable {
    private PrintWriter output;
    private BufferedReader input;
    private Socket client;
    int port = 4895;
    private String ServerAnswer;
    MusicHubController musicHubController;


    public ClientHandler(Socket socket) throws IOException {
        this.client=socket;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(),true);
        musicHubController= new MusicHubController();

    }


    @Override
    public void run() {

        while (true){
            //String request = input.readLine();
      output.println("Arthur send");

        }


    }
}
