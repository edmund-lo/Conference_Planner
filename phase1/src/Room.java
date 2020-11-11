import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * An Entity class representing a conference room at the conference.
 *
 * @author Keegan McGonigal
 * @version 1.0
 *
 */

public class Room {
    private String name;
    private TreeMap<LocalDateTime, String> schedule;

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
    public boolean canBook(LocalDateTime time){
        return !this.schedule.containsKey(time) // if room is not booked
                && time.getHour() <= 16;        // event starts before/at 4pm (16:00)
    }

    /**
     * Adds an event to this Room at the given time.
     *
     * @param time      the time the event starts.
     * @param eventName the name of the event to be added.
     */
    public void addEvent(LocalDateTime time, String eventName){
        this.schedule.put(time, eventName);
    }

    /**
     * Checks to see if this Room's schedule has a specific event.
     *
     * @param time      the time the event starts.
     * @param eventName the name of the event to check for.
     * @return true if this event is scheduled in this room, false otherwise
     */
    public boolean hasEvent(LocalDateTime time, String eventName){
        return  this.schedule.containsKey(time)
                && this.schedule.get(time).equals(eventName);
    }

    /**
     * Removes an event at a certain time from the schedule of this Room.
     *
     * @param time  the time the event starts.
     */
    public void removeEvent(LocalDateTime time){
        this.schedule.remove(time);
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
        String ret = this.name + " Room's Schedule:" + "\n";
        for (Map.Entry<LocalDateTime, String> time : this.schedule.entrySet()) {
            String eventHour = Integer.toString(time.getKey().getHour());
            String eventName = time.getValue();
            ret += eventHour + ":00 - " + eventName + "\n";
        }
        return ret;
    }
}