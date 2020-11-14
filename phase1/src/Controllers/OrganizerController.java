package Controllers;

import Presenters.OrganizerPresenter;
import UseCases.*;

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
    private OrganizerPresenter op;

    /**
     * Constructor for OrganizerController object. Uses constructor from UserController.
     *
     * @param em  current session's UseCases.EventManager class.
     * @param um  current session's UseCases.UserManager class.
     * @param rm  current session's UseCases.RoomManager class.
     * @param mm  current session's UseCases.MessageManager class.
     * @param username current logged in user's username.
     */
    public OrganizerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
        this.op = new OrganizerPresenter();

        boolean inSession = true;
        // Enters a while loop that allows the user to continuously use Organizer and Attendee functions
        while(inSession) {
            op.displayMenu();
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
                    createNewMenu();
                    break;
                case 5:
                    organizerMessageMenu();
                    break;
                case 6:
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
            int option = parseInt(input.nextLine());
            if (option == 0)
                break;
            else if (option == 1)
                createRoomCmd();
            else if (option == 2)
                createSpeakerAccountCmd();
            else
                op.invalidOptionError();
        }
    }

    /**
     * Called when user chooses to display organizer messaging options
     */
    public void organizerMessageMenu() {
        while (true) {
            op.organizerMessagePrompt();
            int option = parseInt(input.nextLine());
            if (option == 0)
                break;
            else if (option == 1)
                messageAllSpeakersCmd();
            else if (option == 2)
                messageAllAttendeesCmd();
            else
                op.invalidOptionError();
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
     * @return A boolean value signifying whether method was successful.
     */
    public boolean createSpeakerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) {
            um.createNewSpeaker(username, password);
            op.speakerCreationResult();
            return true;
        }
        op.invalidSpeakerNameError();
        return false;
    }

    /**
     * Called when user chooses to message all speakers.
     */
    public void messageAllSpeakersCmd() {
        op.enterMessagePrompt();
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
            String messageId = mm.sendMessage(name, username, message);
            addMessagesToUser(name, messageId);
        }
        op.messagedAllSpeakersResult();
        return true;
    }

    /**
     * Called when user chooses to message all attendees.
     */
    public void messageAllAttendeesCmd() {
        op.enterMessagePrompt();
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
        Set<String> attendeeNames = um.getAllUsernames();
        for (String name : attendeeNames) {
            if (!name.equals(username)) {
                String messageId = mm.sendMessage(name, username, message);
                addMessagesToUser(name, messageId);
            }
        }
        op.messagedAllAttendeesResult();
        return true;
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
     * @return A boolean value signifying whether method was successful.
     */
    public boolean createRoom(String roomName, int capacity) {
        if (rm.createRoom(roomName)) {
            op.roomCreationResult();
            return true;
        }
        op.invalidRoomNameError();
        return false;
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public void scheduleSpeakerCmd() {
        getAllEvents();
        op.eventNumberPrompt();
        int index = parseInt(input.nextLine());
        String eventId = em.getAllEventIds().get(index);
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String name : speakerNames)
            System.out.println(name);
        op.speakerNamePrompt();
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
        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!em.canAddSpeakerToEvent(eventId)) {
            op.existingSpeakerAtEventError();
            return false;
        } else if (!um.canAddSpeakerEvent(speakerName, eventId, start, end)) {
            op.speakerUnavailableError();
            return false;
        }
        em.addSpeakerToEvent(speakerName, eventId);
        um.addSpeakerEvent(speakerName, eventId, start, end);
        op.scheduleSpeakerResult();
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
