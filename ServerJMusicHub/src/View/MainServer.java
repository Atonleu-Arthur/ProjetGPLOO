package View;

import Controller.AbstractServer;
import Controller.FirstServer;
import Controller.MusicHubController;
import Controller.ServerThread;
import Model.Album;
import Model.AudioElement;
import Model.PlayList;
import Model.Song;

import java.util.Iterator;
import java.util.Scanner;


public class MainServer {

    public static  void main (String[] args) {


        MainServer mainServer = new MainServer();

        new MainCommand(mainServer);

        AbstractServer as = new FirstServer();
        String ip = "localhost";
        as.connect(ip);

    }

}
