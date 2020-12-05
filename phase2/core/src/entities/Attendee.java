package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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
     * @param password the password of the User
     */
    public Attendee(String username, String password) {
        super(username, password);
        this.vip = false;
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

    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Attendee");

        item.put("username", username);
        item.put("password", password);
        item.put("schedule", schedule);
        item.put("sent Messages", sentMessages);
        item.put("received Messages", receivedMessages);
        item.put("vip", vip);

        array.add(item);

        json.put("data", array);

        return json;
    }
}
