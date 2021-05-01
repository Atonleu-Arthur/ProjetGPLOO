package View;

import Controller.commandController;

import java.util.Scanner;


public class MainCommand implements Runnable {

    Thread t;


    MainCommand(MainServer mainServer) {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        commandController controller = new commandController();
        Scanner scan = new Scanner(System.in);


        System.out.println("Type h for available commands");
        System.out.print(">> : ");
        String choice = "a";

        while (choice != "q") {
            choice = scan.nextLine();
            switch (choice) {
                case "h": controller.h(); break;
                case "t": controller.t(); break;
                case "g": controller.g(); break;
                case "d": controller.d(); break;
                case "u": controller.u(); break;
                case "c": controller.c(); break;
                case "a": controller.a(); break;
                case "+": controller.plus(); break;
                case "l": controller.l(); break;
                case "p": controller.p(); break;
                case "-": controller.moins(); break;
                case "s": controller.s(); break;
                case "q": System.exit(1);
                default : break;
            }
            System.out.print(">> : ");

        }
    }
}
