import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class User {
    private String username;
    private String password;
    private HashMap<String, LocalDateTime[]> schedule;
    private ArrayList<String> messageIDs;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.schedule = new HashMap<>();
        this.messageIDs = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, LocalDateTime[]> getSchedule() {
        return schedule;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }

    public void signUp(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime[] time = {startTime, endTime};
        schedule.put(eventID, time);
    }

    public void cancel(String eventID) {
        schedule.remove(eventID);
    }

    public boolean canSignUp(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        if (schedule.containsKey(eventID)) {
            return false;
        } else {
            for (LocalDateTime[] time : schedule.values()) {
                if ((time[0].isBefore(startTime) && time[1].isAfter(startTime)) ||
                        (time[0].isBefore(endTime) && time[1].isAfter(endTime))) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addMessageID(String messageID) {
        messageIDs.add(messageID);
    }
}
