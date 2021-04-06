package Controller;

import Model.Album;
import Model.Student;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient  {


	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket socket;


	public void connect(String ip,String request)
	{
		int port = 4895;
		try  {
			//create the socket; it is defined by an remote IP address (the address of the server) and a port number
			socket = new Socket(ip, port);

			//create the streams that will handle the objects coming and going through the sockets
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			//String textToSend = new String("send me the student info!");
			//System.out.println("text sent to the server: " + textToSend);
			output.writeObject(request);		//serialize and write the String to the stream

			String requestResponse = (String) input.readObject();	//deserialize and read the Student object from the stream
			System.out.println(requestResponse);
		} catch  (UnknownHostException uhe) {
			uhe.printStackTrace();
		}
		catch  (IOException ioe) {
			ioe.printStackTrace();
		}
		catch  (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
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

	public String RequestReceived(){
		String request = null;
		return null;
	}

}