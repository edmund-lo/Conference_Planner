package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Speaker extends User implements Serializable {
    private HashMap<String, LocalDateTime[]> speakerSchedule;

    public Speaker(String username, String password) {
        super(username, password);
        this.speakerSchedule = new HashMap<>();
    }

    public HashMap<String, LocalDateTime[]> getSpeakerSchedule() {
        return speakerSchedule;
    }

    public void addSpeakerEvent(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime[] time = {startTime, endTime};
        speakerSchedule.put(eventID, time);
    }

    public void cancelSpeakerEvent(String eventID) {
        speakerSchedule.remove(eventID);
    }

    public boolean canAddSpeakerEvent(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        if (speakerSchedule.containsKey(eventID)) {
            return false;
        } else {
            for (LocalDateTime[] time : speakerSchedule.values()) {
                if ((time[0].isBefore(startTime) && time[1].isAfter(startTime)) ||
                        (time[0].isBefore(endTime) && time[1].isAfter(endTime))) {
                    return false;
                }
            }
            return true;
        }
    }
}
