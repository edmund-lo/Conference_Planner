package controllers;

import org.json.simple.*;
import presenters.OrganizerPresenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A Controller class representing a OrganizerController which inherits from UserController.
 *
 * @author Echo Li
 * @author Keegan McGonigal
 * @version 2.0
 *
 */
public class OrganizerController extends UserController {
    private final OrganizerPresenter op;

    /**
     * Constructor for OrganizerController object. Uses constructor from UserController.
     *
     * @param username current logged in user's username.
     */
    public OrganizerController(String username) {
        super(username);
        this.op = new OrganizerPresenter();
    }

    /**
     * Called when user chooses to message all speakers.
     */
    public JSONObject messageAllSpeakersCmd(JSONObject messageContent) {
        String message = messageContent.get("message");
        return messageAllSpeakers(message);
    }

    /**
     * Messages all speakers.
     *
     * @param message String representing the user's message.
     */
    public JSONObject messageAllSpeakers(String message) {
        List<String> speakerNames = um.getAllSpeakerNames();
        for (String recipientName : speakerNames) { //loop through every speaker
            if (mm.messageCheck(recipientName, username, message)) { //ensure that message is valid
                String messageId = mm.createMessage(recipientName, username, message);
                return mp.messageResult(recipientName);
                addMessagesToUser(recipientName, messageId);
            } else {
                mp.invalidMessageError();
            }
        }
        return op.messagedAllSpeakersResult();
    }

    /**
     * Called when user chooses to message all attendees.
     */
    public JSONObject messageAllAttendeesCmd(JSONObject messageContent) {
        String message = messageContent.get("message");
        return messageAllAttendees(message);
    }

    /**
     * Messages all attendees.
     *
     * @param message String representing the user's message.
     */
    public JSONObject messageAllAttendees(String message) {
        Set<String> attendeeNames = um.getAllUsernames();
        for (String recipientName : attendeeNames) { //loop through all attendee names
            if (!recipientName.equals(username)) { //ensure the organizer doesn't message self
                if (mm.messageCheck(recipientName, username, message)) { //ensure the message is valid
                    String messageId = mm.createMessage(recipientName, username, message);
                    return mp.messageResult(recipientName); //message user
                    addMessagesToUser(recipientName, messageId);
                } else {
                    return mp.invalidMessageError();
                }
            }
        }
        return op.messagedAllAttendeesResult();
    }

    /**
     * Called when user chooses to create a new room.
     */
    public JSONObject createRoomCmd(JSONObject roomInfo) {
        String roomName = ;
        int capacity = roomInfo.get("capacity");
        String hasChairs = roomInfo.get("chairs");
        String hasTables = roomInfo.get("tables");
        String hasProjector = roomInfo.get("projector");
        String hasSoundSystem = roomInfo.get("sound");

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

    public JSONObject listEvents(JSONObject speakerInfo) {
        List<String> allSpeakers = um.getAllSpeakerNames();
        List<String> allEvents = getAllEvents();
        //custom error messages
        if (allEvents.size() == 0) {
            return op.noEvents();
        }
        if (allSpeakers.size() == 0) {
            return op.noSpeakers();
        }
        //user picks an event to assign a speaker to
        return op.listEvents(allEvents);
    }

    public JSONObject listSpeakers() {
        List<String> allSpeakers = um.getAllSpeakerNames();
        return op.listSpeakers(allSpeakers);
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public JSONObject scheduleSpeakerCmd(JSONObject speakerInfo) {
        String speakerName = ;
        String eventId = ;

        List<String> allSpeakers = um.getAllSpeakerNames();
        List<String> allEvents = getAllEvents();

        return scheduleSpeaker(allSpeakers.get(speakerName), eventId); //schedule the selected speaker
    }

    /**
     * Messages the attendees of the given list of events.
     *
     * @param speakerSchedulingInfo JSON object representing the speaker's information.
     *
     */
    public JSONObject scheduleSpeaker(JSONObject speakerSchedulingInfo) {
        String speakerName = speakerSchedulingInfo.get("name");
        String eventId = speakerSchedulingInfo.get("eventID");

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

        String hasChairs = constraints.get("chairs");
        String hasTables = constraints.get("tables");
        String hasProjector = constraints.get("projector");
        String hasSoundSystem = constraints.get("sound");

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
        int eventCap = eventInfo.get("capacity");

        ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        return op.listRooms(possibleRooms);
    }

    public JSONObject introduceRooms(JSONObject eventInfo){
        ArrayList<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = eventInfo.get("capacity");

        ArrayList<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        return op.roomIntroduceListLabel(possibleRooms);
    }

    public JSONObject roomSelection(JSONObject eventInfo) {
        ArrayList<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = eventInfo.get("capacity");
        String roomName = eventInfo.get("eventName");

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
            int eventCap = eventInfo.get("capacity");
            LocalDateTime startTime = eventInfo.get("start");
            LocalDateTime endTime = eventInfo.get("end");
            boolean vipEvent = eventInfo.get("vip");
            String roomName = eventInfo.get("roomName");
            String eventName = eventInfo.get("eventName");

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
            em.createNewEvent(eventName, start, end, roomName, chairs, tables, projector, soundSystem, capacity,
                    vipEvent);
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
     * @param info contains the event ID (eventID), the room name (roomName) and the start time (startTime)
     *             and end time (endTime)
     * @return A JSONObject detailing the outcome
     */
    public JSONObject rescheduleEvent(JSONObject info) {
        String eventID = info.get("eventID").toString();
        String roomName = info.get("roomName").toString();
        LocalDateTime newStart = (LocalDateTime) info.get("startTime");
        LocalDateTime newEnd = (LocalDateTime) info.get("endTime");
        if(rm.addToRoomSchedule(newStart, newEnd, roomName, eventID)){
            em.changeEventTime(eventID, newStart, newEnd);
            em.changeEventRoom(eventID, roomName);
            return op.rescheduleSuccess();
        }
        return op.rescheduleFailure(roomName);
    }
}
