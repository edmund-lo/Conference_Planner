package UseCases;

import Entities.Event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


/**
 * Helper that manages all interaction with the Entities.Event classes and ensures no rules are broken.
 */
public class EventManager implements Serializable {
    private HashMap<String, Event> allEvents;

    /**
     * Constructor for UseCases.EventManager. Just initializes an empty hashmap
     *
     */
    public EventManager(){
        this.allEvents = new HashMap<String, Event>();
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
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     */
    public void createNewEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime) {
        Event newEvent = new Event(UUID.randomUUID().toString(), eventName, startTime, endTime);
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
            allEvents.get(eventID).changeTime(startTime, endTime);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * adds user with ID userID to event with ID eventID
     *
     * @param eventID the ID of the event
     * @param userID the ID of the user
     */
    public void addUserToEvent(String eventID, String userID){
        Event currentEvent = allEvents.get(eventID);
        currentEvent.addUserToEvent(userID);
    }

    /**
     * checks to see if user with ID userID can be added to event with ID eventID
     *
     * @param eventID ID of the event
     * @param userID ID of the user
     * @return True iff there is enough space for the user, if the event exists and if the user is already in the event
     */
    public boolean canAddUserToEvent(String eventID, String userID){
        if(eventExists(eventID)){
            Event currentEvent = allEvents.get(eventID);
            return currentEvent.getAttendingUsers().size() < 2 && !currentEvent.getAttendingUsers().contains(userID);
        }
        return false;
    }

    /**
     * removes user with ID userID from event with ID eventID. The event must exist
     *
     * @param eventID the ID of the event
     * @param userID the ID of the user
     * @return True iff the user with ID userID was removed successfully from event with ID eventID
     */
    public boolean removeUserFromEvent(String eventID, String userID){
        Event currentEvent = allEvents.get(eventID);
        if(eventExists(eventID)){
            return currentEvent.removeUserFromEvent(userID);
        }else{
            return false;
        }
    }

    /**
     * For getting the actual event objects
     *
     * @param eventID the ID of the event
     * @return the Entities.Event object corresponding to eventID
     */
    public Event getEventById(String eventID) {
        return allEvents.get(eventID);
    }

    /**
     * checks to see if speaker with ID speakerID can be added to event with ID eventID
     *
     * @param eventID the ID of the event
     * @return True iff the speaker was successfully added to the event with ID eventID
     */
    public boolean canAddSpeakerToEvent(String eventID){
        return eventExists(eventID) && allEvents.get(eventID).getSpeakerID() == null;
    }

    /**
     * adds speaker with ID speakerID to event with ID eventID
     *
     * @param speakerID ID of the speaker
     * @param eventID ID of the event
     */
    public void addSpeakerToEvent(String speakerID, String eventID){
        allEvents.get(eventID).setSpeaker(speakerID);
    }

    /**
     * For getting the list of all events with corresponding IDs
     *
     * @return allEvents
     */
    public HashMap<String, Event> getAllEvents() {
        return allEvents;
    }

    /**
     * Getter for a list of IDs of all events
     *
     * @return An arraylist with all of the IDs of the events in allEvents
     */
    public ArrayList<String> getAllEventIds() {
        return new ArrayList<>(allEvents.keySet());
    }
}