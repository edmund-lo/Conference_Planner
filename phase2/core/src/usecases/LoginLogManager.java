package usecases;

import entities.Event;
import entities.LoginLog;

import entities.Room;
import org.json.simple.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    ArrayList logArray = new ArrayList();
    private HashMap<String, ArrayList<LoginLog>> all_Logs;
    /**
     * Constructs a new empty LoginLogManager object containing no rooms.
     */
    public LoginLogManager() {
        this.all_Logs = new HashMap<>();
    }

    /**
     * Creates a new LoginLog object with an empty log and adds it into this LoginLogManager.
     * @param condition  the condition of the login log, describes the success/failure of login.
     * @param username the username of the login user
     * @param time the time of login for this user
     * @return a boolean value of true if the login log was successfully created, false otherwise.
     */
    public boolean addToLoginLogSet(String condition, String username, String time) {

        //important note: this does not handle changes to the condition
        //updates to conditions are handled elsewhere (Controllers)
        LoginLog ll = new LoginLog(condition, username, time);
        logArray.add(ll);
        if (!this.all_Logs.containsKey(username)){
            this.all_Logs.put(username, logArray);
        } else {
            if (this.all_Logs.get(username).size() == 3) {
                this.all_Logs.get(username).remove(0);
                this.all_Logs.get(username).add(ll);
            } else if (this.all_Logs.get(username).size() < 3) {
                this.all_Logs.get(username).add(ll);
            } else {
                while (this.all_Logs.get(username).size() > 3) {
                    this.all_Logs.get(username).remove(0);
                }
            }
        }
        return true;
    }

    public boolean checkLogExists(String username){
        return this.all_Logs.containsKey(username);
    }

    public ArrayList<LoginLog> getUserLogs(String Username){
        return this.all_Logs.get(Username);
    }

//    /**
//     * Removes a login log given a specified key
//     * @param username
//     * @return boolean based on whether the key-value was successfully removed
//     */
//    public boolean removeLoginLog(String username) {
//        LoginLog returned_val = null;
//        returned_val = all_Logs.remove(username);
//        if (returned_val != null) {
//            return true;
//        }
//        return false;
//    }

    /**
     * Gets a list of all the loginLogs based on username in the system.
     *
     * @return  a set containing all of the username keys
     */
    public Set<String> getAllUsernames(){
        return this.all_Logs.keySet();
    }

    public JSONObject getAllLogsJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: all_Logs.keySet())
            item.put(ID, all_Logs.get(ID).convertToJSON());

        array.add(item);

        json.put("LoginLogs", array);

        return json;
    }
}
