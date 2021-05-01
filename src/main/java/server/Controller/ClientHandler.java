package server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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



    /*public void connectSend(String ip, String Objet) {
        try  {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            socket = new Socket(ip, port);

            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            output.writeObject(Objet);		//serialize and write the String to the stream


        } catch  (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
        catch  (IOException ioe) {
            ioe.printStackTrace();
        }

        finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    public String ConnectReceive(String ip) {

        try {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            socket = new Socket(ip, port);
            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            ServerAnswer = (String) input.readObject();
        } catch (UnknownHostException uhe) {
        System.out.println(uhe.toString());
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        } finally {
            try {
                input.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return ServerAnswer;
        }
    }
*/
    @Override
    public void run() {

        while (true){
            //String request = input.readLine();
      output.println("Arthur send");

        }


    }
}
