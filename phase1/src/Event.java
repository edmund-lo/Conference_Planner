import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private String eventName;
    private String eventID;
    private String roomID;
    private String speakerID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> attendingUsers;

    public Event(String eventID, String roomID, String eventName, LocalDateTime startTime,
                 LocalDateTime endTime, String speakerID){
        this.eventiD = eventID;
        this.roomID = roomID;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speakerID = speakerID;
        attendingUsers = new ArrayList<String>();
    }

    public String getSpeakerID() {
        return speakerID;
    }

    public String getEventID() {
        return eventID;
    }

    public List<String> getAttendingUsers() {
        return attendingUsers;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean addUserToEvent(String userID){
        if(!attendingUsers.contains(userID)){
            attendingUsers.add(userID);
            return true;
        }else{
            return false;
        }

    }
    public boolean removeUserFromEvent(String userID){
        if(attendingUsers.contains(userID)){
            attendingUsers.remove(userID);
            return true;
        }
        else{
            return false;
        }

    }
    public void changeTime(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
