package entities;

import org.json.simple.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An Entity class for an Admin that inherits from User.
 *
 * @author Edmund Lo
 *
 */
public class Admin extends User implements Serializable {

    /**
     * Constructor for an Attendee that inherits from User.
     *
     * @param username the username of the User
     * @param password the password of the User
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Admin");

        item.put("username", username);
        item.put("password", password);
        item.put("schedule", schedule);
        item.put("sent Messages", sentMessages);
        item.put("received Messages", receivedMessages);

        array.add(item);

        json.put("data", array);

        return json;
    }

}
