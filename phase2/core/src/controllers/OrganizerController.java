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
     * Messages all speakers at the conference.
     *
     * @param register The JSON Object which stores all data about the message wanted to send.
     * @return a JSON object containing whether the message was sent successfully or not.
     */
    public JSONObject messageAllSpeakers(JSONObject register) {
        this.deserializeData();

        String content = String.valueOf(register.get("content"));
        String subject = String.valueOf(register.get("subject"));
        String sender = String.valueOf(register.get("sender"));
        ArrayList<String> speakerNames = um.getAllSpeakerNames();
        if (mm.messageCheck(content, sender, speakerNames)) { //ensure that message is valid
            String messageId = mm.createMessage(content, sender, speakerNames, subject);
            addMessagesToUser(speakerNames, messageId);
            this.saveData();
            return op.messagedAllSpeakersResult();
        } else {
            return mp.invalidMessageError();
        }
    }

    /**
     * Messages all attendees.
     *
     * @param register The JSON Object which stores all data about the message wanted to send.
     * @return a JSONObject with the outcome of whether the message was successfully sent.
     */
    public JSONObject messageAllAttendees(JSONObject register) {
        this.deserializeData();

        String content = String.valueOf(register.get("content"));
        String subject = String.valueOf(register.get("subject"));
        String sender = String.valueOf(register.get("sender"));
        List<String> attendeeNames = um.getAllUsernames();
        if (mm.messageCheck(content, sender, attendeeNames)) { //ensure the message is valid
            String messageId = mm.createMessage(content, sender, attendeeNames, subject);
            addMessagesToUser((ArrayList<String>) attendeeNames, messageId);
            this.saveData();
            return op.messagedAllAttendeesResult();
        } else {
            return mp.invalidMessageError();
        }
    }

    /**
     * Creates a new room.
     *
     * @param roomInfo String representing the new room's name.
     * @return a JSONObject containing whether the room was created successfully or not.
     */
    public JSONObject createRoom(JSONObject roomInfo) {
        this.deserializeData();

        // extract the information from the json
        String roomName = roomInfo.get("roomName").toString();
        int capacity = (int) roomInfo.get("capacity");
        boolean hasChairs = (boolean) roomInfo.get("chairs");
        boolean hasTables = (boolean) roomInfo.get("tables");
        boolean hasProjector = (boolean) roomInfo.get("projector");
        boolean hasSoundSystem = (boolean) roomInfo.get("sound");

        if (roomName.length() < 1) {  //ensure that the room name is not empty
            return op.emptyFieldError();
        }
        else if (capacity < 1){
            return op.invalidCapacity();
        }
        else if (rm.createRoom(roomName, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem)) {
            this.saveData();
            return op.roomCreationResult();
        } else {
            return op.invalidRoomNameError();
        }
    }

    /**
     * Lists the speakers that are available to speak at a given event.
     *
     * @param eventID the ID of the event that the speakers can or cannot speak at
     * @return a JSONObject containing the list of speakers that are available to speak at this event.
     */
    public JSONObject listAvailableSpeakers(String eventID) {
        this.deserializeData();

        List<String> allSpeakers = um.getAllSpeakerNames();
        JSONArray availableSpeakers = new JSONArray();
        for (String speaker : allSpeakers) {
            if (um.canAddSpeakerEvent(speaker, eventID, em.getEventStartTime(eventID), em.getEventEndTime(eventID))) {
                availableSpeakers.add(um.getUserJson(speaker));
            }
        }
        return op.listSpeakers(availableSpeakers);
    }

    /**
     * Schedules a speaker to an event.
     *
     * @param speakerName the speaker's name.
     * @param eventID the ID of the event.
     * @return a JSONObject containing whether the speaker was scheduled successfully or not.
     *
     */
    public JSONObject scheduleSpeaker(String eventID, String speakerName) {
        this.deserializeData();

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
        this.saveData();
        return op.scheduleSpeakerResult();
    }

    /**
     * Takes input from the user about constraints for an event.
     * @param constraints the list of amenities that are needed for an event.
     * @return a list of constraints for an event.
     */

    private List<Boolean> getConstraints(JSONObject constraints){
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
        this.deserializeData();

        List<Boolean> constraints = getConstraints(eventInfo);
        int eventCap = (int) eventInfo.get("capacity");

        JSONArray array = new JSONArray();

        for (String roomID: rm.getAllRoomsWith(constraints, eventCap)){
            array.add(rm.getRoomJson(roomID).get("name"));
        }
        return op.listPossibleRooms(array);
    }

    /**
     * Validates user input and creates an event in the EventManager
     *
     * @param eventInfo a JSON object with the information about the event amenities.
     *
     * @return a JSON object with a warning, error or success message.
     */

    public JSONObject createEventCmd(JSONObject eventInfo) {
        this.deserializeData();

        if (rm.getAllRooms().size() == 0) //if there's no rooms
            op.noRoomError();
        List<Boolean> constraints = getConstraints(eventInfo); // get information about room constraints
        int eventCap = (int) eventInfo.get("capacity");
        LocalDateTime startTime = (LocalDateTime) eventInfo.get("start");
        LocalDateTime endTime = (LocalDateTime) eventInfo.get("end");
        boolean vipEvent = (boolean) eventInfo.get("vip");
        if(eventInfo.get("roomName") == null | eventInfo.get("eventName") == null){
            return op.emptyFieldError();
        }
        String roomName = eventInfo.get("roomName").toString();
        String eventName = eventInfo.get("eventName").toString();
        List<String> possibleRooms = rm.getAllRoomsWith(constraints, eventCap);
        if (eventName.equals("") | roomName.equals("")) { //ensures that the event name/times are not empty
            return op.emptyFieldError();
        } else if (!possibleRooms.contains(roomName)){ // check if room can accommodate the event
            return op.selectionNotValid();
        } else if (eventCap < 1){ // checks if capacity input is valid
            return op.invalidCapacityError();
        } else if (startTime.isAfter(endTime) && !startTime.equals(endTime)) { // checks if date/time input is valid (start time before end time
            return op.invalidDateError();
        }
        if (createEvent(eventName, startTime, endTime, roomName, constraints.get(0), constraints.get(1),
                constraints.get(2), constraints.get(3), eventCap, vipEvent)) {
            this.saveData();
            System.out.println("created event "+startTime.equals(endTime));
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
    private boolean createEvent(String eventName, LocalDateTime start, LocalDateTime end, String roomName,
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
     * Cancels event with ID eventID. Cancelling an event results in removing all attendees and speakers from attending
     * and removes its room. Note that the event is NOT deleted from the system.
     * @param eventId the ID of the event you wish to cancel
     */
    public JSONObject cancelEvent(String eventId) {
        this.deserializeData();
        if(!em.isEventCancelled(eventId)){
            rm.removeFromRoomSchedule(em.getEventStartTime(eventId),
                    em.getEventEndTime(eventId), em.getEventRoom(eventId), eventId);
            um.cancelAll(em.getAttendingUsers(eventId), eventId);
            for(String speakerName: em.getSpeakers(eventId)){
                um.cancelSpeakerEvent(speakerName, eventId);
            }
            em.cancelEvent(eventId);
            this.saveData();
            return op.cancelResult();
        }
        else{
            return op.alreadyCancelled();
        }

    }

    /**
     * Reschedules event with ID eventID at newStart to newEnd in roomName. Note that this should be called ONLY after
     * cancelEvent has been called
     * @param info contains the event ID (eventID), the room name (roomName) and the start time (startTime)
     *             and end time (endTime)
     * @return A JSONObject detailing the outcome
     */
    public JSONObject rescheduleEvent(JSONObject info) {
        this.deserializeData();
        String eventID = info.get("eventId").toString();
        String roomName = info.get("roomName").toString();
        LocalDateTime newStart = null;
        LocalDateTime newEnd = null;
        boolean isVipEvent = (boolean) info.get("vip");
        int capacity = (int) info.get("capacity");
        try{
            newStart = (LocalDateTime) info.get("start");
            newEnd = (LocalDateTime) info.get("end");
        }catch(ClassCastException ignored){}

        if(roomName == null){
            return op.roomIsNull();
        }else if(capacity <= 0){
            return op.invalidCapacityError();
        }else if(newStart == null | newEnd == null){
            return op.invalidDateError();
        }
        else if(rm.addToRoomSchedule(newStart, newEnd, roomName, eventID)){
            em.changeEventTime(eventID, newStart, newEnd);
            em.changeEventRoom(eventID, roomName);
            em.changeVipStatus(eventID, isVipEvent);
            em.changeEventCap(eventID, capacity);
            em.rescheduleEvent(eventID);
            this.saveData();
            return op.rescheduleSuccess();
        }
        return op.rescheduleFailure(roomName);
    }

    /**
     * Changes the capacity of a given Event.
     *
     * @param eventInfo contains the information about the event who's capacity is attempting to be changed
     * @return a JSONObject containing whether the capacity was changed successfully or not.
     */
    public JSONObject changeEventCapacity(JSONObject eventInfo){
        this.deserializeData();

        String eventID = eventInfo.get("eventID").toString();
        int capacity = (int) eventInfo.get("capacity");
        String roomName = eventInfo.get("roomName").toString();
        if (capacity < 1){
            return op.invalidCapacityError();
        } else if (rm.getRoomCapacity(roomName) < capacity){
            return op.cannotAccommodate();
        } else if (em.getAttendingUsers(eventID).size() > capacity){
            return op.cannotChangeCap(capacity);
        } else {
            em.changeEventCap(eventID, capacity);
            this.saveData();
            return op.changeCapResult();
        }
    }

    public JSONObject listRoomSchedule(String roomName, LocalDateTime time) {
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventName: rm.getEventsInRoomAfter(roomName, time)){
            System.out.println(em.getEventIDByName(eventName));
            array.add(em.getEventJson(em.getEventIDByName(eventName)));
        }
        System.out.println(array);
        return op.listRoomSchedule(array);
    }

    public JSONObject getAllUsers(){
        this.deserializeData();
        return op.getAllUsers(um.getAllUsersJson());
    }

    public JSONObject getAllSpeakers(){
        this.deserializeData();
        return op.getAllSpeakers(um.getAllSpeakersJson());
    }
}
