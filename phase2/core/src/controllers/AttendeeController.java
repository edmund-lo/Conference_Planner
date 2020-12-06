package controllers;

import org.json.simple.JSONObject;
import presenters.AttendeePresenter;

import java.time.LocalDateTime;

/**
 * A Controller class representing an AttendeeController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AttendeeController extends UserController {

    private final AttendeePresenter ap;

    /**
     * Constructor for AttendeeController object. Uses constructor from UserController
     *
     * @param username current logged in user's username
     */
    public AttendeeController(String username) {
        super(username);
        this.ap = new AttendeePresenter();
    }

    /**
     * Lists all Vip Events
     *
     * @return a JSON object containing the status and description of the action and a list of all vip events
     */
    public JSONObject viewAllVipEvents() {
        return ap.listVipEvents(em.getAllVipEvents());
    }

    /**
     * Signs up an attendee for a vip event
     *
     * @param eventID the vip event
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject signUpVipEventAttendance(String eventID) {
        LocalDateTime start = em.getEventStartTime(eventID);
        LocalDateTime end = em.getEventEndTime(eventID);
        if (!um.isVip(username)) {
            return ap.notVipError();
        } else if (!um.canSignUp(username, eventID, start, end)) {
            return ap.alreadySignedUpError();
        } else if (!em.canAddUserToEvent(eventID,username)){
            return ap.eventFullCapacityError();
        } else {
            em.addUserToEvent(eventID,username);
            um.signUp(username, eventID, start, end);
            return ap.signUpVipResult(em.getEventName(eventID));
        }
    }

    /**
     * Cancels an attendee's attendance in a vip event
     *
     * @param eventID the vip event
     * @return a JSON object containing the status and description of the action
     */
    public JSONObject cancelVipEventAttendance(String eventID) {
        if(em.removeUserFromEvent(eventID, username)) {
            um.cancel(username, eventID);
            return ap.cancelVipResult(em.getEventName(eventID));
        }
        return ap.notAttendingEventError(em.getEventName(eventID));
    }

}
