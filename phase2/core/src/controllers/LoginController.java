package controllers;

import gateways.LoginLogGateway;
import gateways.UserAccountGateway;
import gateways.UserGateway;
import org.json.simple.JSONObject;
import presenters.LoginPresenter;
import usecases.LoginLogManager;
import usecases.UserAccountManager;
import usecases.UserManager;

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
    private ArrayList<String[]> accounts;
    protected UserAccountManager uam;
    protected LoginLogManager llm;
    private final LoginPresenter lp;

    private final LoginLogGateway llg;
    private final UserAccountGateway uag;

    /**
     * Constructor for LoginController object.
     */
    public LoginController(){
        //The gateways that will be used to serialize data
        this.llg = new LoginLogGateway();
        this.uag = new UserAccountGateway();

        //Use gateways to initialize all use cases and managers
        this.llm = llg.deserializeData();
        this.uam = uag.deserializeData();

        //Get list of all existing accounts from the user manager
        this.accounts = uam.getAccountInfo();
        this.lp = new LoginPresenter();
    }

    /**
     * Called to create a new account for a user.
     */
    public JSONObject createAccount(JSONObject obj, boolean isMade){

        // String Username, String Password, String type, String q1, String ans1,
        //                                    String q2, String ans2, String q3, String ans3, boolean security

        String Username = obj.get("username").toString();
        String Password = obj.get("password").toString();
        String type = obj.get("userType").toString();
        String q1 = obj.get("securityQuestion1").toString();
        String ans1 = obj.get("securityAnswer1").toString();
        String q2 = obj.get("securityQuestion2").toString();
        String ans2 = obj.get("securityAnswer2").toString();
        boolean security = (Boolean) obj.get("setup");

        String firstName = obj.get("firstName").toString();
        String lastName = obj.get("lastName").toString();



        int UsernameCheck = 0;
        //Loops through all existing usernames.
        for (String[] users : accounts){
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

        //Add account to the user manager and update the Accounts Arraylist


        if (!isMade){
            uam.addAccount(Username, Password, type, security,
                    q1, q2, ans1, ans2);
            uag.serializeData(uam);
            this.accounts = uam.getAccountInfo();

            UserManager um = new UserManager();
            UserGateway ug = new UserGateway();

            switch (type) {
                case "attendee":
                    uam.addAccount(Username, Password, type);
                    um.createNewAttendee(Username, firstName, lastName, false);
                    ug.serializeData(um);
                    return lp.AccountMade();
                case "organizer":
                    uam.addAccount(Username, Password, type);
                    um.createNewOrganizer(Username, firstName, lastName);
                    ug.serializeData(um);
                    return lp.AccountMade();
                case "speaker":
                    uam.addAccount(Username, Password, type);
                    um.createNewSpeaker(Username, firstName, lastName);
                    ug.serializeData(um);
                    return lp.AccountMade();
                case "admin":
                    uam.addAccount(Username, Password, type);
                    um.createNewAdmin(Username, firstName, lastName);
                    ug.serializeData(um);
                    return lp.AccountMade();
                default:
                    return lp.IncorrectCredentials();
            }
        }
        else{
            uam.setPassword(Username, Password);
            uam.setUserType(Username, type);
            uam.setSetSecurity(Username, security);
            uam.setSecurityQuestions(Username, q1, ans1, q2, ans2);

            this.accounts = uam.getAccountInfo();

            return lp.AccountExists();
        }
    }

    /**
     * Called to let user login to an existing account in the database.
     */
    public JSONObject login(String Username, String Password){
        boolean UsernameExists = false;
        boolean PasswordExists = false;

        //Go through all existing account to see if username entered exists in the database.
        for (String[] users : accounts){
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
            updateLogs(Username, "Failed Login");
            //If past 3 logs are failed logins, lock the account.
            if (suspiciousLogs(Username)){
                lockOut(Username);
                return lp.AccountLocked();
            }
            else
                return lp.IncorrectCredentials();
        }

        //If account is locked, don't let the user login.
        if(uam.isLocked(Username))
            return lp.AccountLocked();

        return lp.SuccessfulLogin(uam.getAccountJSON(Username));
    }

    /**
     * Locks a user, which prevents them from logging in until an Admin unlocks their account.
     */
    public void lockOut(String Username){
        uam.lockAccount(Username);
        uag.serializeData(uam);
    }

    /**
     * Returns true if past 3 logins were failed logins, false otherwise.
     */
    public boolean suspiciousLogs(String Username){
        //Check if user has logs
        if (!llm.checkLogExists(Username))
            return false;

        return llm.suspiciousLogs(Username);
    }

    /**
     * Lets user change password if they can answer 3 security questions correctly which were set when making an account.
     */
    public JSONObject verifySecurityAnswers(String User, String a1, String a2){
        String[] answers = uam.getSecurityAns(User);

        lp.SecurityQuestion1();
        lp.SecurityQuestion2();

        //Check if answers to security questions match.
        if (a1.equals(answers[1]) && a2.equals(answers[2]))
            return lp.correctAnswers();
        else
            return lp.incorrectAnswers();
    }

    public JSONObject resetPassword(String user, String newPassword, String confirmPassword){

        //Update password
        if (newPassword.equals(confirmPassword)){
            uam.setPassword(user, newPassword);
            uag.serializeData(uam);
            return lp.passwordChanged();
        } else
            return lp.passwordsDontMatch();

    }

    /**
     * Update the logs database.
     */
    public void updateLogs(String Username, String type){
        //Get current time and convert it to string/
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat dateForm = new SimpleDateFormat(pattern);
        String dateString = dateForm.format(Calendar.getInstance().getTime());

        //Add log.
        llm.addToLoginLogSet(type, Username, dateString);
        llg.serializeData(llm);

    }

    public JSONObject accountJson(String username){
        return lp.accountLogs(uam.getAccountJSON(username));
    }

}