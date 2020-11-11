import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrganizerController extends UserController {
    public OrganizerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    public boolean createSpeakerAccount(String username, String password, String name) {
        if (super.um.checkUniqueUsername(username)) {
            super.um.createNewSpeaker(username, password);
            System.out.println("Successfully created new speaker account.");
            return true;
        }
        System.out.println("Unable to create new speaker account: username was not unique.");
        return false;
    }

    public boolean messageAllSpeakers(String message) {
        List<String> speakerNames = super.um.getAllSpeakerNames();
        for (String username : speakerNames) {
            String messageId = super.mm.sendToSpeaker(username, super.username, message);
            super.addMessagesToUser(username, messageId);
        }
        System.out.println("Successfully sent message to all speakers.");
        return true;
    }

    public boolean messageAllAttendees(String message) {
        Set<String> attendeeNames = super.um.getAllUsers().keySet();
        for (String username : attendeeNames) {
            if (!username.equals(super.username)) {
                String messageId = super.mm.sendToAttendee(username, super.username, message);
                super.addMessagesToUser(username, messageId);
            }
        }
        System.out.println("Successfully sent message to all speakers.");
        return true;
    }

    public boolean createRoom(String roomName, int capacity) {
        if (super.rm.createRoom(roomName)) {
            System.out.println("Successfully created new room.");
            return true;
        }
        System.out.println("Unable to create new room: room name was not unique.");
        return false;
    }

    public boolean scheduleSpeaker(String speakerName, String eventId) {
        LocalDateTime start = super.em.getEventById(eventId).getStartTime();
        LocalDateTime end = super.em.getEventById(eventId).getEndTime();
        if (!super.em.canAddSpeakerToEvent(speakerName, eventId)) {
            System.out.println("Another speaker is already speaking at this event.");
            return false;
        } else if (!super.um.speakerAvailable(start, end)) {
            System.out.println("This speaker is already speaking at another event.");
            return false;
        }
        super.em.addSpeakerToEvent(speakerName, eventId);
        System.out.println("Successfully added speaker to selected event.");
        return true;
    }

    /*public boolean createEvent() {
        return true;
    }

    public boolean cancelEvent(String eventId) {
        return true;
    }

    public boolean rescheduleEvent(String eventId, LocalDateTime newStart, LocalDateTime newEnd) {
        return true;
    }*/
}
