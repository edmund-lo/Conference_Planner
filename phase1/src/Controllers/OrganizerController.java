package Controllers;

import Presenters.OrganizerPresenter;
import UseCases.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private final OrganizerPresenter op;

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
        this.op = new OrganizerPresenter();

        boolean inSession = true;
        // Enters a while loop that allows the user to continuously use Organizer and Attendee functions
        while(inSession) {
            op.displayMenu("Organizer", username);
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
                    createNewMenu();
                    break;
                case "6":
                    organizerMessageMenu();
                    break;
                case "7":
                    scheduleSpeakerCmd();
                    break;
                default:
                    op.invalidOptionError();
                    break;
            }
        }
    }

    /**
     * Called when user chooses to display new creation options
     */
    public void createNewMenu() {
        while (true) {
            op.createNewPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1)
                    createRoomCmd();
                else if (option == 2)
                    createSpeakerAccountCmd();
                else if (option == 3) //testing purposes, when not using comment out lines 89-90
                    createEventCmd();
                else
                    op.invalidOptionError();
            } catch (NumberFormatException e) {
                op.invalidOptionError();
            }
        }
    }

    /**
     * Called when user chooses to display organizer messaging options
     */
    public void organizerMessageMenu() {
        while (true) {
            op.organizerMessagePrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1)
                    messageAllSpeakersCmd();
                else if (option == 2)
                    messageAllAttendeesCmd();
                else
                    op.invalidOptionError();
            } catch (NumberFormatException e) {
                op.invalidOptionError();
            }
        }
    }

    /**
     * Called when user chooses to create a new speaker user account.
     */
    public void createSpeakerAccountCmd() {
        op.speakerUsernamePrompt();
        String username = input.nextLine();
        op.speakerPasswordPrompt();
        String password = input.nextLine();
        createSpeakerAccount(username, password);
    }

    /**
     * Creates a new speaker account after performing necessary checks.
     *
     * @param username String representing new speaker's username.
     * @param password String representing new speaker's password.
     */
    public void createSpeakerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) {
            um.createNewSpeaker(username, password);
            op.speakerCreationResult();
            return;
        }
        op.invalidSpeakerNameError();
    }

    /**
     * Called when user chooses to message all speakers.
     */
    public void messageAllSpeakersCmd() {
        mp.enterMessagePrompt();
        String message = input.nextLine();
        messageAllSpeakers(message);
    }

    /**
     * Messages all speakers.
     *
     * @param message String representing the user's message.
     */
    public void messageAllSpeakers(String message) {
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String recipientName : speakerNames) {
            if (mm.messageCheck(recipientName, username, message)) {
                String messageId = mm.createMessage(recipientName, username, message);
                mp.messageResult(recipientName);
                addMessagesToUser(recipientName, messageId);
            } else {
                mp.invalidMessageError();
            }
        }
        op.messagedAllSpeakersResult();
    }

    /**
     * Called when user chooses to message all attendees.
     */
    public void messageAllAttendeesCmd() {
        mp.enterMessagePrompt();
        String message = input.nextLine();
        messageAllAttendees(message);
    }

    /**
     * Messages all attendees.
     *
     * @param message String representing the user's message.
     */
    public void messageAllAttendees(String message) {
        Set<String> attendeeNames = um.getAllUsernames();
        for (String recipientName : attendeeNames) {
            if (!recipientName.equals(username)) {
                if (mm.messageCheck(recipientName, username, message)) {
                    String messageId = mm.createMessage(recipientName, username, message);
                    mp.messageResult(recipientName);
                    addMessagesToUser(recipientName, messageId);
                } else {
                    mp.invalidMessageError();
                }
            }
        }
        op.messagedAllAttendeesResult();
    }

    /**
     * Called when user chooses to create a new room.
     */
    public void createRoomCmd() {
        op.roomNamePrompt();
        String name = input.nextLine();
        /*op.roomCapacityPrompt();
        int capacity = parseInt(input.nextLine());*/
        int capacity = 2;
        createRoom(name, capacity);
    }

    /**
     * Creates a mew room.
     *
     * @param roomName String representing the new room's name.
     * @param capacity Integer representing the new room's capacity.
     */
    public void createRoom(String roomName, int capacity) {
        if (rm.createRoom(roomName)) {
            op.roomCreationResult();
            return;
        }
        op.invalidRoomNameError();
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public void scheduleSpeakerCmd() {
        int index;
        String speakerIndex;
        op.speakerListAllEventsPrompt();
        op.listEvents(getAllEvents());
        op.eventNumberPrompt();
        while(true){
            try{
                index = parseInt(input.nextLine());
                if (em.getAllEventIds().size() < index){
                    op.invalidOptionError();
                }
                else{
                    break;
                }
            }catch(NumberFormatException | IndexOutOfBoundsException e){
                op.invalidOptionError();
            }
        }
        String eventId = em.getAllEventIds().get(index-1);
        op.listSpeakers(um.getAllSpeakerNames());
        op.speakerNamePrompt();
        while(true){
            try{
                speakerIndex = input.nextLine();
                scheduleSpeaker(um.getAllSpeakerNames().get(parseInt(speakerIndex)-1), eventId);
                break;
            }catch(NumberFormatException | IndexOutOfBoundsException e){
                op.invalidOptionError();
            }
        }
    }

    /**
     * Messages the attendees of the given list of events.
     *
     * @param speakerName String representing the speaker's username.
     * @param eventId String representing the event's unique ID.
     */
    public void scheduleSpeaker(String speakerName, String eventId) {
        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!em.canAddSpeakerToEvent(eventId)) {
            op.existingSpeakerAtEventError();
            return;
        }
        if (!um.canAddSpeakerEvent(speakerName, eventId, start, end)) {
            op.speakerUnavailableError();
            return;
        }
        em.addSpeakerToEvent(speakerName, eventId);
        um.addSpeakerEvent(speakerName, eventId, start, end);
        op.scheduleSpeakerResult();
    }

    /**
     * Called when user chooses to create a new event
     */
    public void createEventCmd() {
        if (rm.getAllRooms().size() == 0)
            System.out.println("There are no rooms in the system. You cannot create an event.");
        else{
            for (String room : rm.getAllRooms()) {
                System.out.println(room);
            }
            System.out.println("Enter the room name:");
            String roomName = input.nextLine();
            while (roomName.equals("")) {
                System.out.println("Try again: cannot leave field empty!");
                roomName = input.nextLine();
            }
            System.out.println(rm.getRoomSchedule(roomName));
            System.out.println("Enter the event name:");
            String eventName = input.nextLine();
            while (eventName.equals("")) {
                System.out.println("Try again: cannot leave field empty!");
                eventName = input.nextLine();
            }
            System.out.println("Enter the event start time (formatted 'yyyy-MM-dd HH:mm'):");
            String startString = input.nextLine();
            while (startString.equals("")) {
                System.out.println("Try again: cannot leave field empty!");
                startString = input.nextLine();
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(startString, formatter);
                if (createEvent(eventName, startTime, roomName))
                    System.out.println("Successfully created new event.");
                else
                    System.out.println("Unable to create new event: scheduling conflict occurred.");
            } catch (DateTimeParseException e) {
                System.out.println("Unable to parse date input!");
            }
        }
    }

    /**
     * Attempts to create a new event with given parameters
     * @param eventName String representing the name of the event
     * @param start LocalDateTime representing the starting time of the event
     * @param roomName String representing the room
     * @return Boolean value signifying whether creation was successful
     */
    public boolean createEvent(String eventName, LocalDateTime start, String roomName) {
        LocalDateTime end = start.plusHours(1);
        if (rm.addToRoomSchedule(start, roomName, eventName)) {
            em.createNewEvent(eventName, start, end, roomName);
            return true;
        }
        return false;
    }

    /*public boolean cancelEvent(String eventId) {
        return true;
    }

    public boolean rescheduleEvent(String eventId, LocalDateTime newStart, LocalDateTime newEnd) {
        return true;
    }*/
}
