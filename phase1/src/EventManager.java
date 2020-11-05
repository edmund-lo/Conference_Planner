import java.util.HashMap;
import java.util.UUID;
public class EventManager{
    private HashMap<String, Event> allEvents;

    public EventManager(HashMap<String, Event> allEvents){
        this.allEvents = allEvents;
    }

    private boolean eventExists(String eventID){
        return allEvents.containsKey(eventID);
    }

    public void createNewEvent(String eventName, String roomID,LocalDateTime startTime, LocalDateTime endTime, String speakerID){
        Event newEvent = new Event(UUID.randomUUID().toString(), roomID, eventName, startTime, endTime, speakerID)
        allEvents.put(newEvent.getEventID(), newEvent);
    }
    public boolean removeEvent(String eventID){
        if(eventExists(eventID)){
            allEvents.remove(eventID);
            return true;
        }else{
            return false;
        }
    }
    public boolean changeEventTime(String eventID, LocalDateTime startTime, LocalDateTime endTime){
        if(eventExists(eventID)){
            allEvents.get(eventID).changeTime(startTime, endTime)
            return true;
        }
        else{
            return false;
        }
    }
    public boolean addUserToEvent(String eventID, String userID){
        currentEvent = allEvents.get(eventID);
        if(eventExists(eventID) && currentEvent.getAttendingUsers().size() < 2){
            return currentEvent.addUserToEvent(userID);
        }else{
            return false;
        }
    }
    public boolean removeUserFromEvent(String eventID, String userID){
        currentEvent = allEvents.get(eventID);
        if(eventExists(eventID)){
            return currentEvent.removeUserFromEvent(userID);
        }else{
            return false;
        }
    }

}