import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class OrganizerController extends UserController {
    public OrganizerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    public void createSpeakerAccountCmd() {
        System.out.println("Enter speaker's username:");
        String username = sc.nextLine();
        System.out.println("Enter speaker's password:");
        String password = sc.nextLine();
        createSpeakerAccount(username, password);
    }

    public boolean createSpeakerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) {
            um.createNewSpeaker(username, password);
            System.out.println("Successfully created new speaker account.");
            return true;
        }
        System.out.println("Unable to create new speaker account: username was not unique.");
        return false;
    }

    public void messageAllSpeakersCmd() {
        System.out.println("Enter your message:");
        String message = sc.nextLine();
        messageAllSpeakers(message);
    }

    public boolean messageAllSpeakers(String message) {
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String name : speakerNames) {
            String messageId = mm.sendToSpeaker(name, username, message);
            addMessagesToUser(name, messageId);
        }
        System.out.println("Successfully sent message to all speakers.");
        return true;
    }

    public void messageAllAttendeesCmd() {
        System.out.println("Enter your message:");
        String message = sc.nextLine();
        messageAllAttendees(message);
    }

    public boolean messageAllAttendees(String message) {
        Set<String> attendeeNames = um.getAllUsers().keySet();
        for (String name : attendeeNames) {
            if (!name.equals(username)) {
                String messageId = mm.sendToAttendee(name, username, message);
                addMessagesToUser(name, messageId);
            }
        }
        System.out.println("Successfully sent message to all attendees.");
        return true;
    }

    public void createRoomCmd() {
        System.out.println("Enter room's name:");
        String name = sc.nextLine();
        /*System.out.println("Enter room's capacity:");
        int capacity = parseInt(sc.nextLine());*/
        int capacity = 2;
        createRoom(name, capacity);
    }

    public boolean createRoom(String roomName, int capacity) {
        if (rm.createRoom(roomName)) {
            System.out.println("Successfully created new room.");
            return true;
        }
        System.out.println("Unable to create new room: room name was not unique.");
        return false;
    }

    public void scheduleSpeakerCmd() {
        getAllEvents();
        System.out.println("Type event number:");
        int index = parseInt(sc.nextLine());
        String eventId = new ArrayList<>(em.getAllEvents().keySet()).get(index);
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String name : speakerNames)
            System.out.println(name);
        System.out.println("Type speaker name:");
        String speakerName = sc.nextLine();
        scheduleSpeaker(speakerName, eventId);
    }

    public boolean scheduleSpeaker(String speakerName, String eventId) {
        LocalDateTime start = em.getEventById(eventId).getStartTime();
        LocalDateTime end = em.getEventById(eventId).getEndTime();
        if (!em.canAddSpeakerToEvent(speakerName, eventId)) {
            System.out.println("Another speaker is already speaking at this event.");
            return false;
        } else if (!um.speakerAvailable(start, end)) {
            System.out.println("This speaker is already speaking at another event.");
            return false;
        }
        em.addSpeakerToEvent(speakerName, eventId);
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
