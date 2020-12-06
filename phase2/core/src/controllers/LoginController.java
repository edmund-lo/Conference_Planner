package controllers;
import entities.LoginLog;
import entities.UserAccountEntity;
import gateways.*;
import model.UserAccount;
import org.json.simple.*;

import presenters.LoginPresenter;
import usecases.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
    protected UserAccountManager uam;
    protected RoomManager rm;
    protected UserController controller;
    protected LoginLogManager llm;
    private final LoginPresenter lp;

    LoginLogGateway llg;
    UserAccountGateway uag;

    //Stores logs for login. ArrayList of arrays of strings. First element is type (Login, Created Account, Logout)
    //Second element is username, third element is time.

    /**
     * Constructor for LoginController object.
     */
    public LoginController(){
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        this.llg = new LoginLogGateway();
        this.uag = new UserAccountGateway();

        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();
        this.llm = llg.deserializeData();
        this.uam = uag.deserializeData();

        //Get list of all existing accounts from the user manager
        this.Accounts = uam.getAccountInfo();
        this.lp = new LoginPresenter();
    }

    /**
     * Called to create a new account for a user.
     */
    public JSONObject CreateAccount(String Username, String Password, String type, String q1, String ans1,
                                    String q2, String ans2, String q3, String ans3, boolean security){

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

        UserAccountEntity Account = new UserAccountEntity(Username, Password, type, false, security,
        q1, q2, q3, ans1, ans2, ans3);


        return lp.AccountMade();

        //Depending on which account type the user selected, make a different type of user.

    }

    /**
     * Called to let user login to an existing account in the database.
     */
    public JSONObject login(String Username, String Password){
        boolean UsernameExists = false;
        boolean PasswordExists = false;

        //Go through all existing account to see if username entered exists in the database.
        for (String[] users : Accounts){
            if (users[0].equals(Username)){
                UsernameExists = true;
                //If it does exist, check if the password matches.
                if (users[1].equals(Password)){
                    PasswordExists = true;
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

        UserAccountEntity Account = uam.GetUserAccount(Username);
        return lp.SuccessfulLogin(Account.getJSON());
    }

    //Locks user from logging in due to suspicious behaviour.
    public void lockOut(String Username){
        UserAccountEntity Account = uam.GetUserAccount(Username);
        Account.Lock();
    }

    //Returns true if past 3 logins were failed logins, false otherwise.
    public boolean suspiciousLogs(String Username){
        if (!llm.checkLogExists(Username))
            return false;

        ArrayList<LoginLog> RecentLogs = llm.getUserLogs(Username);
        int strike = 0;

        for (LoginLog log : RecentLogs) {
            if (log.getCondition().equals("Failed Login"))
                strike++;
        }
        return strike == 3;
    }

    public boolean resetPassword(String User, String a1, String a2, String a3){
        UserAccountEntity Account = uam.GetUserAccount(User);

        lp.SecurityQuestion1();
        lp.SecurityQuestion2();
        lp.SecurityQuestion3();

        return a1.equals(Account.getSecurityAns(1)) && a2.equals(Account.getSecurityAns(2))
                && a3.equals(Account.getSecurityAns(3));
    }

    public void UpdateLogs(String Username, String type){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat dateForm = new SimpleDateFormat(pattern);
        String dateString = dateForm.format(Calendar.getInstance().getTime());

        llm.addToLoginLogSet(type, Username, dateString);
        llg.serializeData(llm);

    }

}