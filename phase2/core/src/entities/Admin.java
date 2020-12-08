package entities;

import org.json.simple.*;
import java.io.Serializable;

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
     * @param firstName the user's firstName
     * @param lastName the user's lastName
     */
    public Admin(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Admin");

        item.put("username", getUsername());
        item.put("firstName", getFirstName());
        item.put("lastName", getLastName());
        item.put("schedule", getSchedule());
        item.put("sent requests", getSentRequest());
        item.put("friend requests", getFriendRequest());
        item.put("friends list", getFriendsList());
        item.put("primary inbox", getPrimaryInbox());
        item.put("archived inbox", getArchivedInbox());
        item.put("trash inbox", getTrashInbox());

        array.add(item);

        json.put("data", array);

        return json;
    }

}
