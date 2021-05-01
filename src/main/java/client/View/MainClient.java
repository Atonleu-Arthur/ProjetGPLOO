package client.View;

import client.Controller.SimpleClient;
import client.Controller.AudioClient;
import Model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException {

        final String SERVER_IP ="localhost";

       SimpleClient c1 = new SimpleClient();
       AudioClient c2 = new AudioClient();

      System.out.println("> [CLIENT] Type h for available commands");

        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        String albumTitle = null;
        String playlistTitle = null;
        String musicTitle = null;
        boolean indicate =true;

        if (choice.length() == 0) System.exit(0);
        while (true) 	{
            switch (choice.charAt(0)) {
                case 'h':
                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;
                case 't':
                    //album titles, ordered by date
                    c1.connect(SERVER_IP,choice);
                    //musicTitle = scan.nextLine();

                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;
                case 'g':
                    //songs of an album, sorted by genre
                    System.out.println("> Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
                    c1.connect(SERVER_IP,"t");
                    albumTitle = scan.nextLine();
                        //System.out.println("theHub.getAlbumSongsSortedByGenre(albumTitle)"); // Pareil ici

                    System.out.println("> Songs of the album : ");
                    c1.connect(SERVER_IP,"o"+albumTitle);

                    System.out.println("what song do you want to play ? ");

                    musicTitle = scan.nextLine();
                    c1.connect(SERVER_IP,"l"+musicTitle);


                    try {
                        c2.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;

                case 'p':
                    //playlist list
                    System.out.println("> Playlist list :");
                    c1.connect(SERVER_IP,"p");
                    playlistTitle = scan.nextLine();
                    //System.out.println("theHub.getAlbumSongsSortedByGenre(albumTitle)"); // Pareil ici

                    System.out.println("> Songs of the playlist : ");
                    c1.connect(SERVER_IP,"i"+playlistTitle);

                    System.out.println("what song do you want to play ? ");

                    musicTitle = scan.nextLine();
                    c1.connect(SERVER_IP,"l"+musicTitle);


                    try {
                        c2.init();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    printAvailableCommands();
                    choice = scan.nextLine();
                    break;

            }
        }
    }

    private static void printAvailableCommands() {
        System.out.println("> t: display the album titles, ordered by date");
        System.out.println("> g: display songs of an album, ordered by genre");
        System.out.println("> d: display songs of an album");
        System.out.println("> p: display playlist");
    }
}
