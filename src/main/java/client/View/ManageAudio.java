package client.View;

import client.Controller.AudioClient;
import server.View.MainServer;

import java.io.IOException;
import java.util.Scanner;

public class ManageAudio implements Runnable {

    /**
     * @author Arthur
     * Classe pour gérer la lecture de
     * la musique : Pause, Arrêt ....
     *
     */
    Thread thread;


    ManageAudio(AudioClient audioClient) throws IOException {
        thread = new Thread(this);
        thread.start();


    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            //AudioClient audioClient= new AudioClient();
            System.out.println(">> Enter  m for stop music and display the menu: ");

            String choice =null;
            Boolean value=true;
            while (value==true) 	{
                System.out.print(">> ");
                choice = scanner.nextLine();
                switch (choice.charAt(0)) {
                    case 'm':
                      // AudioClient.stop();
                        System.out.println(">> Music stops: ");
                        value=false;
                    break;
                    default:
                        System.out.println(">> m for stop: ");
                        choice = scanner.nextLine();
                        break;

                }
            }

            thread.interrupt();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
