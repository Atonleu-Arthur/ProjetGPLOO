package server.Controller;

import Model.Album;
import Model.AudioElement;
import Model.Exceptions.NoAlbumFoundException;
import Model.Exceptions.NoElementFoundException;
import Model.Exceptions.NoPlayListFoundException;
import Model.Song;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * This thread is responsible to handle client connection.
 */
public class ServerThread extends Thread {

	/**
	 * @author Atonleu A, Lucas D
	 * Cette classe est un Thread qui permet de gérer les différentes réquètes émises par le client
	 *
	 */
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

			/**
			 *create the streams that will handle the objects coming through the sockets
			 */
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			MusicHubController musicHubController = new MusicHubController();

			String request = (String)input.readObject();  //read the object received through the stream and deserialize it

			switch (request.charAt(0)) {
				case 't':
					output.writeObject(musicHubController.getAlbumsTitlesSortedByDate());

					break;
				case 'g':
					break;

				case 'p':
					output.writeObject(musicHubController.getPlaylistsList());
					break;
				case 'o' :
					StringBuffer songlist = new StringBuffer();

					StringBuilder requestAlbum = new StringBuilder(request);
					requestAlbum = requestAlbum.deleteCharAt(0);
					try { for (AudioElement al : musicHubController.getAlbumSongs(requestAlbum.toString()))
						songlist.append(">> "+al.getTitle()+ "\n");
						}catch (NoAlbumFoundException e){
							output.writeObject("Can't find the album");
							logs.writeError("The album requested : "+requestAlbum.toString()+" can't be found");
						}
					output.writeObject(songlist.toString());
					break;

				case 'i' :
					StringBuffer playlistList = new StringBuffer();
					StringBuilder requestPlaylist = new StringBuilder(request);

					requestPlaylist = requestPlaylist.deleteCharAt(0);

					try { for (AudioElement al : musicHubController.getPlaylistSongs(requestPlaylist.toString()))
						playlistList.append(">> "+al.getTitle()+ "\n");
					}catch (NoPlayListFoundException e){
						output.writeObject("Can't find the playlist");
						logs.writeError("The playlist requested : "+requestPlaylist.toString()+" can't be found");
					}
					output.writeObject(playlistList.toString());


					break;

				case 'l' :
					StringBuilder requestSong = new StringBuilder(request);
					requestAlbum = requestSong.deleteCharAt(0);
					request = requestAlbum.toString();
					try {
						AudioServer audio = new AudioServer();
						output.writeObject("Serveur audio Prêt");
						audio.init("files\\" + request + ".wav");
						}catch (Exception ex){
							output.writeObject("Can't find the song");
							System.out.println("Can't find the song");
						}



					break;
				default:
					output.writeObject(musicHubController.getAlbumSongsSortedByGenre(request));
					break;



			}


        } catch (IOException ex) {

			/**
			 * logWrite error
			 */
			logs.writeError("[SERVER]: "+ex.getMessage());

		} catch (ClassNotFoundException ex) {

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
		}  finally {
			try {
				output.close();
				input.close();
			} catch (IOException ioe) {
				/**
				 * logWrite error
				 */
				logs.writeError("[SERVER]: "+ioe.getMessage());
			}
		}


    }
}
