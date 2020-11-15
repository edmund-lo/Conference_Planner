package Controllers;

import Gateways.*;
import Presenters.LoginPresenter;
import UseCases.*;

import java.util.Scanner;

/**
 * Config file for the entire project
 */
public class ProjectConfig {

    /**
     * Main run method that instantiates all the gateways and handles each user session.
     */
    public void run() {
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        MessageGateway mg = new MessageGateway();
        EventGateway eg = new EventGateway();

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while(running) {
            LoginController lc = startSession(ug, rg, mg, eg);
            endSession(ug, rg, mg, eg, lc);
            System.out.println("Type 'exit' to exit program, anything else returns to the main menu");
            if (sc.nextLine().equals("exit"))
                running = false;
        }
        System.exit(0);
    }

    /**
     * Starts a user session by deserializing all the .ser files into Use Case classes and redirects the user to
     * the login menu.
     * @param ug Represents the current session's UserGateway instance
     * @param rg Represents the current session's RoomGateway instance
     * @param mg Represents the current session's MessageGateway instance
     * @param eg Represents the current session's EventGateway instance
     * @return The current session's LoginController instance
     */
    public LoginController startSession(UserGateway ug, RoomGateway rg, MessageGateway mg, EventGateway eg) {
        UserManager um = ug.deserializeData();
        RoomManager rm = rg.deserializeData();
        MessageManager mm = mg.deserializeData();
        EventManager em = eg.deserializeData();
        LoginPresenter lp = new LoginPresenter();

        Scanner sc = new Scanner(System.in);

        LoginController lc = new LoginController(um, rm, mm, em);
        String x;
        System.out.println("Choose:" +
                "\n1. Login" +
                "\n2. Create Account" +
                "\n3. Exit Program");
        x = sc.nextLine();
        switch(x){
            case "1":
                lc.login();
                break;
            case "2":
                lc.CreateAccount();
                break;
            case "3":
                break;
            default:
                lp.ValidNumber();

        }
        return lc;
    }

    /**
     * Ends the user's current session and serializes the Use Case classes into .ser files.
     * @param ug Represents the current session's UserGateway instance
     * @param rg Represents the current session's RoomGateway instance
     * @param mg Represents the current session's MessageGateway instance
     * @param eg Represents the current session's EventGateway instance
     * @param lc Represents the current session's LoginController instance
     */
    public void endSession(UserGateway ug, RoomGateway rg, MessageGateway mg, EventGateway eg, LoginController lc) {
        ug.serializeData(lc.um);
        rg.serializeData(lc.rm);
        mg.serializeData(lc.mm);
        eg.serializeData(lc.em);
    }
}
