package server.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Antoine R
 * Classe qui gère l'écriture des évènement dans le journal (logs)
 */
public class Logs {



    private String pathFile;
    public FileWriter fw;
    public static final String dir = System.getProperty("user.dir");
    public final String defaultPath = dir + "\\files\\logs.txt";

    /**
     * Initialise une instance de Logs qui écrira dans le fichier journal par défault : "files\logs.txt"
     */

    public Logs()  {

        this.pathFile = this.defaultPath;


        try {
            this.fw = new FileWriter(this.pathFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise une instance de Logs qui écrira dans le fichier journale de son choix
     * @param pathFile Spécification du fichier journal dans lequel on voudra écrire
     * @throws IOException Exception levée si le chemin spécifié en paramètre n'existe pas
     */

    public Logs(String pathFile) throws IOException {
        this.pathFile = pathFile;

        try {
            FileWriter fw = new FileWriter(this.pathFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme l'écriture d'un fichier journal
     * @throws IOException
     */

    public void closeLogs() throws IOException {
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecris un evenement de type "information" horodaté dans le journal <br>
     * Format horodatage : yyyy.MM.dd.HH:mm:ss <br>
     * Format message : horodatage + "! INFO !" + paramètre <br>
     * @param warning Message d'information que l'on veut associer à l'évènement
     */

    public void writeInfo(String warning)  {

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());

        try {
            fw.write(timeStamp + " ! INFO ! " + warning + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ecris un evenement de type "erreur" horodaté dans le journal <br>
     * Format horodatage : yyyy.MM.dd.HH:mm:ss <br>
     * Format message : horodatage + "! ERROR !" + paramètre <br>
     * @param warning Message d'erreur que l'on veut associer à l'évènement
     */

    public void writeError(String warning)  {

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());

        try {
            fw.write(timeStamp + " ! ERROR ! " + warning + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
