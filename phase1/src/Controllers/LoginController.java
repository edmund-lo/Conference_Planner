package controllers;

import presenters.*;
import usecases.EventManager;
import usecases.MessageManager;
import usecases.RoomManager;
import usecases.UserManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Controller class which deals with users logging in and creating new accounts.
 *
 * @author Haider Bokhari
 * @version 1.0
 *
 */

public class LoginController {
    private ArrayList<String[]> Accounts;
    private final Scanner sc;
    protected UserManager um;
    protected RoomManager rm;
    protected MessageManager mm;
    protected EventManager em;
    protected UserController controller;
    private final LoginPresenter lp;

    /**
     * Constructor for LoginController object.
     *
     * @param um  current session's UseCases.UserManager class.
     * @param rm  current session's UseCases.RoomManager class.
     * @param mm  current session's UseCases.MessageManager class.
     * @param em  current session's UseCases.EventManager class.
     *
     */
    public LoginController(UserManager um, RoomManager rm, MessageManager mm, EventManager em){
        //Scanner to read user input
        this.sc = new Scanner(System.in);
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.em = em;
        //Get list of all existing accounts from the user manager
        this.Accounts = um.getAccountInfo();
        this.lp = new LoginPresenter();
    }

    /**
     * Called to create a new account for a user.
     */
    public void CreateAccount(){
        String Username;
        String Password;
        String type;

        int UsernameCheck;
        boolean UsernameSet = false;
        do {
            lp.CreateAccountP();
            UsernameCheck = 0;
            lp.EnterUsername();
            Username = sc.nextLine();

            //Loops through all existing usernames.
            for (String[] users : Accounts){
                if (users[0].equals(Username)){
                    //If the Username the user entered already exists then UsernameCheck counter is increased.
                    UsernameCheck++;
                }
            }

            //If the counter is 0, that means the username isn't taken and can be set.
            if (UsernameCheck == 0){
                if(Username.length() < 1)
                    lp.EmptyName();
                else
                    UsernameSet = true;
            }
            else{
                //If it is taken, then give the user an option to return to login menu
                // or continue to login with new username
                lp.UsernameTaken();
                String login = sc.nextLine();
                if (login.equals("login")){
                    login();
                    return;
                }
            }
            //Repeat Do loop until a username is set which isn't taken.
        }while(!UsernameSet);

        lp.EnterPassword();
        Password = sc.nextLine();

        boolean AccountTypeSet = false;

        do {
            lp.AccountType();
            type = sc.nextLine();

            //Depending on which account type the user selected, make a different type of user.
            switch (type) {
                case "1":
                    um.createNewOrganizer(Username, Password);
                    Accounts = um.getAccountInfo();
                    AccountTypeSet = true;
                    lp.AccountMade();
                    break;
                case "2":
                    um.createNewAttendee(Username, Password);
                    Accounts = um.getAccountInfo();
                    AccountTypeSet = true;
                    lp.AccountMade();
                    break;
                case "3":
                    um.createNewSpeaker(Username, Password);
                    Accounts = um.getAccountInfo();
                    AccountTypeSet = true;
                    lp.AccountMade();
                    break;
                default:
                    lp.ValidNumber();
            }
        }while(!AccountTypeSet);
    }


    /**
     * Called to let user login to an existing account in the database.
     */
    public void login(){
        String Username;
        String Password;

        boolean UsernameExists;
        boolean PasswordExists;
        String AccountType = "";

        do {
            lp.Login();
            UsernameExists = false;
            PasswordExists = false;

            lp.EnterUsername();
            Username = sc.nextLine();

            lp.EnterPassword();
            Password = sc.nextLine();

            //Go through all existing account to see if username entered exists in the database.
            for (String[] users : Accounts){
                if (users[0].equals(Username)){
                    UsernameExists = true;
                    //If it does exist, check if the password matches.
                    if (users[1].equals(Password)){
                        PasswordExists = true;
                        AccountType = users[2];
                    }
                }
            }

            //If the username doesn't exist or password doesn't match, let the user know.
            if (!(UsernameExists && PasswordExists)){
                lp.IncorrectCredentials();
                lp.New();
                //Give user an option to return to account creation menu if they don't have an account.
                if (sc.nextLine().equals("New")){
                    CreateAccount();
                    return;
                }

            }

            //Keep looping until the user enters a set of Username and Password which match and exist in the database.
        }while(!(UsernameExists && PasswordExists));

        //Depending on the account type attached to the account, give the user access to different controllers.
        switch(AccountType){
            case "Organizer":
                this.controller = new OrganizerController(em, um, rm, mm, Username);
                break;
            case "Attendee":
                this.controller = new AttendeeController(em, um, rm, mm, Username);
                break;
            case "Speaker":
                this.controller = new SpeakerController(em, um, rm, mm, Username);
                break;
            default:
                lp.ValidNumber();
        }

        //Update the values of the login controller.
        this.em = controller.em;
        this.um = controller.um;
        this.rm = controller.rm;
        this.mm = controller.mm;

    }

}