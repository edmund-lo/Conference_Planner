package controllers;
import entities.LoginLog;
import entities.UserAccountEntity;
import gateways.*;
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
 * @version 2.0
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

    /**
     * Constructor for LoginController object.
     */
    public LoginController(){
        //The gateways that will be used to serialize data
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        this.llg = new LoginLogGateway();
        this.uag = new UserAccountGateway();

        //Use gateways to initialize all use cases and managers
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
        //Check for whitespace
        if (Username.contains(" "))
            return lp.noWhiteSpace();
        //Check Username Minimum Length
        if (Username.length() < 1)
            return lp.EmptyName();

        //If the counter is 0, that means the username isn't taken and can be set.
        if (UsernameCheck != 0)
            return lp.UsernameTaken();

        //Check minimum password length for security
        if(Password.length() < 6)
            return lp.EmptyPassword();

        //Security Questions if forget password or want to reset
        lp.SecurityQuestion1();
        lp.SecurityQuestion2();
        lp.SecurityQuestion3();

        //Add account to the user manager and update the Accounts Arraylist
        uam.addAccount(Username, Password, type, security,
                q1, q2, q3, ans1, ans2, ans3);
        this.Accounts = uam.getAccountInfo();

        return lp.AccountMade();
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

        //If the username doesn't exist or password doesn't match, log a failed login.
        if (!(UsernameExists && PasswordExists)){
            UpdateLogs(Username, "Failed Login");
            //If past 3 logs are failed logins, lock the account.
            if (suspiciousLogs(Username)){
                lockOut(Username);
                return lp.AccountLocked();
            }
            else
                return lp.IncorrectCredentials();
        }

        UserAccountEntity Account = uam.getUserAccount(Username);
        //If account is locked, don't let the user login.
        if(Account.isLocked())
            return lp.AccountLocked();

        return lp.SuccessfulLogin(Account.getJSON());
    }

    /**
     * Locks a user, which prevents them from logging in until an Admin unlocks their account.
     */
    public void lockOut(String Username){
        UserAccountEntity Account = uam.getUserAccount(Username);
        Account.Lock();
        uam.updateAccount(Username, Account);
    }

    /**
     * Returns true if past 3 logins were failed logins, false otherwise.
     */
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

    /**
     * Lets user change password if they can answer 3 security questions correctly which were set when making an account.
     */
    public boolean resetPassword(String User, String a1, String a2, String a3, String NewPassword){
        UserAccountEntity Account = uam.getUserAccount(User);

        lp.SecurityQuestion1();
        lp.SecurityQuestion2();
        lp.SecurityQuestion3();

        if(a1.equals(Account.getSecurityAns(1)) && a2.equals(Account.getSecurityAns(2))
                && a3.equals(Account.getSecurityAns(3))){
            Account.setPassword(NewPassword);
            uam.updateAccount(Account.getUsername(), Account);
            return true;
        }
        return false;
    }

    /**
     * Update the logs database.
     */
    public void UpdateLogs(String Username, String type){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat dateForm = new SimpleDateFormat(pattern);
        String dateString = dateForm.format(Calendar.getInstance().getTime());

        llm.addToLoginLogSet(type, Username, dateString);
        llg.serializeData(llm);

    }

}