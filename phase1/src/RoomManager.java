import java.time.LocalDateTime;
import java.util.HashMap;

public class RoomManager {
    private HashMap<String, Room> allRooms;

    public RoomManager() {
        this.allRooms = new HashMap<>();
    }

    public boolean createRoom(String name) {
        if (!this.allRooms.containsKey(name)){
            this.allRooms.put(name, new Room(name));
            return true;
        }
        return false;
    }

    public boolean removeRoom(String name) {
        if (this.allRooms.containsKey(name)){
            this.allRooms.remove(name);
            return true;
        }
        return false;
    }

    public boolean addToRoomSchedule(Event event, Room room) {
        LocalDateTime eventStartTime = event.getStartTime();
        if (room.canBook(eventStartTime)) {
            room.addEvent(event);
            return true;
        }
        return false;
    }

    public boolean removeFromRoomSchedule(Event event, Room room) {
        if (room.hasEvent(event)) {
            room.removeEvent(event);
            return true;
        }
        return false;
    }
}
