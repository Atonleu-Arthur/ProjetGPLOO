package server.Controller;

import java.io.File;

public class AudioUtil {
    /**
     * @author Lucas D, Emmanuel D
     *
     */
        public static File getSoundFile(String fileName) {
            File soundFile = new File(fileName);
            if (!soundFile.exists() || !soundFile.isFile())
                throw new IllegalArgumentException("not a file: " + soundFile);
            return soundFile;
        }
}
