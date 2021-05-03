package server.View;

import server.Controller.AbstractServer;
import server.Controller.FirstServer;
import server.Controller.AbstractServer;


public class MainServer {

    public static  void main (String[] args) {


        MainServer mainServer = new MainServer();

        new MainCommand();

        AbstractServer as = new FirstServer();
        String ip = "localhost";
        as.connect(ip);

    }

}
