package server.Controller;

import server.Controller.AudioUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class AudioServer {



    public static void init(String song) throws IOException {

        /**
         * @author Atonleu A, Lucas D
         * Cette classe a pour rôle d'envoyer le flux audio vers le client
         * via les websockets
         * Création d'un objet log pour
         * l'ecriture des erreurs dans
         * fichier horodatés
         */
        Logs logs = new Logs();
       if (song.length() == 0)
            throw new IllegalArgumentException("expected sound file arg");
        File soundFile = AudioUtil.getSoundFile(song);

        System.out.println("[server] : " + soundFile.getName() +".\n Press h to display the administration menu ");

        try (ServerSocket serverSocker = new ServerSocket(4895);
             FileInputStream in = new FileInputStream(soundFile)) {
            if (serverSocker.isBound()) {
                Socket client = serverSocker.accept();
                try (OutputStream out = client.getOutputStream()) {

                    byte buffer[] = new byte[2048];
                    int count;
                    while ((count = in.read(buffer)) != -1)
                        out.write(buffer, 0, count);
                }catch (Exception ex)
                {
                    /**
                     * logWrite error
                     */
                    logs.writeError("[SERVER]: "+ex.getMessage());
                }
            }
        }

        System.out.println("server: shutdown");
    }
}

