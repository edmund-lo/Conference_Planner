package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

/**
 * An Entity class representing a conference room at the conference.
 *
 * @author Keegan McGonigal
 * @version 1.0
 *
 */

public class Room implements Serializable {
    private final String name;
    private TreeMap<LocalDateTime[], String> schedule;

    /**
     * Constructor for a Room object
     *
     * @param name  the name of the room
     */
    public Room(String name){
        this.name = name;
        this.schedule = new TreeMap<>();
    }

//    /**
//     * Returns the name of the Room
//     *
//     * @return the room's name
//     */
//    public String getName() {
//        return this.name;
//    }

    /**
     * Checks to see if this Room can be booked for an event. A room can be booked if there is a free
     * time slot and the event ends before or at 5pm.
     *
     * @return true if the room can be booked, false otherwise
     */
    public boolean canBook(LocalDateTime startTime, LocalDateTime endTime){
        for (Map.Entry<LocalDateTime[], String> times : this.schedule.entrySet()){
            LocalDateTime bookedStartTime = times.getKey()[0];
            LocalDateTime bookedEndTime = times.getKey()[1];
            if ((bookedStartTime.isBefore(startTime) && bookedEndTime.isAfter(startTime)) ||
                    (bookedStartTime.isBefore(endTime) && bookedEndTime.isAfter(endTime))) { // if room is not booked
                return false;
            }
        }
        return endTime.getHour() <= 17; // event ends before/at 5pm (17:00)
    }

    /**
     * Adds an event to this Room at the given time.
     *
     * @param startTime the time the event starts.
     * @param endTime   the time the event ends.
     * @param eventName the name of the event to be added.
     */
    public void addEvent(LocalDateTime startTime, LocalDateTime endTime, String eventName){
        LocalDateTime[] times = new LocalDateTime[]{startTime, endTime};
        this.schedule.put(times, eventName);
    }

    /**
     * Checks to see if this Room's schedule has a specific event.
     *
     * @param time      the time the event starts.
     * @param eventName the name of the event to check for.
     * @return true if this event is scheduled in this room, false otherwise
     */
    public boolean hasEvent(LocalDateTime startTime, LocalDateTime endTime, String eventName){
       LocalDateTime[] times = new LocalDateTime[]{startTime, endTime};
        return  this.schedule.containsKey(times)
                && this.schedule.get(times).equals(eventName);
    }

    /**
     * Removes an event at a certain time from the schedule of this Room.
     *
     * @param time  the time the event starts.
     */
    public void removeEvent(LocalDateTime startTime, LocalDateTime endTime){
        LocalDateTime[] times = new LocalDateTime[]{startTime, endTime};
        this.schedule.remove(times);
    }

    /**
     * Gives the String representation of this Room.
     *
     * @return the string representation of this Room
     */
    @Override
    public String toString() {
        return this.name + " Room";
    }

    /**
     * Gives the String representation of this Room's schedule.
     *
     * @return the string representation of this Room's schedule
     */
    public String roomScheduleToString() {
        StringBuilder ret = new StringBuilder(this.name + " Room's Schedule:" + "\n");
        DateTimeFormatter dayTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter hourMin = DateTimeFormatter.ofPattern("HH:mm");
        for (Map.Entry<LocalDateTime[], String> time : this.schedule.entrySet()) {
            String eventStartTime = dayTime.format(time.getKey()[0]);
            String eventName = time.getValue();
            ret.append(eventStartTime);
            ret.append(" - ");
            ret.append(hourMin.format(time.getKey()[1]));
            ret.append(eventName);
            ret.append("\n");
        }
        return ret.toString();
    }
}