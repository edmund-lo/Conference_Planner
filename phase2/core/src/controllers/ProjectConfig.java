package controllers;

import gateways.*;
import presenters.LoginPresenter;
import usecases.*;

import java.util.Scanner;

/**
 * Config file for the entire project
 */
public class ProjectConfig {

    /**
     * Main run method that instantiates all the gateways and handles each user session.
     */
    public void run() {
        // initializes all four gateways
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        MessageGateway mg = new MessageGateway();
        EventGateway eg = new EventGateway();

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while(running) {
            LoginController lc = startSession(ug, rg, mg, eg); // start user session
            endSession(ug, rg, mg, eg, lc); // end user session
            // allow user to exit or return to login menu
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
        // deserialize all four saved Use Case classes from .ser files
        UserManager um = ug.deserializeData();
        RoomManager rm = rg.deserializeData();
        MessageManager mm = mg.deserializeData();
        EventManager em = eg.deserializeData();

        LoginPresenter lp = new LoginPresenter();
        Scanner sc = new Scanner(System.in);

        // instantiate a new LoginController with saved Use Case classes as parameters
        LoginController lc = new LoginController();
        System.out.println("Choose:" +
                "\n1. Login" +
                "\n2. Create Account" +
                "\n3. Exit Program");
        String x = sc.nextLine();
        switch(x){
            case "1": // Login with existing account
                lc.login();
                break;
            case "2": // Create a new account
                //lc.CreateAccount();
                break;
            case "3": // Exit program
                break;
            default: // Invalid input
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
        // serialize all four current session's Use Case classes to .ser files
        ug.serializeData(lc.um);
        rg.serializeData(lc.rm);
        mg.serializeData(lc.mm);
        eg.serializeData(lc.em);
    }
}
