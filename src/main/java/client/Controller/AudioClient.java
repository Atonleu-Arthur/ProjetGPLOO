package client.Controller;

import client.View.MainClient;


import java.io.*;
import java.net.*;
import javax.sound.sampled.*;


public class AudioClient implements Runnable {
    /**
     *
     * Classe qui gère la connexion au serveur audio afin de pouvoir lire les fichiers audio
     * @author lucas D
     */
    Socket socket = new Socket("localhost", 4895);
     Thread thread;
    private volatile boolean running = true;
    public AudioClient() throws IOException {
        thread = new Thread(this);
        thread.start();
    }

    public void init() throws Exception {


                if (socket.isConnected()) {
                    InputStream in = new BufferedInputStream(socket.getInputStream());
                    //System.out.println(in);
                    play(in);
                }



        System.out.println(">> Fin de la lecture \n>>Menu ");
    }


    private static synchronized void play(final InputStream in) throws Exception {
        System.out.println(">> Lecture en cours...");
        AudioInputStream ais = AudioSystem.getAudioInputStream(in);

       // System.out.println("Lecture");
        try (Clip clip = AudioSystem.getClip()) {
          // System.out.println("Lecture");
            clip.open(ais);
          //  System.out.println("Lecture");
            clip.start();
            //System.out.println("Lecture");
            Thread.sleep(100); // given clip.drain a chance to start
            clip.drain();
        }
    }


    public   void stop() {
        this.running = false;
    }


    @Override
    public void run() {
         while (running==true){

             if (socket.isConnected()) {
                 //System.out.println(in);
                 try {
                     InputStream in = new BufferedInputStream(socket.getInputStream());
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
}

class AudioUtil {
    /**
     *
     * Classe qui gère la lecture du fichier audio
     * @author lucas
     */
    public static File getSoundFile(String fileName) {
        File soundFile = new File(fileName);
        if (!soundFile.exists() || !soundFile.isFile())
            throw new IllegalArgumentException("not a file: " + soundFile);
        return soundFile;
    }
}