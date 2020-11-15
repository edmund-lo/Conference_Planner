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
            sp.displayMenu(um);
            String option = input.nextLine();
            switch(option) {
                case "0":
                    logout();
                    inSession = false;
                    break;
                case "1":
                    signUpMenu();
                    break;
                case "2":
                    cancelMenu();
                    break;
                case "3":
                    messageMenu();
                    break;
                case "4":
                    viewEventsMenu();
                    break;
                case "5":
                    messageEventsAttendeesCmd();
                    break;
                case "6":
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
        if (eventIdsString.equals("")) {
            sp.invalidEventNumberError();
        } else {
            List<String> eventIds = new ArrayList<>();
            Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
            List<String> allSpeakerEventIds = new ArrayList<>(schedule.keySet());
            for (String i : eventIdsString.split(",")) {
                int index;
                try {
                    index = parseInt(i);
                } catch (NumberFormatException e) {
                    sp.invalidEventNumberError();
                    continue;
                }
                eventIds.add(allSpeakerEventIds.get(index));
            }
            mp.enterMessagePrompt();
            String message = input.nextLine();
            messageEventsAttendees(eventIds, message);
        }
    }

    /**
     * Messages the attendees of the given list of events and prints if message was sent or not.
     *
     * @param eventIds List of strings representing unique event IDs.
     * @param message String representing the user's message.
     */
    public void messageEventsAttendees(List<String> eventIds, String message) {
        for (String eventId: eventIds) {
            String eventName = em.getEventName(eventId);
            if (messageEventAttendees(eventId, message))
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
        List<String> attendees = em.getAttendingUsers(eventId);
        for (String recipientName : attendees) {
            if (mm.messageCheck(recipientName, username, message)) {
                String messageId = mm.createMessage(recipientName, username, message);
                mp.messageResult(recipientName);
                addMessagesToUser(recipientName, messageId);
            } else {
                mp.invalidMessageError();
            }
        }
        return true;
    }

    /**
     * Prints to console a list of events that this speaker is speaking at.
     *
     */
    public void getSpeakerEvents() {
        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
        List<String> eventStrings = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventStrings.add(em.getEventDescription(eventId));
        sp.speakerEventsLabel();
        sp.listEvents(eventStrings);
    }
}
