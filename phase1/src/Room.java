import java.time.LocalDateTime;
import java.util.HashMap;

public class Room {
    private String name;
    private HashMap<LocalDateTime, String> schedule;

    public Room(String name){
        this.name = name;
        this.schedule = new HashMap<>();
    }

//    public String getName() {
//        return this.name;
//    }

//    public HashMap<LocalDateTime, String> getSchedule() {
//        return this.schedule;
//    }

    public boolean canBook(LocalDateTime time){
        return this.schedule.containsKey(time) && time.getHour() <= 16; // if event starts before/at 4pm (16:00)
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

}
