import java.time.LocalDateTime;
import java.util.Map;
import java.util.SortedMap;
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

    public Room(String name){
        this.name = name;
        this.schedule = new TreeMap<>();
    }

    public String getName() {
        return this.name;
    }

    public TreeMap<LocalDateTime, String> getSchedule() {
        return this.schedule;
    }

    public boolean canBook(LocalDateTime time){
        return !this.schedule.containsKey(time) // if room is free and
                && time.getHour() <= 16;        // event starts before/at 4pm (16:00)
    }

    public void addEvent(Event event){
        this.schedule.put(event.getStartTime(), event.getEventName());
    }

    public boolean hasEvent(Event event){
        LocalDateTime eventTime = event.getStartTime();
        return this.schedule.containsKey(eventTime) && this.schedule.get(eventTime).equals(event.getEventName());
    }

    public void removeEvent(Event event){
        this.schedule.remove(event.getStartTime());
    }

    @Override
    public String toString() {
        return this.name + "Room";
    }

    public String roomScheduleToString() {
        String ret = this.name + "Room's Schedule: \n";
        for (Map.Entry<LocalDateTime, String> time : this.schedule.entrySet()) {
            Integer hour = time.getKey().getHour();
            Integer minute = time.getKey().getMinute();
            String eventHour = hour.toString();
            String eventMinute = minute.toString();
            String eventName = time.getValue();

            ret += eventHour + ":" + eventMinute + " - " + eventName + "\n";
        }
        return ret;
    }
}
