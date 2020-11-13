import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * A Controller class representing a OrganizerController which inherits from UserController.
 *
 * @author Echo Li
 * @version 1.0
 *
 */
public class OrganizerController extends UserController {

    /**
     * Constructor for OrganizerController object. Uses constructor from UserController.
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public OrganizerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);

        boolean inSession = true;
        while(inSession) {
            OrganizerPresenter.displayMenu();
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
                    createSpeakerAccountCmd();
                    break;
                case 5:
                    messageAllAttendeesCmd();
                    break;
                case 6:
                    messageAllSpeakersCmd();
                    break;
                case 7:
                    scheduleSpeakerCmd();
                    break;
                case 8:
                    createRoomCmd();
                    break;
                default:
                    System.out.println("Please enter a valid option!");
                    break;
            }
        }
    }

    /**
     * Called when user chooses to create a new speaker user account.
     */
    public void createSpeakerAccountCmd() {
        System.out.println("Enter speaker's username:");
        String username = input.nextLine();
        System.out.println("Enter speaker's password:");
        String password = input.nextLine();
        createSpeakerAccount(username, password);
    }

    /**
     * Creates a new speaker account after performing necessary checks.
     *
     * @param username String representing new speaker's username.
     * @param password String representing new speaker's password.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean createSpeakerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) {
            um.createNewSpeaker(username, password);
            System.out.println("Successfully created new speaker account.");
            return true;
        }
        System.out.println("Unable to create new speaker account: username was not unique.");
        return false;
    }

    /**
     * Called when user chooses to message all speakers.
     */
    public void messageAllSpeakersCmd() {
        System.out.println("Enter your message:");
        String message = input.nextLine();
        messageAllSpeakers(message);
    }

    /**
     * Messages all speakers.
     *
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean messageAllSpeakers(String message) {
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String name : speakerNames) {
            String messageId = mm.sendToSpeaker(name, username, message);
            addMessagesToUser(name, messageId);
        }
        System.out.println("Successfully sent message to all speakers.");
        return true;
    }

    /**
     * Called when user chooses to message all attendees.
     */
    public void messageAllAttendeesCmd() {
        System.out.println("Enter your message:");
        String message = input.nextLine();
        messageAllAttendees(message);
    }

    /**
     * Messages all attendees.
     *
     * @param message String representing the user's message.
     * @return A boolean value signifying whether method was successful.
     */
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

    /**
     * Called when user chooses to create a new room.
     */
    public void createRoomCmd() {
        System.out.println("Enter room's name:");
        String name = input.nextLine();
        /*System.out.println("Enter room's capacity:");
        int capacity = parseInt(sc.nextLine());*/
        int capacity = 2;
        createRoom(name, capacity);
    }

    /**
     * Creates a mew room.
     *
     * @param roomName String representing the new room's name.
     * @param capacity Integer representing the new room's capacity.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean createRoom(String roomName, int capacity) {
        if (rm.createRoom(roomName)) {
            System.out.println("Successfully created new room.");
            return true;
        }
        System.out.println("Unable to create new room: room name was not unique.");
        return false;
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public void scheduleSpeakerCmd() {
        getAllEvents();
        System.out.println("Type event number:");
        int index = parseInt(input.nextLine());
        String eventId = new ArrayList<>(em.getAllEvents().keySet()).get(index);
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String name : speakerNames)
            System.out.println(name);
        System.out.println("Type speaker name:");
        String speakerName = input.nextLine();
        scheduleSpeaker(speakerName, eventId);
    }

    /**
     * Messages the attendees of the given list of events.
     *
     * @param speakerName String representing the speaker's username.
     * @param eventId String representing the event's unique ID.
     * @return A boolean value signifying whether method was successful.
     */
    public boolean scheduleSpeaker(String speakerName, String eventId) {
        LocalDateTime start = em.getEventById(eventId).getStartTime();
        LocalDateTime end = em.getEventById(eventId).getEndTime();
        if (!em.canAddSpeakerToEvent(eventId)) {
            System.out.println("Another speaker is already speaking at this event.");
            return false;
        } else if (!um.canAddSpeakerEvent(speakerName, eventId, start, end)) {
            System.out.println("This speaker is already speaking at another event.");
            return false;
        }
        em.addSpeakerToEvent(speakerName, eventId);
        um.addSpeakerEvent(speakerName, eventId, start, end);
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
