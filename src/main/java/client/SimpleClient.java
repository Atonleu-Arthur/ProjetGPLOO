package client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *  * @Author Atonleu A, Lucas D
 *
 * Implementation du design Singleton
 * Cette classe à pour rôle de d'etablir une connexion avec
 * le serveur afin de pouvoir communiquer :  recevoir et envoyer les
 * les éléments du serveur
 */
public class SimpleClient   {


	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket socket;
	private volatile boolean running = true;
	Socket socketAudio ;
	Thread thread;
	private char erreur = '0';

	public SimpleClient() throws IOException {

	}

	public void connect(String ip,String request)
	{

		int port = 4896;
		try  {
			socket = new Socket(ip, port);

			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			output.writeObject(request);		//serialize and write the String to the stream
			erreur = '0';
			String requestResponse = (String) input.readObject();	//deserialize and read the Student object from the stream


			if (requestResponse.contains("Can't find"))
			{
			}

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

	public char getErreur(){
		return erreur;
	}


	public void run() throws IOException {
		socketAudio = new Socket("localhost", 4895);
		while (running==true){

			if (socketAudio.isConnected()) {
				try {
					InputStream in = new BufferedInputStream(socketAudio.getInputStream());
					play(in);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(">> Fin de la lecture \n>>Menu ");
		}
		Thread.interrupted();

		System.out.println(">> Fin de la lecture \n>>Menu ");
	}
	private static synchronized void play(final InputStream in) throws Exception {
		System.out.println(">> Lecture en cours...");
		AudioInputStream ais = AudioSystem.getAudioInputStream(in);

		try (Clip clip = AudioSystem.getClip()) {
			clip.open(ais);
			clip.start();
			Thread.sleep(100); // given clip.drain a chance to start
			clip.drain();
		}
	}

	private static synchronized void stop(final InputStream in) throws Exception {
		System.out.println(">> Lecture arrêtée...");
		AudioInputStream ais = AudioSystem.getAudioInputStream(in);
		try (Clip clip = AudioSystem.getClip()) {
			clip.open(ais);
			clip.stop();
			Thread.sleep(100); // given clip.drain a chance to start
			clip.drain();
		}
	}
	public   void stop() {

	}


}