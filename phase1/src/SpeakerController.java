import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpeakerController extends UserController {
    public SpeakerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    public boolean messageEventsAttendees(List<String> eventIds, String message) {
        for (String eventId: eventIds) {
            if (messageEventAttendees(eventId, message))
                System.out.println("Successfully sent message to attendees of selected event(s).");
        }
        return true;
    }

    public boolean messageEventAttendees(String eventId, String message) {
        List<String> attendees = super.em.getEventById(eventId).getAttendingUsers();
        for (String username : attendees) {
            String messageId = super.mm.sendToAttendeeSpeakerEvent(username, super.username, message);
            super.um.addMessageToUser(username, messageId);
            super.um.addMessageToUser(super.username, messageId);
        }
        return true;
    }

    public List<String> getSpeakerEvents() {
        Map<String, LocalDateTime[]> schedule = super.um.getSpeakerSchedule(super.username);
        List<String> eventStrings = new ArrayList<>();
        for (String eventId : schedule.keySet()) {
            String eventString = super.em.getEventById(eventId).toString();
            eventStrings.add(eventString);
            System.out.println((eventString));
        }
        return eventStrings;
    }
}
