package server.Controller;

import Model.Album;
import Model.Exceptions.NoAlbumFoundException;
import Model.Song;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {
    private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	/**
	 * Création d'un objet log pour
	 * l'ecriture des erreurs dans
	 * fichier horodatés
	 */
	Logs logs = new Logs();
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
					//System.out.println("theHub.getAlbumSongsSortedByGenre(albumTitle)"); // Pareil ici
					break;

				case 'p':
					//playlist list
					output.writeObject(musicHubController.getPlaylistsList());
					break;
				case 'o' :
					StringBuffer songlist = new StringBuffer();

					StringBuilder requestAlbum = new StringBuilder(request);
					requestAlbum = requestAlbum.deleteCharAt(0);
					for (Song al : musicHubController.getAlbumSongsSortedByGenre(requestAlbum.toString()))
						songlist.append(">> "+al.getTitle()+ "\n");
					output.writeObject(songlist.toString());
					break;

				case 'i' :
					StringBuilder requestPlaylist = new StringBuilder(request);
					requestPlaylist = requestPlaylist.deleteCharAt(0);
					output.writeObject(musicHubController.getAlbumSongsSortedByGenre(requestPlaylist.toString()));
					break;

				case 'l' :
					StringBuilder requestSong = new StringBuilder(request);
					requestAlbum = requestSong.deleteCharAt(0);
					request = requestAlbum.toString();

					AudioServer audio = new AudioServer();
					output.writeObject("Serveur audio Prêt");
					audio.init("files\\"+request+".wav");


					break;
				default:
					output.writeObject(musicHubController.getAlbumSongsSortedByGenre(request)); // Pareil ici
					break;



			}






        } catch (IOException ex) {
          //  System.out.println("Server exception: " + ex.getMessage());
            //ex.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+ex.getMessage());

		} catch (ClassNotFoundException ex) {
            //System.out.println("Server exception: " + ex.getMessage());
            //ex.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+ex.getMessage());
        } catch (NoAlbumFoundException e) {
			//e.printStackTrace();
			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+e.getMessage());
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException ioe) {
				//ioe.printStackTrace();
				/**
				 * logWrite error
				 */
				logs.writeError("[SERVER]: "+ioe.getMessage());
			}
		}


    }
}
