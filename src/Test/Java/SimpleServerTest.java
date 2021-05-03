import server.Model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import static org.junit.jupiter.api.Assertions.*;

import server.Controller.FirstServer;
import server.Controller.*;


/**
 * test si l'ajoue d'une music marche bien et quel la connection réussi
 *
 * @author Emanuel Dabadie
 */

public class SimpleServerTest {

    MusicHubController musicHubController = new MusicHubController();


    /**
     * test si l'ajoue d'une music marche bien
     *
     * @throws IOException si la connextion a échouer ou a été intérompue
     */

    @Test
    void testconnexion() throws IOException {


       try {
           /**
            * On crée le serveur on lui donne une adresse Ip aute que localhost et on l'active
            */
           AbstractServer as = new FirstServer();
           String ip = "192.160.1";
           as.connect(ip);




            /**
             * On crée le client qui va pouvoir encoyer et resevoir les information du serveur
             */
            Socket c2 = new Socket("192.16.0.1", 4895);
            ObjectInputStream inputClient = new ObjectInputStream(c2.getInputStream());
           ObjectOutputStream outputClient = new ObjectOutputStream(c2.getOutputStream());

           /**
            * On crée la musuic qu'on va tester pour ajouter une music
            */
           Song s = new Song ("test", "moi", 240, "jsp", "jazz");
           /**
            * on l'envoie
            */
           musicHubController.addElement(s);

           /**
            * Ici on récupère la réponse et on voie si c'est la meme que prévue
            */
           String requestResponse = (String) inputClient.readObject();
           System.out.println("Message reçu : " + inputClient.readObject());
           assertEquals(">> Song created!", inputClient.readObject());

          //  connect b1 = new connect();
          //  b1.setCommand("h");
           // outputClient.writeObject("nhgv");
            //test
            //Pour ètres sur que le message ai le temps arriver
          //  sleep(50);
            //ON regarde ce que le client a recu
           // c2 = (Socket) inputClient.readObject();
         //   assertEquals("> t: display the album titles, ordered by date", c2.ConnectReceive());// ou ServerAnswer dans ClientHandler

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}