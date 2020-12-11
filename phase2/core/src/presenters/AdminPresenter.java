package presenters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A Presenter class representing an AdminPresenter which inherits from UserPresenter
 *
 * @author Edmund Lo
 *
 */
public class AdminPresenter extends UserPresenter {

    public AdminPresenter() {
        super();
    }

    /**
     * Prints message that attendee has been created
     */
    public JSONObject attendeeCreationResult() {
        return pu.createJSON("success", "Attendee has been created");
    }

    /**
     * Prints message that organizer has been created
     */
    public JSONObject organizerCreationResult() {
        return pu.createJSON("success", "Organizer has been created");
    }

    /**
     * Prints message that speaker has been created
     */
    public JSONObject speakerCreationResult() {
        return pu.createJSON("success", "Speaker has been created");
    }

    /**
     * Prints message that username is already being used
     */
    public JSONObject usedNameError() {
        return pu.createJSON("error", "Username already being used");
    }

    /**
     * Prints message that user type is invalid
     */
    public JSONObject invalidUserTypeError() {
        return pu.createJSON("warning", "User type does not exist");
    }

    /**
     * Confirms attendee set as vip
     */
    public JSONObject setVipResult() {
        return pu.createJSON("success", "Attendee is now a VIP");
    }

    /**
     * Prints message that attendee is already a vip
     */
    public JSONObject alreadyVipError() {
        return pu.createJSON("error", "Attendee is already a VIP");
    }

    /**
     * Confirms attendee set as not vip
     */
    public JSONObject setNotVipResult() {
        return pu.createJSON("success", "Attendee is now not a VIP");
    }

    /**
     * Prints message that attendee is already not a vip
     */
    public JSONObject alreadyNotVipError() {
        return pu.createJSON("error", "Attendee is already not a VIP");
    }

    /**
     * Prints message that attendee does not exist
     */
    public JSONObject invalidAttendeeNameError() {
        return pu.createJSON("warning", "Attendee does not exist");
    }

    /**
     * Confirms empty event has been removed
     */
    public JSONObject removeEventResult() {
        return pu.createJSON("success", "Empty event has been removed");
    }

    /**
     * Prints message that event is not empty
     */
    public JSONObject eventNotEmptyError() {
        return pu.createJSON("error", "Event is not empty");
    }

    /**
     * Prints message that the message is deleted
     */
    public JSONObject deleteMessageResult() {
        return pu.createJSON("success", "Message is deleted.");
    }

    /**
     * Prints message that the account is unlocked
     */
    public JSONObject accountUnlocked() {
        return pu.createJSON("success", "Account has been unlocked");
    }

    /**
     * for getting all message threads in JSON format
     * @param data the message threads
     * @return a JSONObject detailing the status, result and data
     */
    public JSONObject getAllMessageThreads(JSONArray data){
        return pu.createJSON("success", "returning all message threads", data);
    }

    /**
     * for getting all login logs in JSON format
     * @param data the login logs
     * @return a JSONObject detailing the status, result and data
     */
    public JSONObject getLoginLogs(JSONArray data){
        return pu.createJSON("success", "returning all login logs", data);
    }

    public JSONObject getAllAccounts(JSONArray data){
        return pu.createJSON("success", "returning all accounts", data);
    }

    public JSONObject getAllUserLogs(JSONArray data){
        return pu.createJSON("success", "returning all user logs", data);
    }

    public JSONObject viewAllAttendees(JSONArray data){
        return pu.createJSON("success", "returning all attendees", data);
    }
}

