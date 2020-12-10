package usecases;

import entities.LoginLog;
import org.json.simple.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A Use Case class that stores the login logs with key as username and values
 * as an extendable Array List
 *
 * @author Dylan Baptist
 * @version 1.0
 *
 */
public class LoginLogManager implements Serializable {
    private HashMap<String, ArrayList<LoginLog>> allLogs;

    /**
     * Constructs a new empty LoginLogManager object containing no logs.
     */
    public LoginLogManager() {
        this.allLogs = new HashMap<>();
    }

    /**
     * Creates a new LoginLog object with an empty log and adds it into this LoginLogManager.
     * @param condition  the condition of the login log, describes the success/failure of login.
     * @param username the username of the login user
     * @param time the time of login for this user
     * @return a boolean value of true if the login log was successfully created, false otherwise.
     */
    public boolean addToLoginLogSet(boolean condition, String username, LocalDateTime time) {

        //important note: this does not handle changes to the condition
        //updates to conditions are handled elsewhere (Controllers)
        LoginLog ll = new LoginLog(condition, username, time);

        if (!this.allLogs.containsKey(username)){
            ArrayList logArray = new ArrayList();
            logArray.add(ll);
            this.allLogs.put(username, logArray);
        } else {
            if (this.allLogs.get(username).size() == 3) {
                this.allLogs.get(username).remove(0);
                this.allLogs.get(username).add(ll);
            } else if (this.allLogs.get(username).size() < 3) {
                this.allLogs.get(username).add(ll);
            } else {
                while (this.allLogs.get(username).size() > 3) {
                    this.allLogs.get(username).remove(0);
                }
            }
        }
        return true;
    }

    /**
     * Checks if login logs exist for a user
     * @param username the username
     * @return true iff login logs exist
     */
    public boolean checkLogExists(String username){
        return this.allLogs.containsKey(username);
    }

    /**
     * Checks if login logs are suspicious
     * @param username the username
     * @return true iff login logs are suspicious
     */
    public boolean suspiciousLogs(String username) {
        int strike = 0;

        for (LoginLog log : allLogs.get(username)) {
            if (log.getCondition() == false) {
                strike++;
            }
        }

        return strike == 3;
    }

    /**
     * Gets all logs JSON
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject getAllLogsJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONArray array2 = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allLogs.keySet()) {
            int num = 0;
            JSONObject item2 = new JSONObject();
            for(LoginLog l: allLogs.get(ID)){
                item2.put(num, l.convertToJSON());
                num++;
            }
            array2.add(item2);
            item.put(ID, array2);
        }

        array.add(item);

        json.put("LoginLogs", array);

        return json;
    }

    /**
     * Gets login log json for a user
     * @param username the username
     * @return  A JSONObject that contains the JSON representation of this class
     */
    public JSONObject getLoginLogJSON(String username) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        int num = 0;

        for(LoginLog l: allLogs.get(username)){
            item.put(num, l.convertToJSON());
            num++;
        }

        array.add(item);

        json.put("LoginLogs", array);

        return json;
    }
}
