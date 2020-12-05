package entities;

import org.json.simple.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An Entity class for an Organizer that inherits from User.
 *
 * @author Edmund Lo
 *
 */
public class Organizer extends User implements Serializable {

    /**
     * Constructor for an Organizer that inherits from User.
     *
     * @param username the username of the User
     * @param password the password of the User
     */
    public Organizer(String username, String password) {
        super(username, password);
    }

    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Organizer");

        item.put("username", getUsername());
        item.put("password", getPassword());
        item.put("schedule", getSchedule());
        item.put("sentMessages", getSentMessages());
        item.put("received Messages", getReceivedMessages());

        array.add(item);

        json.put("data", array);

        return json;
    }
}
