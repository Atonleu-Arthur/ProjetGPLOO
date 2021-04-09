package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logs {

    private String pathFile;
    public FileWriter fw;
    public static final String dir = System.getProperty("user.dir");
    public final String defaultPath = dir + "\\files\\logs.txt";

    public Logs() throws IOException {

        this.pathFile = this.defaultPath;


        try {
            this.fw = new FileWriter(this.pathFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logs(String pathFile) throws IOException {
        this.pathFile = pathFile;

        try {
            FileWriter fw = new FileWriter(this.pathFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogs() throws IOException {
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeInfo(String warning) throws IOException {

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());

        try {
            fw.write(timeStamp + " ! INFO ! " + warning + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeError(String warning) throws IOException {

        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
        String timeStamp = date.format(new Date());

        try {
            fw.write(timeStamp + " ! ERROR ! " + warning + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Logs testLog = new Logs();


            testLog.writeInfo("Album not found");
            testLog.writeError("Cela na pas fonctionné");

            testLog.closeLogs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
