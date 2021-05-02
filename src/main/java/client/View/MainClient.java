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
        MainClient mainClient = new MainClient();
       SimpleClient c1 = new SimpleClient();

        System.out.println("                      WELCOME TO  MUSICHUB 2.0                             \n\nTo listen music, please select an album or a playlist and type the song's name and the listen will be start.                \n\n                      HOPE YOU LIKE IT                        \n\n");


        String choice = "1";

        String albumTitle = null;
        String playlistTitle = null;
        String musicTitle = null;
        boolean indicate =true;

        if (choice.length() == 0) System.exit(0);
        while (true) 	{

            System.out.println(">> [CLIENT] Type h for available commands");
            System.out.print(">>");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();

            switch (choice.charAt(0)) {
                case 'h':
                    printAvailableCommands();
                    break;

                case 'g':
                    //songs of an album, sorted by genre
                    System.out.println(">> Albums list : \n");
                    c1.connect(SERVER_IP,"t");
                    System.out.println(">> Which album do you want to play ? (press q to go to the main menu)");
                    System.out.print(">>");
                    albumTitle = scan.nextLine();
                    System.out.print(albumTitle+"\n");
                    if (!albumTitle.equals("q"))
                    {


                        //System.out.println("theHub.getAlbumSongsSortedByGenre(albumTitle)"); // Pareil ici

                    System.out.println(">> Songs of the album : ");
                    c1.connect(SERVER_IP,"o"+albumTitle);

                    System.out.println(">> Which song do you want to play ? (press q to go to the main menu)");
                    System.out.print(">>");
                    musicTitle = scan.nextLine();
                    if (!musicTitle.equals("q"))
                    {


                    c1.connect(SERVER_IP,"l"+musicTitle);


                    try {
                       // AudioClient c2 = new AudioClient();
                       AudioClient audioClient= new AudioClient();
                        Thread.sleep(100); // given clip.drain a chance to start
                        MainClient.ManageAudio(audioClient);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    printAvailableCommands();
                    System.out.print(">>");
                    choice = scan.nextLine();
                    }
                    }
                    break;

                case 'p':
                    //playlist list
                    System.out.println(">> Playlist list :\n");
                    c1.connect(SERVER_IP,"p");
                    System.out.println(">> Which playlist do you want to play ? ");
                    System.out.print(">>");
                    playlistTitle = scan.nextLine();

                    if (!playlistTitle.equals("q"))
                    {
                    System.out.println(">> Songs of the playlist : ");
                    c1.connect(SERVER_IP,"i"+playlistTitle);

                        System.out.println(">> Which song do you want to play ? (press q to go to the main menu)");
                        System.out.print(">>");
                        musicTitle = scan.nextLine();
                        if (!musicTitle.equals("q"))
                        {

                            c1.connect(SERVER_IP,"l"+musicTitle);

                            try {
                                // AudioClient c2 = new AudioClient();
                                AudioClient audioClient= new AudioClient();
                                Thread.sleep(100); // given clip.drain a chance to start
                                MainClient.ManageAudio(audioClient);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            printAvailableCommands();
                            System.out.print(">>");
                            choice = scan.nextLine();
                        }
                    }
                    break;

            }


        }
    }

    public static void  ManageAudio(AudioClient audioClient){
        try {
            Scanner scanner = new Scanner(System.in);
           // AudioClient audioClient= new AudioClient();
            System.out.println(">> Appuyer sur m pour arrêter la musique et afficher le menu: ");
            // AudioClient c2 = new AudioClient();
          //  c2.init();
            String choice =null;
            Boolean value=true;
            while (value==true){
                System.out.print(">> ");
                choice = scanner.nextLine();
                switch (choice.charAt(0)) {
                    case 'm'://
                      audioClient.stop();
                        value=false;
                        break;
                    default:
                        System.out.println(">> m pour arrêter: ");
                        choice = scanner.nextLine();
                        value=true;
                        break;

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void printAvailableCommands() {
        System.out.println(">> g: display album");
        System.out.println(">> p: display playlist");
    }
}
