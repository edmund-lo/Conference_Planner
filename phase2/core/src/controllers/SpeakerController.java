package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.SpeakerPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A Controller class representing a SpeakerController which inherits from UserController.
 *
 * @author Echo Li
 * @version 1.0
 *
 */
public class SpeakerController extends UserController {
    private final SpeakerPresenter sp;

    /**
     * Constructor for SpeakerController object. Uses constructor from UserController.
     *
     * @param username current logged in user's username.
     */
    public SpeakerController(String username) {
        super(username);
        sp = new SpeakerPresenter();
    }

    /**
     * Called when user chooses to message one or more events' attendees.
     */
    public JSONObject messageEventsAttendeesCmd(JSONObject register) {
        this.deserializeData();

//        String sender = String.valueOf(register.get("sender"));
//        ArrayList recipientNames = (ArrayList)(register.get("recipients"));
//        String content = String.valueOf(register.get("content"));
//        String subject = String.valueOf(register.get("subject"));
        this.sendMessage(register);
        this.saveData();
        return sp.messageEventAttendeesMultiEventsResult();
    }

    /**
     * Messages the attendees of the given list of events and prints if message was sent or not.
     *
     * @param eventIds List of strings representing unique event IDs.
     * @param message String representing the user's message.
     */
    public JSONObject messageEventsAttendees(List<String> eventIds, String message, String subject) {
        for (String eventId: eventIds) { //loop through all event ids
            String eventName = em.getEventName(eventId);
            if (messageEventAttendees(eventId, message, subject)) {
                this.saveData();
                return sp.messageEventAttendeesResult(eventName); // if successfully messaged all attendees at event
            }
            else
                 return sp.messageEventAttendeesError(eventName);
        }
        return sp.noEventsGivenError();
    }

    /**
     * Messages the attendees of a single event.
     *
     * @param eventId String representing the unique event ID.
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageEventAttendees(String eventId, String message, String subject) {
        ArrayList<String> attendees = (ArrayList<String>) em.getAttendingUsers(eventId); //get all attendees at event with id eventId
        if (mm.messageCheck(username, message, attendees)) {
            String messageId = mm.createMessage(username, message, attendees, subject);
            addMessagesToUser(attendees, messageId);//message user with recipientName
            mp.messageResult(attendees);
        } else {
            mp.invalidMessageError();
        }

        return true;
    }

    /**
     * Gets all events that current speaker is speaking at.
     * @return List of Strings representing the events the current speaker is speaking at.
     */
    public JSONObject getSpeakerEvents() {
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventID: um.getSpeakerSchedule(this.username).keySet()){
            array.add(em.getEventJson(eventID));
        }

        return sp.getSpeakerEvents(array);
    }
}
