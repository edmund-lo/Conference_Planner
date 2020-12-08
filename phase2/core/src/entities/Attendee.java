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
    private boolean vip;

    /**
     * Constructor for an Attendee that inherits from User.
     *
     * @param username the username of the User
     * @param firstName the user's firstName
     * @param lastName the user's lastName
     */
    public Attendee(String username, String firstName, String lastName, boolean vip) {
        super(username, firstName, lastName);
        this.vip = vip;
    }

    /**
     * Sets the vip status of the attendee
     *
     * @param vip the vip status to be set
     */
    public void setVipStatus(boolean vip) {
        this.vip = vip;
    }

    /**
     * Checks whether attendee is a vip
     *
     * @return true iff the attendee is a vip
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Attendee");

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
        item.put("vip", vip);

        array.add(item);

        json.put("data", array);

        return json;
    }
}
