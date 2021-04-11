package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;


public class AudioServer {
    public static void init(String song) throws IOException {
       if (song.length() == 0)
            throw new IllegalArgumentException("expected sound file arg");
        File soundFile = AudioUtil.getSoundFile(song);

        System.out.println("server: " + soundFile);

        try (ServerSocket serverSocker = new ServerSocket(4896);
             FileInputStream in = new FileInputStream(soundFile)) {
            if (serverSocker.isBound()) {
                Socket client = serverSocker.accept();
                try (OutputStream out = client.getOutputStream()) {

                    byte buffer[] = new byte[2048];
                    int count;
                    while ((count = in.read(buffer)) != -1)
                        out.write(buffer, 0, count);
                }
            }
        }

        System.out.println("server: shutdown");
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