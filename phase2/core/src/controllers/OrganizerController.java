package controllers;

import org.json.simple.JSONObject;
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
    public JSONObject createRoomCmd(JSONObject roomInfo) {
        String roomName = ;
        int capacity = ;
        String hasChairs = ;
        String hasTables = ;
        String hasProjector = ;
        String hasSoundSystem = ;

        return createRoom(roomName, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem);
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
    public JSONObject createRoom(String roomName, int capacity, String hasChairs, String hasTables, String hasProjector,
                           String hasSoundSystem) {
        if(roomName.length() < 1)  //ensure that the room name is not empty
            return op.emptyFieldError();
        else if (!isInputCorrect(hasChairs) | !isInputCorrect(hasTables) | !isInputCorrect(hasProjector)
                | !isInputCorrect(hasSoundSystem)){
            return op.incorrectInputError();
        }
        else if (rm.createRoom(roomName, capacity, convertToBoolean(hasChairs), convertToBoolean(hasTables),
                convertToBoolean(hasProjector), convertToBoolean(hasSoundSystem)))
            return op.roomCreationResult();
        else
            return op.invalidRoomNameError();
    }


    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public JSONObject scheduleSpeakerCmd() {
        int index;
        String speakerIndex;
        String eventId;

        List<String> allSpeakers = um.getAllSpeakerNames();
        List<String> allEvents = getAllEvents();
        //custom error messages
        if(allEvents.size()==0){
            return op.noEvents();
        }

        if(allSpeakers.size()==0){
            return op.noSpeakers();
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
     *
     */
    public JSONObject scheduleSpeaker(JSONObject speakerSchedulingInfo) {
        String speakerName = ;
        String eventId = ;

        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!em.canAddSpeakerToEvent(eventId, speakerName)) {
            return op.existingSpeakerAtEventError();
        }
        if (!um.canAddSpeakerEvent(speakerName, eventId, start, end)) { //if speaker is speaking at another event
            return op.speakerUnavailableError();
        }
        em.addSpeakerToEvent(speakerName, eventId); //add speaker to event
        um.addSpeakerEvent(speakerName, eventId, start, end); //add event to speaker
        return op.scheduleSpeakerResult();
    }

    /**
     * Takes input from the user about constraints for an event.
     *
     * @return a list of constraints for an event
     */

    public ArrayList<Boolean> getConstraints(JSONObject constraints){
        ArrayList<String> stringConstraints = new ArrayList<>();
        ArrayList<Boolean> boolConstraints = new ArrayList<>();

        String hasChairs = ;
        String hasTables = ;
        String hasProjector = ;
        String hasSoundSystem = ;

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
    public JSONObject listPossibleRooms(JSONObject eventInfo){
        ArrayList<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = ;

        ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        return op.listRooms(possibleRooms);
    }

    public JSONObject introduceRooms(JSONObject eventInfo){
        ArrayList<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = ;

        ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        return op.roomIntroduceListLabel(possibleRooms);
    }

    public JSONObject roomSelection(JSONObject eventInfo) {
        ArrayList<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = ;
        String roomName = ;

        ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        if(roomName.equals("")){
            return op.emptyFieldError();
        } else if (!possibleRooms.contains(roomName)){
            return op.selectionNotValid();
        }
        if (rm.getRoomSchedule(roomName) == null){
            return op.roomDoesNotExistLabel();
        }
        return op.listRoomSchedule(rm.getRoomSchedule(roomName)); //list the schedule of the selected room
    }

    public JSONObject createEventCmd(JSONObject eventInfo) {
        if (rm.getAllRooms().size() == 0) //if there's no rooms
            op.noRoomError();
        else {
            ArrayList<Boolean> constraints = getConstraints(eventInfo); // get information about room constraints
            int eventCap = ;
            LocalDateTime startTime = ;
            LocalDateTime endTime = ;
            boolean vipEvent = ;
            String roomName = ;
            String eventName = ;
            LocalDateTime startTime = ;
            LocalDateTime endTime = ;

            ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap);
        }
            if (eventName.equals("")) { //ensures that the event name is not empty
               return op.emptyFieldError();
            } else if (startTime == null | endTime == null) { //ensures that the times are not empty for custom error message
                return op.emptyFieldError();
            }
            if (createEvent(eventName, startTime, endTime, roomName, constraints.get(0), constraints.get(1),
                    constraints.get(2), constraints.get(3), eventCap, vipEvent)) {
                return op.eventCreationResult();
            } else {
                return op.eventFailedCreationError();
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
                               boolean tables, boolean projector, boolean soundSystem, int capacity, boolean vipEvent) {
        if (rm.addToRoomSchedule(start, end, roomName, eventName)) {
            em.createNewEvent(eventName, start, end, roomName, chairs, tables, projector, soundSystem, capacity, vipEvent);
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
