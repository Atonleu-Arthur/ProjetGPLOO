package client.Controller;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;


public class AudioClient {
    public void init() throws Exception {

            try (Socket socket = new Socket("localhost", 4895)) {
                if (socket.isConnected()) {
                    InputStream in = new BufferedInputStream(socket.getInputStream());
                    //System.out.println(in);
                    play(in);
                }
            }


        System.out.println(">> Fin de la lecture \n>>Menu ");
    }


    private static synchronized void play(final InputStream in) throws Exception {
        System.out.println("> Lecture en cours...");
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
}

class AudioUtil {
    public static File getSoundFile(String fileName) {
        File soundFile = new File(fileName);
        if (!soundFile.exists() || !soundFile.isFile())
            throw new IllegalArgumentException("not a file: " + soundFile);
        return soundFile;
    }
}