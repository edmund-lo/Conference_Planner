package Entities;

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
     * @param password the password of the User
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

}
