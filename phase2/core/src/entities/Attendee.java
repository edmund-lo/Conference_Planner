package entities;

import org.json.simple.*;
import java.io.Serializable;

/**
 * An Entity class for an Attendee that inherits from User.
 *
 * @author Edmund Lo
 *
 */
public class Attendee extends User implements Serializable {

    /**
     * Constructor for an Attendee that inherits from User.
     *
     * @param username the username of the User
     * @param firstName the user's firstName
     * @param lastName the user's lastName
     */
    public Attendee(String username, String firstName, String lastName, boolean vip) {
        super(username, firstName, lastName);
        setVipStatus(vip);
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject item = new JSONObject();

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
        item.put("vip", isVip());

        return item;
    }
}
