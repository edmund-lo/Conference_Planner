package controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.SpeakerPresenter;
import usecases.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

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
    public JSONObject messageEventsAttendeesCmd(JSONArray eventIds, String message) {
        List<String> eventIDsString = new ArrayList<>();
        if(eventIds.isEmpty()){
            return sp.noSpeakerEventsError();
        }
        for(Object ID: eventIds){
            eventIDsString.add(ID.toString());
        }
        messageEventsAttendees(eventIDsString, message); //message all attendees at each event in eventIds
        this.saveData();
        return sp.messageEventAttendeesMultiEventsResult();
    }

    /**
     * Messages the attendees of the given list of events and prints if message was sent or not.
     *
     * @param eventIds List of strings representing unique event IDs.
     * @param message String representing the user's message.
     */
    public void messageEventsAttendees(List<String> eventIds, String message) {
        for (String eventId: eventIds) { //loop through all event ids
            String eventName = em.getEventName(eventId);
            if (messageEventAttendees(eventId, message)) {
                this.saveData();
                sp.messageEventAttendeesResult(eventName); // if successfully messaged all attendees at event
            }
            else
                sp.messageEventAttendeesError(eventName);
        }
    }

    /**
     * Messages the attendees of a single event.
     *
     * @param eventId String representing the unique event ID.
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageEventAttendees(String eventId, String message) {
        List<String> attendees = em.getAttendingUsers(eventId); //get all attendees at event with id eventId
        for (String recipientName : attendees) { //loop through all attendees at event
            if (mm.messageCheck(recipientName, username, message)) {
                String messageId = mm.createMessage(recipientName, username, message);
                mp.messageResult(recipientName);
                addMessagesToUser(recipientName, messageId);//message user with recipientName
            } else {
                mp.invalidMessageError();
            }
        }
        return true;
    }

    /**
     * Gets all events that current speaker is speaking at.
     * @return List of Strings representing the events the current speaker is speaking at.
     */
    public List<String> getSpeakerEvents() {
        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
        List<String> eventStrings = new ArrayList<>();
        for (String eventId : schedule.keySet()) //loop through all events in speaker's schedule
            eventStrings.add(em.getEventDescription(eventId));
        return eventStrings;
    }
}
