package controllers;

import presenters.OrganizerPresenter;
import usecases.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    }

    /**
     * Called when user chooses to message all speakers.
     */
    public void messageAllSpeakersCmd() {
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
        createRoom(name, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem);
    }

    private boolean isInputCorrect(String userInput){
        return userInput.equalsIgnoreCase("Y") | userInput.equalsIgnoreCase("N");
    }
    private boolean convertToBoolean(String userInput){
        return userInput.equals("Y");
    }

    /**
     * Creates a new room.
     *
     * @param roomName String representing the new room's name.
     * @param capacity Integer representing the new room's capacity.
     */
    public void createRoom(String roomName, int capacity, String hasChairs, String hasTables, String hasProjector,
                           String hasSoundSystem) {
        if(roomName.length() < 1)  //ensure that the room name is not empty
            op.emptyFieldError();
        else if (!isInputCorrect(hasChairs) | !isInputCorrect(hasTables) | !isInputCorrect(hasProjector)
                | !isInputCorrect(hasSoundSystem)){
            op.incorrectInputError();
        }
        else if (rm.createRoom(roomName, capacity, convertToBoolean(hasChairs), convertToBoolean(hasTables),
                convertToBoolean(hasProjector), convertToBoolean(hasSoundSystem)))
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
        op.listEvents(allEvents);
        while(true){
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
        if (!em.canAddSpeakerToEvent(eventId, speakerName)) {
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
     * Takes input from the user about constraints for an event.
     *
     * @return a list of constraints for an event
     */

    public ArrayList<Boolean> getConstraints(){
        ArrayList<String> stringConstraints = new ArrayList<>();
        ArrayList<Boolean> boolConstraints = new ArrayList<>();
        op.needItemPrompt("chair");
        stringConstraints.add(input.next());
        op.needItemPrompt("table");
        stringConstraints.add(input.next());
        op.needItemPrompt("projector");
        stringConstraints.add(input.next());
        op.needItemPrompt("speaker/sound system");
        stringConstraints.add(input.next());
        for (String constraint : stringConstraints){ // check to see that user input is correct
            if (!isInputCorrect(constraint)){
                op.incorrectInputError();
                break;
            } else {
                boolConstraints.add(convertToBoolean(constraint));
            }
        }
        return boolConstraints;
    }

    /**
     * Called when user chooses to create a new event
     */
    public void createEventCmd(ArrayList<Boolean> constraints, int eventCap, LocalDateTime endTime) {
        if (rm.getAllRooms().size() == 0) //if there's no rooms
            op.noRoomError();
        else {
            ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
            op.roomIntroduceListLabel(possibleRooms);
            op.listRooms(possibleRooms);
            String roomName;
            while (true) { //user inputs the room name they wish to create an event in
                roomName = input.nextLine();
                try{
                    if(roomName.equals("")){
                        op.emptyFieldError();
                    }
                    op.listRoomSchedule(rm.getRoomSchedule(roomName)); //list the schedule of the selected room
                    break; //break the loop as the user has entered a valid input
                } catch(NullPointerException e){
                    op.roomDoesNotExistLabel();
                }
            }
            //user enters the desired event name
            String eventName = input.nextLine();
            while (eventName.equals("")) { //ensures that the event name is not empty
                op.emptyFieldError();
                eventName = input.nextLine();
            }
            //user enters the desired starting time
            String startString = input.nextLine();
            while (startString.equals("")) { //ensures that the starting time is not empty for custom error message
                op.emptyFieldError();
                startString = input.nextLine();
            }
            try { //try to ensure that user enters valid format for the dtf
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(startString, formatter);
                if (createEvent(eventName, startTime, endTime, roomName, constraints.get(0), constraints.get(1),
                        constraints.get(2), constraints.get(3), eventCap)) {
                    op.eventCreationResult();
                } else {
                    op.eventFailedCreationError();
                }
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
    public boolean createEvent(String eventName, LocalDateTime start, LocalDateTime end, String roomName, boolean chairs,
                               boolean tables, boolean projector, boolean soundSystem, int capacity) {
        if (rm.addToRoomSchedule(start, end, roomName, eventName)) {
            em.createNewEvent(eventName, start, end, roomName, chairs, tables, projector, soundSystem, capacity);
            return true;
        }
        return false;
    }

    /**
     * Cancels event with ID eventID. Cancelling an event results in removing all attendess and speakers from attending
     * and removes its room. Note that the event is NOT deleted.
     * @param eventId the ID of the event you wish to cancel
     */
    public void cancelEvent(String eventId) {
        rm.removeFromRoomSchedule(em.getEventStartTime(eventId),
                em.getEventEndTime(eventId), em.getEventRoom(eventId), eventId);
        um.cancelAll(em.getAttendingUsers(eventId), eventId);
        for(String speakerName: em.getSpeakers(eventId)){
            um.cancelSpeakerEvent(speakerName, eventId);
        }
        em.cancelEvent(eventId);
    }

    /**
     * removes event with ID eventID. Removing an event results in cancelling it and then removing it completely from
     * the system.
     * @param eventID the ID of the event you wish to cancel
     */
    public void removeEvent(String eventID){
        cancelEvent(eventID);
        em.removeEvent(eventID);
    }

    /**
     * Reschedules event with ID eventID at newStart to newEnd in roomName. Note that this should be called ONLY after
     * cancelEvent has been called
     * @param eventId the ID of the event you wish to reschedule
     * @param roomName the room you wish to move the event to
     * @param newStart the time at which the event will start
     * @param newEnd the time at which the event will end
     * @return True iff the event was successfully rescheduled
     */
    public boolean rescheduleEvent(String eventId, String roomName, LocalDateTime newStart, LocalDateTime newEnd) {
        if(rm.addToRoomSchedule(newStart, newEnd, roomName, eventId)){
            em.changeEventTime(eventId, newStart, newEnd);
            em.changeEventRoom(eventId, roomName);
            return true;
        }
        return false;
    }
}
