package Entities;

import java.io.Serializable;

public class Attendee extends User implements Serializable {

    public Attendee(String username, String password) {
        super(username, password);
    }

}
