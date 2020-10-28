import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private String eventName;
    private int eventID;
    private int roomID;
    private int speakerID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Integer> attendingUsers;

    public int getSpeakerID() {
        return speakerID;
    }

    public int getEventID() {
        return eventID;
    }

    public List<Integer> getAttendingUsers() {
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

}
