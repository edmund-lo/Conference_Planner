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

    /**
     * Constructor for SpeakerController object.
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public SpeakerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    /**
     * Called when user chooses to message one or more events' attendees.
     */
    public void messageEventsAttendeesCmd() {
        getSpeakerEvents();
        System.out.println("Enter the event numbers separated by a comma:");
        String eventIdsString = sc.nextLine();
        List<String> eventIds = new ArrayList<>();
        Map<String, LocalDateTime[]> schedule = um.getSpeakerSchedule(username);
        List<String> allSpeakerEventIds = new ArrayList<>(schedule.keySet());
        for (String i : eventIdsString.split(",")) {
            int index = parseInt(i);
            eventIds.add(allSpeakerEventIds.get(index));
        }
        System.out.println("Enter your message:");
        String message = sc.nextLine();
        messageEventsAttendees(eventIds, message);
    }

    /**
     * Messages the attendees of the given list of events.
     *
     * @param eventIds List of strings representing unique event IDs.
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageEventsAttendees(List<String> eventIds, String message) {
        for (String eventId: eventIds) {
            if (messageEventAttendees(eventId, message))
                System.out.println("Successfully sent message to attendees of selected event(s).");
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
        List<String> attendees = em.getEventById(eventId).getAttendingUsers();
        for (String name : attendees) {
            String messageId = mm.sendToAttendeeSpeakerEvent(name, username, message);
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
        int count = 1;
        for (String eventId : schedule.keySet()) {
            String eventString = em.getEventById(eventId).toString();
            eventStrings.add(eventString);
            System.out.println((count + ": " + eventString));
        }
        return eventStrings;
    }
}
