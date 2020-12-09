package controllers;

import entities.LoginLog;
import gateways.LoginLogGateway;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.AdminPresenter;
import usecases.LoginLogManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{
    private LoginLogManager llm;
    private final AdminPresenter ap;
    private LoginLogGateway llg;

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
        String username = String.valueOf(register.get("username"));
        String firstName = String.valueOf(register.get("firstName"));
        String lastName = String.valueOf(register.get("lastName"));
        String userType = String.valueOf(register.get("userType"));
        String password = String.valueOf(register.get("password"));
        boolean vip = (boolean) register.get("vip");

        switch (userType) {
            case "attendee":
                uam.addAccount(username, password, userType);
                this.saveData();
                return createAttendeeAccount(username, firstName, lastName, vip);
            case "organizer":
                uam.addAccount(username, password, userType);
                this.saveData();
                return createOrganizerAccount(username, firstName, lastName);
            case "speaker":
                uam.addAccount(username, password, userType);
                this.saveData();
                return createSpeakerAccount(username, firstName, lastName);
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
    private JSONObject createAttendeeAccount(String username, String firstName, String lastName, boolean vip) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewAttendee(username, firstName, lastName, vip); //create new attendee
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
    private JSONObject createOrganizerAccount(String username, String firstName, String lastName) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewOrganizer(username, firstName, lastName); //create new organizer
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
    private JSONObject createSpeakerAccount(String username, String firstName, String lastName) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, firstName, lastName); //create new speaker
            this.saveData();
            return ap.speakerCreationResult();
        }
        return ap.usedNameError();
    }

    /**
     * Lists the names of all attendees
     *
     * @return a JSON object containing the status and description of the action and a list of all attendees
     */
    public JSONObject viewAllAttendees() {
        return um.getAllAttendeesJson();
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
        if(em.isEventEmpty(eventID)) {
            em.removeEvent(eventID);
            return ap.removeEventResult();
        } else {
            return ap.eventNotEmptyError();
        }
    }

    /**
     * Gets all of messageThreads.
     *
     * @return List of Strings representing all of the messageThreads.
     */
    public JSONArray getAllMessageThreads(){
        JSONArray messageStrings = new JSONArray();
        List<String> messageIds = (List<String>) mm.getAllMessageThreads().keySet();
        for (String id : messageIds) {
            messageStrings.add(mm.getOneMessageThreadToJson(id));
        }
        return messageStrings;
    }

    /**
     * Delete a messageThread given it's Id.
     *
     * Precondition: the messageThread exist.
     */
    public void deleteMessageThread(String messageThreadId){
        mm.deleteMessage(messageThreadId);
        um.deleteMessageFromUsers(messageThreadId);
    }

    public JSONObject getAllUserLogs() {
        return llm.getAllLogsJson();
    }

    public JSONObject unlockAccount(String username){
        uam.unlockAccount(username);
        this.saveData();
        return ap.accountUnlocked();
    }
}
