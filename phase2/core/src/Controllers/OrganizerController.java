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
        while(inSession) { //loop until user logs out
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
        while (true) { //loop until valid input
            op.createNewPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1)
                    createRoomCmd();
                else if (option == 2)
                    createSpeakerAccountCmd();
                else if (option == 3)
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
        while (true) { //loop until valid input
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
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, password); //create new speaker
            op.speakerCreationResult();
            return; //exit method
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
        for (String recipientName : speakerNames) { //loop through every speaker
            if (mm.messageCheck(recipientName, username, message)) { //ensure that message is valid
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
        for (String recipientName : attendeeNames) { //loop through all attendee names
            if (!recipientName.equals(username)) { //ensure the organizer doesn't message self
                if (mm.messageCheck(recipientName, username, message)) { //ensure the message is valid
                    String messageId = mm.createMessage(recipientName, username, message);
                    mp.messageResult(recipientName); //message user
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
        /*op.roomCapacityPrompt(); capacity for phase 2
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
    public void createRoom(String roomName, int capacity) { //capacity for phase 2
        if(roomName.length() < 1)  //ensure that the room name is not empty
            op.emptyFieldError();
        else if (rm.createRoom(roomName))
            op.roomCreationResult();
        else
            op.invalidRoomNameError();
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public void scheduleSpeakerCmd() {
        int index;
        String speakerIndex;
        String eventId;

        List<String> allSpeakers = um.getAllSpeakerNames();
        List<String> allEvents = getAllEvents();
        //custom error messages
        if(allEvents.size()==0){
            op.noEvents();
            return;
        }

        if(allSpeakers.size()==0){
            op.noSpeakers();
            return;
        }
        //user picks an event to assign a speaker to
        op.speakerListAllEventsPrompt();
        op.listEvents(allEvents);
        while(true){
            op.eventNumberPrompt();
            try{
                index = parseInt(input.nextLine());
                if(index == 0){ //index of 0 is the back number hence break the loop
                    return; //leave method
                }
                else if (em.getAllEventIds().size() < index){ //inputted index is not one of the numbered events
                    op.invalidOptionError();
                }
                else{
                    eventId = em.getAllEventIds().get(index-1); //get selected event
                    break;
                }
            }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                op.invalidOptionError();
            }
        }
        op.listSpeakers(allSpeakers);
        op.speakerNamePrompt();
        while(true){
            try{
                speakerIndex = input.nextLine();
                if(speakerIndex.equals("0")){ //if user wanted to go back to main menu in this stage
                    break;
                }
                scheduleSpeaker(allSpeakers.get(parseInt(speakerIndex)-1), eventId); //schedule the selected speaker
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
        if (!em.canAddSpeakerToEvent(eventId)) { //if event already has a speaker
            op.existingSpeakerAtEventError();
            return; //exit method
        }
        if (!um.canAddSpeakerEvent(speakerName, eventId, start, end)) { //if speaker is speaking at another event
            op.speakerUnavailableError();
            return; //exit method
        }
        em.addSpeakerToEvent(speakerName, eventId); //add speaker to event
        um.addSpeakerEvent(speakerName, eventId, start, end); //add event to speaker
        op.scheduleSpeakerResult();
    }

    /**
     * Called when user chooses to create a new event
     */
    public void createEventCmd() {
        if (rm.getAllRooms().size() == 0) //if there's no rooms
            op.noRoomError();
        else{
            op.roomIntroduceListLabel();
            op.listRooms(rm.getAllRooms());
            String roomName;
            while (true) { //user inputs the room name they wish to create an event in
                op.roomNamePrompt();
                roomName = input.nextLine();
                try{
                    if(roomName.equals("")){
                        op.emptyFieldError();
                    }
                    op.listRoomSchedule(rm.getRoomSchedule(roomName)); //list the schedule of the selected room
                    break; //break the loop as the user has entered a valid input
                }catch(NullPointerException e){
                    op.roomDoesNotExistLabel();
                }
            }
            //user enters the desired event name
            op.eventNamePrompt();
            String eventName = input.nextLine();
            while (eventName.equals("")) { //ensures that the event name is not empty
                op.emptyFieldError();
                eventName = input.nextLine();
            }
            //user enters the desired starting time
            op.eventTimePrompt();
            String startString = input.nextLine();
            while (startString.equals("")) { //ensures that the starting time is not empty for custom error message
                op.emptyFieldError();
                startString = input.nextLine();
            }
            try { //try to ensure that user enters valid format for the dtf
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(startString, formatter);
                if (createEvent(eventName, startTime, roomName))
                    op.eventCreationResult();
                else
                    op.eventFailedCreationError();
            } catch (DateTimeParseException e) {
                op.invalidDateError();
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
        if (rm.addToRoomSchedule(start, end, roomName, eventName)) {
            em.createNewEvent(eventName, start, end, roomName);
            return true;
        }
        return false;
    }
    //phase 2 methods
    /*public boolean cancelEvent(String eventId) {
        return true;
    }

    public boolean rescheduleEvent(String eventId, LocalDateTime newStart, LocalDateTime newEnd) {
        return true;
    }*/
}
