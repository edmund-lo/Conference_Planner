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
     * Creates a new Room object with an empty schedule and adds it into this RoomManager.
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
     * Removes an existing Room object from this RoomManager.
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

    private Room getRoom(String roomName){
        return this.allRooms.get(roomName);
    }

    /**
     * Adds an event to the schedule of a given Room in this RoomManager.
     * @param time      the scheduled time of the event to add
     * @param roomName  the name of the room to add the event to.
     * @return          a boolean value of true if the event was successfully added to the room, false otherwise.
     */
    public boolean addToRoomSchedule(LocalDateTime time, String roomName, String eventName) {
        Room room = getRoom(roomName);
        if (room.canBook(time)){
            room.addEvent(time, eventName);
            return true;
        }
        return false;
    }

    /**
     * Removes an event to the schedule of a given Room in this RoomManager.
     *
     * @param time      the time of the event to be removed to the schedule of a room.
     * @param roomName  the name of the room to remove the event from.
     * @param eventName the name of the event to be removed
     * @return          a boolean value of true if the event was successfully removed from the room, false otherwise.
     */
    public boolean removeFromRoomSchedule(LocalDateTime time, String roomName, String eventName){
        Room room = getRoom(roomName);
        if (room.hasEvent(time, eventName)) {
            room.removeEvent(time);
            return true;
        }
        return false;
    }

    /**
     * Gets the string representation for a Room in this RoomManager
     *
     * @return  the String representation of a Room in this RoomManager
     */
    public String getRoomString(String roomName){
        return getRoom(roomName).toString();
    }

    /**
     * Gets the string representation for a Room's schedule in this RoomManager
     *
     * @return  a String representation of a Room's schedule in this RoomManager
     */
    public String getRoomSchedule(String roomName){
        return getRoom(roomName).roomScheduleToString();
    }
}
