package server.Controller;


import Model.*;
import Model.Exceptions.NoAlbumFoundException;
import Model.Exceptions.NoElementFoundException;
import Model.Exceptions.NoPlayListFoundException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class commandController {

    MusicHubController theHub = new MusicHubController ();

    Scanner scan = new Scanner(System.in);
    /**
     * Création d'un objet log pour
     * l'ecriture des erreurs dans
     * fichier horodatés
     * @author Antoine R
     */
    Logs logs = new Logs();
    String albumTitle = null;
    String choice;



    public void t() {
        //album titles, ordered by date
        System.out.println(theHub.getAlbumsTitlesSortedByDate());
    }

    public void g() {
        //songs of an album, sorted by genre
        System.out.println("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
        System.out.println(theHub.getAlbumsTitlesSortedByDate());
        System.out.print("album : ");
        albumTitle = scan.nextLine();
        try {
            System.out.println(theHub.getAlbumSongsSortedByGenre(albumTitle));
        } catch (NoAlbumFoundException ex) {
            System.out.println("No album found with the requested title " + ex.getMessage());
            /**
             * logWrite error
             */
            logs.writeError("[SERVER]: "+"No album found with the requested title");
        }
    }

    public void d() {
        //songs of an album
        System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
        System.out.println(theHub.getAlbumsTitlesSortedByDate());
        System.out.print("album : ");
        albumTitle = scan.nextLine();
        try {
            System.out.println(theHub.getAlbumSongs(albumTitle));
        } catch (NoAlbumFoundException ex) {
            System.out.println("No album found with the requested title " + ex.getMessage());
            /**
             * logWrite error
             */
            logs.writeError("[SERVER]: "+"No album found with the requested title");

        }

    }

    public void u() {
        //audiobooks ordered by author
        System.out.println(theHub.getAudiobooksTitlesSortedByAuthor());
    }

    public void c() {
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
        System.out.println(">> New element list: ");
        Iterator<AudioElement> it = theHub.elements();
        while (it.hasNext()) System.out.println(it.next().getTitle());
        System.out.println(">> Song created!");
        h();
    }

    public void a() {
        // add a new album
        System.out.println(">> Enter a new album: ");
        System.out.println(">> Album title: ");
        String aTitle = scan.nextLine();
        System.out.println(">> Album artist: ");
        String aArtist = scan.nextLine();
        System.out.println (">> Album length in seconds: ");
        int aLength = Integer.parseInt(scan.nextLine());
        System.out.println(">> Album date as YYYY-DD-MM: ");
        String aDate = scan.nextLine();
        Album a = new Album(aTitle, aArtist, aLength, aDate);
        theHub.addAlbum(a);
        System.out.println(">> New list of albums: ");
        Iterator<Album> ita = theHub.albums();
        while (ita.hasNext()) System.out.println(ita.next().getTitle());
        System.out.println(">> Album created!");
        h();
    }

    public void plus() {
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
        try {
            theHub.addElementToAlbum(songTitle, titleAlbum);
        } catch (NoAlbumFoundException ex){
            System.out.println (ex.getMessage());
            logs.writeError(ex.getMessage());

        } catch (NoElementFoundException ex){
            System.out.println (ex.getMessage());
            logs.writeError(ex.getMessage());

        }
        System.out.println("Song added to the album!");
        h();
    }

    public void l() {
        // add a new audiobook
        System.out.println("Enter a new audiobook: ");
        System.out.println("AudioBook title: ");
        String bTitle = scan.nextLine();
        System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
        String bCategory = scan.nextLine();
        System.out.println("AudioBook artist: ");
        String bArtist = scan.nextLine();
        System.out.println ("AudioBook length in seconds: ");
        int bLength = Integer.parseInt(scan.nextLine());
        System.out.println("AudioBook content: ");
        String bContent = scan.nextLine();
        System.out.println("AudioBook language (french, english, italian, spanish, german)");
        String bLanguage = scan.nextLine();
        AudioBook b = new AudioBook (bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
        theHub.addElement(b);
        System.out.println("Audiobook created! New element list: ");
        Iterator<AudioElement> itl = theHub.elements();
        while (itl.hasNext()) System.out.println(itl.next().getTitle());
    }

    public void p() {
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
        String elementTitle = "initialisation";
        while (elementTitle.charAt(0)!= 'n') 	{
            System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
            elementTitle = scan.nextLine();
            try {
                theHub.addElementToPlayList(elementTitle, playListTitle);
            } catch (NoPlayListFoundException ex) {
                System.out.println (ex.getMessage());

                logs.writeError(ex.getMessage());

            } catch (NoElementFoundException ex) {

                System.out.println (ex.getMessage());
                logs.writeError(ex.getMessage());

            }

            System.out.println("Type y to add a new one, n to end");
            elementTitle = scan.nextLine();
        }
        System.out.println("Playlist created!");

    }

    public void moins() {
        //delete a playlist
        System.out.println("Delete an existing playlist. Available playlists:");
        Iterator<PlayList> itp = theHub.playlists();
        while (itp.hasNext()) {
            PlayList p = itp.next();
            System.out.println(p.getTitle());
        }
        String plTitle = scan.nextLine();
        try {
            theHub.deletePlayList(plTitle);
        }	catch (NoPlayListFoundException ex) {
            System.out.println (ex.getMessage());
        }
        System.out.println("Playlist deleted!");
    }

    public void s() {
        //save elements, albums, playlists
        theHub.saveElements();
        theHub.saveAlbums();
        theHub.savePlayLists();
        System.out.println("Elements, albums and playlists saved!");
        h();
    }

    public void h() {
        System.out.println("c: add a new song");
        System.out.println("a: add a new album");
        System.out.println("+: add a song to an album");
        System.out.println("l: add a new audiobook");
        System.out.println("p: create a new playlist from existing songs and audio books");
        System.out.println("-: delete an existing playlist");
        System.out.println("s: save elements, albums, playlists");
        System.out.println("q: quit program");
    }

}
