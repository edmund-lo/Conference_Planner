import java.util.HashMap;
import java.util.UUID;

/**
 * Helper that manages all interaction with the Event classes and ensures no rules are broken.
 */
public class EventManager{
    private HashMap<String, Event> allEvents;

    /**
     * Constructor for EventManager
     *
     * @param allEvents A hashmap where the values are events and the keys are the IDs to each respective Event
     */
    public EventManager(HashMap<String, Event> allEvents){
        this.allEvents = allEvents;
    }

    /**
     * A helper to check if the event is in allEvents
     *
     * @param eventID the ID of the event we are checking
     * @return True iff the event is in allEvents
     */
    private boolean eventExists(String eventID){
        return allEvents.containsKey(eventID);
    }

    /**
     * Creator for a new event
     *
     * @param eventName the name of the event
     * @param roomID the ID of the room that the event is held in
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     */
    public void createNewEvent(String eventName, String roomID, LocalDateTime startTime, LocalDateTime endTime){
        Event newEvent = new Event(UUID.randomUUID().toString(), roomID, eventName, startTime, endTime)
        allEvents.put(newEvent.getEventID(), newEvent);
    }

    /**
     * remove event with ID eventID from allEvents
     *
     * @param eventID the ID of the event that wishes to be removed
     * @return True iff the event was removed successfully
     */
    public boolean removeEvent(String eventID){
        if(eventExists(eventID)){
            allEvents.remove(eventID);
            return true;
        }else{
            return false;
        }
    }

    /**
     * changes the time at which the event is held. That is, changes the start and end time of the event
     *
     * @param eventID the ID of the event that wishes to have its time changed
     * @param startTime the new start time of the event
     * @param endTime the new end time of the event
     * @return True iff the time was changed successfully
     */
    public boolean changeEventTime(String eventID, LocalDateTime startTime, LocalDateTime endTime){
        if(eventExists(eventID)){
            allEvents.get(eventID).changeTime(startTime, endTime)
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * adds user with ID userID to event with ID eventID. The event must have enough capacity and the event must exist
     *
     * @param eventID the ID of the event
     * @param userID the ID of the user
     * @return True iff the user with ID userID was added successfully to event with ID eventID
     */
    public boolean addUserToEvent(String eventID, String userID){
        currentEvent = allEvents.get(eventID);
        if(eventExists(eventID) && currentEvent.getAttendingUsers().size() < 2){
            return currentEvent.addUserToEvent(userID);
        }else{
            return false;
        }
    }

    /**
     * removes user with ID userID from event with ID eventID. The event must exist
     *
     * @param eventID the ID of the event
     * @param userID the ID of the user
     * @return True iff the user with ID userID was removed successfully from event with ID eventID
     */
    public boolean removeUserFromEvent(String eventID, String userID){
        currentEvent = allEvents.get(eventID);
        if(eventExists(eventID)){
            return currentEvent.removeUserFromEvent(userID);
        }else{
            return false;
        }
    }

    /**
     * checks to see if speaker with ID speakerID can be added to event with ID eventID
     *
     * @param speakerID the ID of the speaker
     * @param eventID the ID of the event
     * @return True iff the speaker was successfully added to the event with ID eventID
     */
    public boolean canAddSpeakerToEvent(String speakerID, String eventID){
        if(eventExists(eventID) && allEvents.get(eventID).getSpeakerID() == null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * adds speaker with ID speakerID to event with ID eventID
     *
     * @param speakerID ID of the speaker
     * @param eventID ID of the event
     */
    public addSpeakerToEvent(String speakerID, String eventID){
        allEvents.get(eventID).setSpeaker(speakerID);
    }

}