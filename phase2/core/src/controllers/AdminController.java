package controllers;

import gateways.LoginLogGateway;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.AdminPresenter;
import usecases.LoginLogManager;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{
    private LoginLogManager llm;
    private final AdminPresenter ap;
    private LoginLogGateway llg = new LoginLogGateway();

    /**
     * Constructor for AdminController object. Uses constructor from UserController
     *
     * @param username current logged in user's username
     */
    public AdminController(String username) {
        super(username);
        this.ap = new AdminPresenter();
        this.llm = llg.deserializeData();
    }

    /**
     * Creates a new account for the specified user type
     *
     * @param register JSONObject containing user info
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject createAccount(JSONObject register) {
        this.deserializeData();

        String username = String.valueOf(register.get("username"));
        String firstName = String.valueOf(register.get("firstName"));
        String lastName = String.valueOf(register.get("lastName"));
        String password = String.valueOf(register.get("password"));
        String userType = String.valueOf(register.get("userType"));

        switch (userType) {
            case "attendee":
                return createAttendeeAccount(username, firstName, lastName, false, password, userType);
            case "organizer":
                return createOrganizerAccount(username, firstName, lastName, password, userType);
            case "speaker":
                return createSpeakerAccount(username, firstName, lastName, password, userType);
            case "vip":
                return createAttendeeAccount(username, firstName, lastName, true, password, userType);
            default:
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
    private JSONObject createAttendeeAccount(String username, String firstName, String lastName, boolean vip,
                                             String password, String userType) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewAttendee(username, firstName, lastName, vip); //create new attendee
            uam.addAccount(username, password, userType);
            this.saveData();
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
    private JSONObject createOrganizerAccount(String username, String firstName, String lastName,
                                              String password, String userType) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewOrganizer(username, firstName, lastName); //create new organizer
            uam.addAccount(username, password, userType);
            this.saveData();
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
    private JSONObject createSpeakerAccount(String username, String firstName, String lastName,
                                            String password, String userType) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, firstName, lastName); //create new speaker
            uam.addAccount(username, password, userType);
            this.saveData();
            return ap.speakerCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Lists the names of all attendees
     *
     * @return a JSON object containing all attendees
     */
    public JSONObject viewAllAttendees() {
        this.deserializeData();
        return um.getAllAttendeesJson();
    }

    /**
     * Sets an attendee as a vip
     *
     * @param username the username of the attendee to be set as a VIP
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject setAttendeeAsVip(String username) {
        this.deserializeData();

        if (!um.isVip(username)) {
            um.setAttendeeAsVip(username);
            this.saveData();
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
        this.deserializeData();

        if (um.isVip(username)) {
            um.setAttendeeAsNotVip(username);
            this.saveData();
            return ap.setNotVipResult();
        } else if (!um.isVip(username)) {
            return ap.alreadyNotVipError();
        }
        return ap.invalidAttendeeNameError();
    }

    /**
     * Removes an event with no attendees, no speakers and room from the system. Note that this should be called ONLY
     * after an Organizer has called cancelEvent() to safely delete an Event from the system.
     *
     * @param eventID the event ID
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject removeEvent(String eventID) {
        this.deserializeData();

        if(em.isEventEmpty(eventID)) {
            em.removeEvent(eventID);
            this.saveData();
            return ap.removeEventResult();
        } else {
            return ap.eventNotEmptyError();
        }
    }

    /**
     * Gets all message threads.
     *
     * @return JSONObject containing all message threads
     */
    public JSONObject getAllMessageThreads() {
        this.deserializeData();
        return ap.getAllMessageThreads(mm.getAllMessageThreadToJson());
    }

    /**
     * Delete a message thread given it's ID.
     *
     * Precondition: the messageThread exist.
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject deleteMessageThread(String messageThreadID) {
        this.deserializeData();

        mm.deleteMessage(messageThreadID);
        um.deleteMessageFromUsers(messageThreadID);
        this.saveData();
        return ap.deleteMessageResult();
    }

    /**
     * Gets all user login logs
     *
     * @return a JSONObject containing all user login logs
     */
    public JSONObject getAllUserLogs() {
        this.deserializeData();
        this.llm = llg.deserializeData();
        return ap.getAllUserLogs(llm.getAllLogsJson());
    }

    /**
     * Gets a user's login logs
     *
     * @param username the username
     * @return a JSONObject containing the user's login logs
     */
    public JSONObject getLoginLogs(String username) {
        this.deserializeData();
        this.llm = llg.deserializeData();
        return ap.getLoginLogs(llm.getLoginLogJSON(username));
    }

    /**
     * Gets all user accounts
     *
     * @return a JSONObject containing all user accounts
     */
    public JSONObject getAllAccounts() {
        this.deserializeData();
        return ap.getAllAccounts(uam.getAllAccountsJSON());
    }

    /**
     * Unlocks an user's account
     *
     * @param username the username
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject unlockAccount(String username){
        this.deserializeData();
        uam.unlockAccount(username);
        this.saveData();
        return ap.accountUnlocked();
    }
}
