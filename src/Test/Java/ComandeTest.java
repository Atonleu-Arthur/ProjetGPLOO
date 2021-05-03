//package Test;


import server.Model.Exceptions.NoAlbumFoundException;
import server.Model.Exceptions.NoElementFoundException;
import server.Model.Exceptions.NoPlayListFoundException;
import server.Model.PlayList;
import org.junit.jupiter.api.Test;

import server.Controller.MusicHubController;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * test les different commande possible
 *
 * @author Emanuel Dabadie
 */

public class ComandeTest {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    //SimpleClient c2 = new SimpleClient();
    String albumTitle = "Best of Beatles";
    String playlist= "my favourite songs";
    String elementTitle="Here Comes the Sun";
    MusicHubController musicHubController = new MusicHubController();
    Iterator<PlayList> itpl = musicHubController.playlists();
    PlayList pl= itpl.next();





    @Test
    void testgetAlbumsTitlesSortedByDate() throws IOException, ClassNotFoundException {
       // output.writeObject("t");
       musicHubController.getAlbumsTitlesSortedByDate();
       // assertEquals((String) input.readObject(), musicHubController.getAlbumsTitlesSortedByDate());
       // assertEquals((List<String>) input.readObject(), musicHubController.availableCommands());
    }

    @Test
    void testgetAlbumSongsSortedByGenre() throws IOException, ClassNotFoundException, NoAlbumFoundException {
      // musicHubController.getAlbumSongsSortedByGenre(albumTitle);
        // output.writeObject("g");
       // assertEquals((String) input.readObject(), musicHubController.getAlbumSongsSortedByGenre(albumTitle));
      //  assertEquals((List<String>) input.readObject(), musicHubController.availableCommands());
    }

    @Test
    void testgetAlbumSongs() throws IOException, ClassNotFoundException, NoAlbumFoundException {
      //  musicHubController.getAlbumSongs(albumTitle);
        //output.writeObject("d");
       // assertEquals((String) input.readObject(), musicHubController.getAlbumSongs(albumTitle));
      //  assertEquals((List<String>) input.readObject(), musicHubController.availableCommands());
    }

    /**
     * On test si les different élément on bien été sauvegarder
     */
    @Test
    void testsave(){
        musicHubController.saveElements();
        musicHubController.saveAlbums();
       musicHubController.savePlayLists();
    }

    /**
     * On test si on peut bien suprimer un playlist
     *
     * @throws NoPlayListFoundException si jamais on trouve pas la playlist en question
     */
    @Test
    void testdeletePlayList() throws NoPlayListFoundException {
        musicHubController.deletePlayList(playlist);
    }

    /**
     * On test si on peux bien ajouter une playlist
     * Et si on peux ajouter une music a une playlist
     *
     * @throws NoElementFoundException si on trouve pas la music en question qu'on veux rajouter
     * @throws NoPlayListFoundException Si on trouve pas la playliste
     */
    @Test
    void testaddPlayList() throws NoElementFoundException, NoPlayListFoundException {
       musicHubController.addPlaylist(pl);
       musicHubController.addElementToPlayList(elementTitle, playlist);
   }
   /*@Test
    void testaddElementToAlbum(){
        try {
            musicHubController.addElementToAlbum(elementTitle, albumTitle);
        } catch (NoAlbumFoundException e) {
            e.printStackTrace();
        } catch (NoElementFoundException e) {
            e.printStackTrace();
        }
    }*/


}
