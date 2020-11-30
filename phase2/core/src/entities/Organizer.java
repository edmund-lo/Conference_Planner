package entities;

import java.io.Serializable;

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

}
