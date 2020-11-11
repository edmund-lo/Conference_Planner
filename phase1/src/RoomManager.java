import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * A Use Case class that stores the rooms of the conference and updates the appropriate
 * attributes of the rooms to reflect the room's current state.
 *
 * @author Keegan McGonigal
 * @version 1.0
 *
 */


public class RoomManager {
    private HashMap<String, Room> allRooms;

    /**
     * Constructs a new empty RoomManager object containing no rooms.
     */
    public RoomManager() {
        this.allRooms = new HashMap<>();
    }

    /**
     * Creates a new Room object with an empty schedule and adds it into the RoomManager.
     * @param name  the name of the new room.
     * @return      a boolean value of true if the room was successfully created, false otherwise.
     */
    public boolean createRoom(String name) {
        if (!this.allRooms.containsKey(name)){
            this.allRooms.put(name, new Room(name));
            return true;
        }
        return false;
    }

    /**
     * Removes an existing Room object from the RoomManager.
     * @param name  the name of the room to be removed.
     * @return      a boolean value of true if the room was successfully removed, false otherwise.
     */
    public boolean removeRoom(String name) {
        if (this.allRooms.containsKey(name)){
            this.allRooms.remove(name);
            return true;
        }
        return false;                
    }

    /**
     * Adds an event to the schedule of a given Room in the RoomManager.
     * @param event the event to be added to the schedule of a room.
     * @param room  the room to add the event to.
     * @return      a boolean value of true if the event was successfully added to the room, false otherwise.
     */
    public boolean addToRoomSchedule(Event event, Room room) {
        LocalDateTime eventStartTime = event.getStartTime();
        if (room.canBook(eventStartTime)) {
            room.addEvent(event);
            return true;
        }
        return false;
    }

    /**
     * Adds an event to the schedule of a given Room in the RoomManager.
     * @param event the event to be removed to the schedule of a room.
     * @param room  the room to remove the event from.
     * @return      a boolean value of true if the event was successfully removed from the room, false otherwise.
     */
    public boolean removeFromRoomSchedule(Event event, Room room) {
        if (room.hasEvent(event)) {
            room.removeEvent(event);
            return true;
        }
        return false;
    }

    public String getRoomSchedule(Room room){
        return room.roomScheduleToString();
    }
}
