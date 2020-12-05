package controllers;

import org.json.simple.JSONObject;
import presenters.AdminPresenter;
import usecases.EventManager;
import usecases.MessageManager;
import usecases.RoomManager;
import usecases.UserManager;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{
    private final AdminPresenter ap;

    /**
     * Constructor for AdminController object. Uses constructor from UserController.
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public AdminController(username) {
        super(username);
        this.ap = new AdminPresenter();
    }

    /**
     * Creates a new attendee account after performing necessary checks.
     *
     * @param register JSONObject containing user info.
     */
    public JSONObject createAttendeeAccount(JSONObject register) {
        String username = String.valueOf(register.get("username"));
        String password = String.valueOf(register.get("password"));

        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewAttendee(username, password); //create new attendee
            return ap.attendeeCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Creates a new organizer account after performing necessary checks.
     *
     * @param register JSONObject containing user info.
     */
    public JSONObject createOrganizerAccount(JSONObject register) {
        String username = String.valueOf(register.get("username"));
        String password = String.valueOf(register.get("password"));

        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewOrganizer(username, password); //create new organizer
            return ap.organizerCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Creates a new speaker account after performing necessary checks.
     *
     * @param register JSONObject containing user info.
     */
    public JSONObject createSpeakerAccount(JSONObject register) {
        String username = String.valueOf(register.get("username"));
        String password = String.valueOf(register.get("password"));

        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, password); //create new speaker
            return ap.speakerCreationResult();
        }
        return ap.usedNameError();
    }

    public JSONObject viewAllVips() {
        return ap.listVips(um.getAllVipNames());
    }

    public JSONObject setAttendeeAsVip(String username) {
        if (!um.isVip(username)) {
            um.setAttendeeAsVip(username);
            return ap.setVipResult();
        } else if (um.isVip(username)) {
            return ap.alreadyVipError();
        }
        return ap.invalidAttendeeNameError();
    }

    public JSONObject setAttendeeAsNotVip() {
        if (um.isVip(username)) {
            um.setAttendeeAsNotVip(username);
            return ap.setNotVipResult();
        } else if (!um.isVip(username)) {
            return ap.alreadyNotVipError();
        }
        return ap.invalidAttendeeNameError();
    }

}
