package Controllers;

import Presenters.SpeakerPresenter;
import UseCases.*;

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
    private SpeakerPresenter sp;

    /**
     * Constructor for SpeakerController object. Uses constructor from UserController.
     *
     * @param em  current session's UseCases.EventManager class.
     * @param um  current session's UseCases.UserManager class.
     * @param rm  current session's UseCases.RoomManager class.
     * @param mm  current session's UseCases.MessageManager class.
     * @param username current logged in user's username.
     */
    public SpeakerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
        sp = new SpeakerPresenter();

        boolean inSession = true;
        // Enters a while loop that allows the user to continuously use Speaker and Attendee functions
        while(inSession) {
            sp.displayMenu();
            int option = parseInt(input.nextLine());
            switch(option) {
                case 0:
                    logout();
                    inSession = false;
                    break;
                case 1:
                    signUpMenu();
                    break;
                case 2:
                    cancelMenu();
                    break;
                case 3:
                    messageMenu();
                    break;
                case 4:
                    messageEventsAttendeesCmd();
                    break;
                case 5:
                    getSpeakerEvents();
                    break;
                default:
                    sp.invalidOptionError();
                    break;
            }
        }
    }

    /**
     * Called when user chooses to message one or more events' attendees.
     */
    public void messageEventsAttendeesCmd() {
        getSpeakerEvents();
        sp.messageEventAttendeesPrompt();
        String eventIdsString = input.nextLine();
        List<String> eventIds = new ArrayList<>();
        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
        List<String> allSpeakerEventIds = new ArrayList<>(schedule.keySet());
        for (String i : eventIdsString.split(",")) {
            int index;
            try {
                index = parseInt(i);
            } catch (NumberFormatException e) {
                sp.invalidEventNumber();
                continue;
            }
            eventIds.add(allSpeakerEventIds.get(index));
        }
        sp.enterMessagePrompt();
        String message = input.nextLine();
        messageEventsAttendees(eventIds, message);
    }

    /**
     * Messages the attendees of the given list of events and prints if message was sent or not.
     *
     * @param eventIds List of strings representing unique event IDs.
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageEventsAttendees(List<String> eventIds, String message) {
        for (String eventId: eventIds) {
            String eventName = em.getEventName(eventId);
            if (messageEventAttendees(eventId, message))
                sp.messageEventAttendeesResult(eventName);
            else
                sp.messageEventAttendeesError(eventName);
        }
        return true;
    }

    /**
     * Messages the attendees of a single event.
     *
     * @param eventId String representing the unique event ID.
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageEventAttendees(String eventId, String message) {
        List<String> attendees = em.getAttendingUsers(eventId);
        for (String name : attendees) {
            String messageId = mm.sendMessage(name, username, message);
            addMessagesToUser(name, messageId);
        }
        return true;
    }

    /**
     * Prints to console a list of events that this speaker is speaking at.
     *
     * @return A List of Strings representing the events that the speaker is speaking at.
     */
    public List<String> getSpeakerEvents() {
        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
        List<String> eventStrings = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventStrings.add(em.getEventDescription(eventId));
        sp.speakerEventsLabel();
        sp.listEvents(eventStrings);
        return eventStrings;
    }
}
