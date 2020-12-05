package controllers;

import entities.User;
import gateways.EventGateway;
import gateways.MessageGateway;
import gateways.RoomGateway;
import gateways.UserGateway;
import model.UserAccount;
import netscape.javascript.JSObject;
import presenters.LoginPresenter;
import usecases.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    protected UserManager um;
    protected RoomManager rm;
    protected UserController controller;
    private final LoginPresenter lp;

    //Stores logs for login. ArrayList of arrays of strings. First element is type (Login, Created Account, Logout)
    //Second element is username, third element is time.
    protected static ArrayList<String[]> loginLogs;
    protected static ArrayList<String[]> securityAns;

    /**
     * Constructor for LoginController object.
     */
    public LoginController(){
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();

        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();

        //Get list of all existing accounts from the user manager
        this.Accounts = um.getAccountInfo();
        this.lp = new LoginPresenter();
    }

    /**
     * Called to create a new account for a user.
     */
    public JSONObject CreateAccount(String Username, String Password, String type, String ans1, String ans2, String ans3,){

        int UsernameCheck = 0;

        //Loops through all existing usernames.
        for (String[] users : Accounts){
            if (users[0].equals(Username)){
                //If the Username the user entered already exists then UsernameCheck counter is increased.
                UsernameCheck++;
            }
        }
        if (Username.length() < 1)
            return lp.EmptyName();

        //If the counter is 0, that means the username isn't taken and can be set.
        if (UsernameCheck != 0)
            return lp.UsernameTaken();

        //Repeat Do loop until a username is set which isn't taken.

        if(Password.length() < 6)
            return lp.EmptyPassword();

        //Security Questions if forget password or want to reset
        lp.SecurityQuestion1();
        lp.SecurityQuestion2();
        lp.SecurityQuestion3();
        securityAns.add(new String[]{ans1, ans2, ans3});

        //Depending on which account type the user selected, make a different type of user.
        switch (type) {
            case "1":
                um.createNewOrganizer(Username, Password);
                Accounts = um.getAccountInfo();
                UpdateLogs(Username, "Account Created");
                return lp.AccountMade();
                break;
            case "2":
                um.createNewAttendee(Username, Password);
                Accounts = um.getAccountInfo();
                UpdateLogs(Username, "Account Created");
                return lp.AccountMade();
                break;
            case "3":
                um.createNewSpeaker(Username, Password);
                Accounts = um.getAccountInfo();
                UpdateLogs(Username, "Account Created");
                return lp.AccountMade();
                break;
            default:
                return lp.IncorrectCredentials();
        }
    }

    /**
     * Called to let user login to an existing account in the database.
     */
    public JSONObject login(String Username, String Password){
        boolean UsernameExists = false;
        boolean PasswordExists = false;
        String AccountType = "";

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
            UpdateLogs(Username, "Failed Login");
            if (suspiciousLogs(Username)){
                lockOut(Username);
                return lp.AccountLocked();
            }
            else
                return lp.IncorrectCredentials();
        }

        UserAccount Account = new UserAccount(Username, Password, AccountType, false, false);
        return lp.SuccessfulLogin(Account.getJSON());
    }

    //Locks user from logging in due to suspicious behaviour.
    public void lockOut(String Username){
        //TODO
    }

    //Returns true if past 3 logins were failed logins, false otherwise.
    public boolean suspiciousLogs(String Username){
        ArrayList<String> RecentLogs = new ArrayList<String>();
        for (int i = Accounts.size() - 1 ; i >= 0 ; i--) {
            if (Accounts.get(i)[0].equals(Username)) 
                RecentLogs.add(Accounts.get(i)[1]);
            if (RecentLogs.size() == 3)
                break;
        }

        for (String recentLog : RecentLogs) {
            if (!recentLog.equals("Failed Login"))
                return false;
        }

        return true;
    }

    public boolean resetPassword(String User){
        int index = -1;
        for (int i = 0; i < Accounts.toArray().length; i++) {
            if (User.equals(Accounts.get(i)[0]))
                index = i;
        }
        if(index == -1){
            lp.NoAccount();
            return false;
        }

        lp.SecurityQuestion1();
        String a1 = sc.nextLine();

        lp.SecurityQuestion2();
        String a2 = sc.nextLine();

        lp.SecurityQuestion3();
        String a3 = sc.nextLine();

        //Check if answers provided are right.
        if (securityAns.get(index)[0].equals(a1) && securityAns.get(index)[1].equals(a2) && securityAns.get(index)[2].equals(a3)){
            String pass = sc.nextLine();
            um.setPassword(User, pass);
            return true;
        }

        lp.IncorrectAnswers();
        return false;
    }

    public void UpdateLogs(String Username, String type){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat dateForm = new SimpleDateFormat(pattern);
        String dateString = dateForm.format(Calendar.getInstance().getTime());

        loginLogs.add(new String[]{type , Username, dateString});

    }

}