package controllers;

import org.json.simple.JSONObject;
import presenters.AdminPresenter;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{
    private final AdminPresenter ap;

    /**
     * Constructor for AdminController object. Uses constructor from UserController
     *
     * @param username current logged in user's username
     */
    public AdminController(String username) {
        super(username);
        this.ap = new AdminPresenter();
    }

    /**
     * Creates a new account for the specified user type
     *
     * @param register JSONObject containing user info
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject createAccount(JSONObject register) {
        String username = String.valueOf(register.get("username"));
        String firstName = String.valueOf(register.get("firstName"));
        String lastName = String.valueOf(register.get("lastName"));
        String userType = String.valueOf(register.get("userType"));

        if (userType.equals("attendee")) {
            return createAttendeeAccount(username, firstName, lastName);
        } else if (userType.equals("organizer")) {
            return createOrganizerAccount(username, firstName, lastName);
        } else if (userType.equals("speaker")) {
            return createSpeakerAccount(username, firstName, lastName);
        } else {
            return ap.invalidUserTypeError();
        }
    }

    /**
     * Creates a new attendee account after performing necessary checks
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     * @return a JSON object containing the status and description of the action
     */
    private JSONObject createAttendeeAccount(String username, String firstName, String lastName) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewAttendee(username, firstName, lastName); //create new attendee
            return ap.attendeeCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Creates a new organizer account after performing necessary checks
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     * @return a JSON object containing the status and description of the action
     */
    private JSONObject createOrganizerAccount(String username, String firstName, String lastName) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewOrganizer(username, firstName, lastName); //create new organizer
            return ap.organizerCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Creates a new speaker account after performing necessary checks
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     * @return a JSON object containing the status and description of the action
     */
    private JSONObject createSpeakerAccount(String username, String firstName, String lastName) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, firstName, lastName); //create new speaker
            return ap.speakerCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Lists the names of all VIPs
     *
     * @return a JSON object containing the status and description of the action and a list of all vips
     */
    public JSONObject viewAllVips() {
        return ap.listVips(um.getAllVipNames());
    }

    /**
     * Sets an attendee as a vip
     *
     * @param username the username of the attendee to be set as a VIP
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject setAttendeeAsVip(String username) {
        if (!um.isVip(username)) {
            um.setAttendeeAsVip(username);
            return ap.setVipResult();
        } else if (um.isVip(username)) {
            return ap.alreadyVipError();
        }
        return ap.invalidAttendeeNameError();
    }

    /**
     * Sets an attendee as not a vip
     *
     * @param username the username of the attendee to be set as not a VIP
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject setAttendeeAsNotVip(String username) {
        if (um.isVip(username)) {
            um.setAttendeeAsNotVip(username);
            return ap.setNotVipResult();
        } else if (!um.isVip(username)) {
            return ap.alreadyNotVipError();
        }
        return ap.invalidAttendeeNameError();
    }

    /**
     * Removes an event with no attendees
     *
     * @param eventID the event
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject removeEvent(String eventID) {
        if(em.eventIsEmpty(eventID)) {
            return ap.removeEventResult();
        } else {
            return ap.eventNotEmptyError();
        }
    }

}
