package UseCases;

import Entities.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

/**
 * A Use Case class that stores the rooms of the conference and updates the appropriate
 * attributes of the rooms to reflect the room's current state.
 *
 * @author Keegan McGonigal
 * @version 1.0
 *
 */

public class RoomManager implements Serializable {
    private HashMap<String, Room> allRooms;

    /**
     * Constructs a new empty UseCases.RoomManager object containing no rooms.
     */
    public RoomManager() {
        this.allRooms = new HashMap<>();
    }

    /**
     * Creates a new Entities.Room object with an empty schedule and adds it into this UseCases.RoomManager.
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
     * Removes an existing Entities.Room object from this UseCases.RoomManager.
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
     * Adds an event to the schedule of a given Entities.Room in this UseCases.RoomManager.
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
     * Removes an event to the schedule of a given Entities.Room in this UseCases.RoomManager.
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
     * Gets a list of all the room names in the system.
     *
     * @return  a set containing all of the room names
     */
    public Set<String> getAllRooms(){
        return this.allRooms.keySet();
    }

    /**
     * Gets the string representation for a Entities.Room in this UseCases.RoomManager.
     *
     * @param   roomName the name of the Entities.Room to get the String representation of.
     * @return  the String representation of a Entities.Room in this UseCases.RoomManager
     */
    public String getRoomString(String roomName){
        return getRoom(roomName).toString();
    }

    /**
     * Gets the string representation for a Entities.Room in this UseCases.RoomManager.
     *
     * @param   roomName the name of the Entities.Room to get the String representation of the schedule.
     * @return  the String representation of a Entities.Room's schedule in this UseCases.RoomManager.
     */
    public String getRoomSchedule(String roomName){
        return getRoom(roomName).roomScheduleToString();
    }

    /**
     * Reschedules an event that is happening inside a Entities.Room in this UseCases.RoomManager.
     *
     * @param eventName the name of the event to be rescheduled.
     * @param currTime  the current start time of the event.
     * @param newTime   the new start time of the event.
     * @param roomName  the name of the room where the event is taking place.
     * @return          true if the event was successfully rescheduled, false otherwise.
     */
    public boolean reschedule(LocalDateTime currTime, LocalDateTime newTime, String roomName, String eventName){
        Room room = getRoom(roomName);
        if (room.hasEvent(currTime, eventName) && room.canBook(newTime)){
            room.removeEvent(currTime);
            room.addEvent(newTime, eventName);
            return true;
        }
        return false;
    }
}
