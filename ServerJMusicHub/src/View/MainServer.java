package View;

import Controller.AbstractServer;
import Controller.FirstServer;


public class MainServer {

    public static  void main (String[] args) {


        MainServer mainServer = new MainServer();

        new MainCommand(mainServer);

        AbstractServer as = new FirstServer();
        String ip = "localhost";
        as.connect(ip);

    }

}
