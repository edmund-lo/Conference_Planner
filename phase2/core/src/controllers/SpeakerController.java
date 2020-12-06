package controllers;

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
    public void messageEventsAttendeesCmd() {
        List<String> eventStrings = getSpeakerEvents();
        if (eventStrings.size() != 0) { //check to ensure there exists events that have self speaking at
            sp.speakerEventsLabel();
            sp.listEvents(eventStrings);
            List<String> eventIds = new ArrayList<>();
            while (true) { //loop until user enters valid input
                boolean crashed = false;
                sp.messageEventAttendeesPrompt();
                String eventIdsString = input.nextLine();
                try {
                    if (eventIdsString.equals("")) {
                        sp.invalidEventNumberError();
                        crashed = true;
                    } else if (eventIdsString.equals("0")) { //0 is the exit index
                        break;
                    } else { //user entered valid input
                        eventIds = new ArrayList<>(); //init new eventIds
                        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
                        List<String> allSpeakerEventIds = new ArrayList<>(schedule.keySet());
                        for (String i : eventIdsString.split(",")) { //loop through each of the user's inp. indices
                            int index;
                            try {
                                index = parseInt(i);
                                eventIds.add(allSpeakerEventIds.get(index - 1)); //add inputted event into the eventIds
                            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                sp.invalidEventNumberError();
                                crashed = true;
                            }
                        }
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sp.invalidOptionError();
                }
                if (!crashed) { //if while loop ran without any error then valid input hence leave the loop
                    break;
                }
            }
            mp.enterMessagePrompt();
            String message = input.nextLine(); //take the input msg. Note that anything can go in a msg hence no try..
            messageEventsAttendees(eventIds, message); //message all attendees at each event in eventIds
        } else
            sp.noSpeakerEventsError();
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
            if (messageEventAttendees(eventId, message)) //if successfully messaged all attendees at event
                sp.messageEventAttendeesResult(eventName);
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
                addMessagesToUser(recipientName, messageId); //message user with recipientName
            } else {
                mp.invalidMessageError();
            }
        }
        return true;
    }

    /**
     * Display all the events the speaker is speaking at
     */
    public void getDisplaySpeakerEvents() {
        sp.speakerEventsLabel();
        sp.listEvents(getSpeakerEvents());
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
