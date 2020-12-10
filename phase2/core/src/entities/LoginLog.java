package entities;
import org.json.simple.*;
import java.io.Serializable;
import java.util.ArrayList;

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

    /**
     * toString for the login log
     * @return a string of the login log
     */
    public String toString() {
        return this.getCondition() + " The username is " + this.getUsername() +
                ". The time of login is " + this.getTime() + ".";
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "LoginLog");

        item.put("condition", this.getCondition());
        item.put("username", this.getUsername());
        item.put("time", this.getTime());


        array.add(item);

        json.put("data", array);

        return json;
    }
}
