package Controller;

import Model.Album;
import Model.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Model.Exceptions.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

 
    public ServerThread(Socket socket) {
        this.socket = socket;

    }
 
    public void run() {
        try {

        	//create the streams that will handle the objects coming through the sockets
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			MusicHubController musicHubController = new MusicHubController();

			String request = (String)input.readObject();  //read the object received through the stream and deserialize it

			switch (request.charAt(0)) {
				case 't':
					//album titles, ordered by date
					output.writeObject(musicHubController.getAlbumsTitlesSortedByDate());

					break;
				case 'g':
					//songs of an album, sorted by genre
					//output.writeObject(musicHubController.getAlbumsTitlesSortedByDate());
					//albumTitle = scan.nextLine();
					System.out.println("theHub.getAlbumSongsSortedByGenre(albumTitle)"); // Pareil ici
					break;
				case 'd':
					//songs of an album
					System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
					System.out.println("theHub.getAlbumsTitlesSortedByDate()");

					//albumTitle = scan.nextLine();
					System.out.println("theHub.getAlbumSongs(albumTitle)");
					break;

				default:
					output.writeObject(musicHubController.getAlbumSongsSortedByGenre(request)); // Pareil ici
					break;



			}






        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();

		} catch (ClassNotFoundException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (NoAlbumFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}


    }
}
