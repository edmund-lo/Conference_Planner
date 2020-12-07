package controllers;

import org.json.simple.*;
import presenters.OrganizerPresenter;

import java.time.LocalDateTime;
import java.util.*;

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
        String message = messageContent.get("content").toString();
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
                addMessagesToUser(recipientName, messageId);
                return mp.messageResult(recipientName);
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
        String message = messageContent.get("content").toString();
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
                    addMessagesToUser(recipientName, messageId);
                    return mp.messageResult(recipientName); //message user
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
        String roomName = roomInfo.get("roomName").toString();
        int capacity = (int) roomInfo.get("capacity");
        boolean hasChairs = (boolean) roomInfo.get("chairs");
        boolean hasTables = (boolean) roomInfo.get("tables");
        boolean hasProjector = (boolean) roomInfo.get("projector");
        boolean hasSoundSystem = (boolean) roomInfo.get("sound");

        return createRoom(roomName, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem);
    }

    /**
     * Creates a new room.
     *
     * @param roomName String representing the new room's name.
     * @param capacity Integer representing the new room's capacity.
     */
    public JSONObject createRoom(String roomName, int capacity, boolean hasChairs, boolean hasTables,
                                 boolean hasProjector, boolean hasSoundSystem) {
        if(roomName.length() < 1)  //ensure that the room name is not empty
            return op.emptyFieldError();
        else if (rm.createRoom(roomName, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem))
            return op.roomCreationResult();
        else
            return op.invalidRoomNameError();
    }

    public JSONObject listEvents() {
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

    public JSONObject listSpeakers(String eventID) {
        List<String> allSpeakers = um.getAllSpeakerNames();
        return op.listSpeakers(allSpeakers);
    }

    public JSONObject listAvailableSpeakers(String eventID) {
        List<String> allSpeakers = um.getAllSpeakerNames();
        List<String> availableSpeakers = new ArrayList<>();
        for (String speaker : allSpeakers) {
            if (um.canAddSpeakerEvent(speaker, eventID, em.getEventStartTime(eventID), em.getEventEndTime(eventID))) {
                availableSpeakers.add(speaker);
            }
        }
        return op.listSpeakers(availableSpeakers);
    }

    /**
     * Called when user chooses to schedule a speaker to an event.
     */
    public JSONObject scheduleSpeakerCmd(String eventID, String speakerName) {

        return scheduleSpeaker(speakerName, eventID); //schedule the selected speaker
    }

    /**
     * Messages the attendees of the given list of events.
     *
     * @param speakerName the speaker's name.
     * @param eventID the ID of the event.
     *
     */
    public JSONObject scheduleSpeaker(String speakerName, String eventID) {
        LocalDateTime start = em.getEventStartTime(eventID);
        LocalDateTime end = em.getEventEndTime(eventID);
        if (!em.canAddSpeakerToEvent(eventID, speakerName)) {
            return op.existingSpeakerAtEventError();
        }
        if (!um.canAddSpeakerEvent(speakerName, eventID, start, end)) { //if speaker is speaking at another event
            return op.speakerUnavailableError();
        }
        em.addSpeakerToEvent(speakerName, eventID); //add speaker to event
        um.addSpeakerEvent(speakerName, eventID, start, end); //add event to speaker
        return op.scheduleSpeakerResult();
    }

    /**
     * Takes input from the user about constraints for an event.
     *
     * @return a list of constraints for an event
     */

    public List getConstraints(JSONObject constraints){
        boolean hasChairs = (boolean) constraints.get("chairs");
        boolean hasTables = (boolean) constraints.get("tables");
        boolean hasProjector = (boolean) constraints.get("projector");
        boolean hasSoundSystem = (boolean) constraints.get("sound");
        List<Boolean> boolConstraints = new ArrayList<>();
        boolConstraints.add(hasChairs);
        boolConstraints.add(hasTables);
        boolConstraints.add(hasProjector);
        boolConstraints.add(hasSoundSystem);
        return boolConstraints;
    }

    /**
     * Retrieves the list of available rooms, given the amenities needed for the event.
     *
     * @param eventInfo a JSON object with the information about the event amenities
     *
     * @return a JSON object with a warning, error or success message.
     */
    public JSONObject listPossibleRooms(JSONObject eventInfo){
        List<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = (int) eventInfo.get("capacity");

        List<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap); // all possible rooms that can host
        return op.listRooms(possibleRooms);
    }

    /**
     * Validates user input and creates an event in the EventManager
     *
     * @param eventInfo a JSON object with the information about the event amenities.
     *
     * @return a JSON object with a warning, error or success message.
     */

    public JSONObject createEventCmd(JSONObject eventInfo) {
        if (rm.getAllRooms().size() == 0) //if there's no rooms
            op.noRoomError();
        List<Boolean> constraints = getConstraints(eventInfo); // get information about room constraints
        int eventCap = (int) eventInfo.get("capacity");
        LocalDateTime startTime = (LocalDateTime) eventInfo.get("start");
        LocalDateTime endTime = (LocalDateTime) eventInfo.get("end");
        boolean vipEvent = (boolean) eventInfo.get("vip");
        String roomName = eventInfo.get("roomName").toString();
        String eventName = eventInfo.get("eventName").toString();

        List<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap);

        if (eventName.equals("") | roomName.equals("")) { //ensures that the event name/times are not empty
            return op.emptyFieldError();
        } else if (!possibleRooms.contains(roomName)){ // check if room can accommodate the event
            return op.selectionNotValid();
        } else if (eventCap < 1){ // checks if capacity input is valid
            return op.invalidCapacityError();
        } else if (startTime.isAfter(endTime)){ // checks if date/time input is valid (start time before end time
            return op.invalidDateError();
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
    public boolean createEvent(String eventName, LocalDateTime start, LocalDateTime end, String roomName,
                               boolean chairs, boolean tables, boolean projector, boolean soundSystem, int capacity,
                               boolean vipEvent) {
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

    public JSONObject changeEventCapacity(JSONObject eventInfo){
        String eventID = eventInfo.get("eventID").toString();
        int capacity = (int) eventInfo.get("capacity");
        String roomName = eventInfo.get("roomName").toString();
        if (capacity < 1){
            return op.invalidCapacityError();
        } else if (rm.getRoomCapacity(roomName) < capacity){
            return op.cannotAccommodate();
        } else if (em.getAttendingUsers(eventID).size() < capacity){
            return op.cannotChangeCap(capacity);
        } else {
            em.changeEventCap(eventID, capacity);
            return op.changeCapResult();
        }
    }
}
