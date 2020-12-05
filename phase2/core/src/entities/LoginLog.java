package entities;
import org.json.simple.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * An Entity class for a Login Log.
 * @author dylanbaptist
 * @version 1.0
 */
public class LoginLog implements Serializable {
    private final ArrayList<String>  logs = new ArrayList<>();
    /**
     * Constructor for a LoginLog.
     *
     * @param condition the type and condition of the log
     * @param username the username of the User
     * @param time the time of login

     */
    public LoginLog(String condition, String username, String time) {
        logs.set(0, condition);
        logs.set(1, username);
        logs.set(2, time);
    }
    /**
     * getter for login logs
     *
     * @return The login logs as an ArrayList
     */
    public ArrayList<String> getLoginLogs() {
        return logs;
    }

    /**
     * gets the condition of the login logs
     * Example: "Successful Login"
     * @return type of login condition
     */
    public String getCondition() {
        return logs.get(0);
    }
    /**
     * gets the username stored in the login log
     * @return username
     */
    public String getUsername() {
        return logs.get(1);
    }
    /**
     * gets the time of login
     * @return password
     */
    public String getTime() {
        return logs.get(2);
    }
    public String toString() {
        return this.getCondition() + " The username is " + this.getUsername() +
                ". The time of login is " + this.getTime() + ".";
    }
}
