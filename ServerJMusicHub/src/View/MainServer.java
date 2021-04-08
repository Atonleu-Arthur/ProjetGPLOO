package View;

import Controller.AbstractServer;
import Controller.FirstServer;
import Controller.MusicHubController;
import Controller.ServerThread;
import Model.Album;
import Model.AudioElement;
import Model.Exceptions.NoAlbumFoundException;
import Model.Exceptions.NoElementFoundException;
import Model.Exceptions.NoPlayListFoundException;
import Model.PlayList;
import Model.Song;

import java.util.Iterator;
import java.util.Scanner;


public class MainServer {

    public static  void main (String[] args) throws NoAlbumFoundException, NoElementFoundException, NoPlayListFoundException {




        /***
         *
         *
         */
       MusicHubController theHub = new MusicHubController();
        System.out.println("system administration by administrator \n");
        printAvailableCommands();


        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        while (choice.charAt(0)!= 'q'){
            switch (choice.charAt(0)){
                case 'h':
                    AbstractServer as = new FirstServer();
                    String ip = "localhost";
                    as.connect(ip);
                    break;
                case 'c':
                    // add a new song
                    System.out.println("Enter a new song: ");
                    System.out.println("Song title: ");
                    String title = scan.nextLine();
                    System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
                    String genre = scan.nextLine();
                    System.out.println("Song artist: ");
                    String artist = scan.nextLine();
                    System.out.println ("Song length in seconds: ");
                    int length = Integer.parseInt(scan.nextLine());
                    System.out.println("Song content: ");
                    String content = scan.nextLine();
                    Song s = new Song (title, artist, length, content, genre);
                    theHub.addElement(s);
                    System.out.println("New element list: ");
                    Iterator<AudioElement> it = theHub.elements();
                    while (it.hasNext()) System.out.println(it.next().getTitle());
                    System.out.println("Song created!");
                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;
                case 'a':
                    // add a new album
                    System.out.println("Enter a new album: ");
                    System.out.println("Album title: ");
                    String aTitle = scan.nextLine();
                    System.out.println("Album artist: ");
                    String aArtist = scan.nextLine();
                    System.out.println ("Album length in seconds: ");
                    int aLength = Integer.parseInt(scan.nextLine());
                    System.out.println("Album date as YYYY-DD-MM: ");
                    String aDate = scan.nextLine();
                    Album a = new Album(aTitle, aArtist, aLength, aDate);
                    theHub.addAlbum(a);
                    System.out.println("New list of albums: ");
                    Iterator<Album> ita = theHub.albums();
                    while (ita.hasNext()) System.out.println(ita.next().getTitle());
                    System.out.println("Album created!");
                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;

                case '+':
                    //add a song to an album:
                    System.out.println("Add an existing song to an existing album");
                    System.out.println("Type the name of the song you wish to add. Available songs: ");
                    Iterator<AudioElement> itae = theHub.elements();
                    while (itae.hasNext()) {
                        AudioElement ae = itae.next();
                        if ( ae instanceof Song) System.out.println(ae.getTitle());
                    }
                    String songTitle = scan.nextLine();

                    System.out.println("Type the name of the album you wish to enrich. Available albums: ");
                    Iterator<Album> ait = theHub.albums();
                    while (ait.hasNext()) {
                        Album al = ait.next();
                        System.out.println(al.getTitle());
                    }
                    String titleAlbum = scan.nextLine();
                    theHub.addElementToAlbum(songTitle, titleAlbum);
                    System.out.println("Song added to the album!");
                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;
                case 'p':
                    //create a new playlist from existing elements
                    System.out.println("Add an existing song or audiobook to a new playlist");
                    System.out.println("Existing playlists:");
                    Iterator<PlayList> itpl = theHub.playlists();
                    while (itpl.hasNext()) {
                        PlayList pl = itpl.next();
                        System.out.println(pl.getTitle());
                    }
                    System.out.println("Type the name of the playlist you wish to create:");
                    String playListTitle = scan.nextLine();
                    PlayList pl = new PlayList(playListTitle);
                    theHub.addPlaylist(pl);
                    System.out.println("Available elements: ");

                    Iterator<AudioElement> itael = theHub.elements();
                    while (itael.hasNext()) {
                        AudioElement ae = itael.next();
                        System.out.println(ae.getTitle());
                    }
                    while (choice.charAt(0)!= 'n') 	{
                        System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
                        String elementTitle = scan.nextLine();
                        theHub.addElementToPlayList(elementTitle, playListTitle);

                        System.out.println("Type y to add a new one, n to end");
                        choice = scan.nextLine();
                    }
                    System.out.println("Playlist created!");
                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;

            }
        }
    }

    private static void printAvailableCommands() {
        System.out.println("> h:[SERVER] Start Server");
        System.out.println("> c: add a new song");
        System.out.println("> a: add a new album");
        System.out.println("> +: add a song to an album");
        System.out.println("> -: delete an existing playlist");
        System.out.println("> p: create a new playlist from existing songs and audio books");
        System.out.println("> s: save elements, albums, playlists");
        System.out.println("> q: quit program");
    }

}
