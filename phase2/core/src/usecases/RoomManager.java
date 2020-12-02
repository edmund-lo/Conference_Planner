package usecases;

import entities.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Use Case class that stores the rooms of the conference and updates the appropriate attributes of the rooms
 * to reflect their current state.
 *
 * @author Keegan McGonigal
 * @version 2.0
 *
 */

public class RoomManager implements Serializable {
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
    public boolean createRoom(String name, int capacity, boolean hasChairs, boolean hasTables, boolean hasProjector,
                              boolean hasSoundSystem) {
        if (!this.allRooms.containsKey(name)){
            this.allRooms.put(name, new Room(name, capacity, hasChairs, hasTables, hasProjector, hasSoundSystem));
            return true;
        }
        return false;
    }
//      ***** Saving method for phase 2
//    /**
//     * Removes an existing Room object from this RoomManager.
//     * @param name  the name of the room to be removed.
//     * @return      a boolean value of true if the room was successfully removed, false otherwise.
//     */
//    public boolean removeRoom(String name) {
//        if (this.allRooms.containsKey(name)){
//            this.allRooms.remove(name);
//            return true;
//        }
//        return false;
//    }

    private Room getRoom(String roomName){
        return this.allRooms.get(roomName);
    }

    /**
     * Adds an event to the schedule of a given Room in this RoomManager.
     * @param startTime      the start time of the event to add
     * @param endTime        the end time of the event to add
     * @param roomName       the name of the room to add the event to.
     * @return               a boolean value of true if the event was successfully added to the room, false otherwise.
     */
    public boolean addToRoomSchedule(LocalDateTime startTime, LocalDateTime endTime, String roomName,
                                     String eventName) {
        Room room = getRoom(roomName);
        if (room.canBook(startTime, endTime)){ // && eventCap < room.getCapacity()){
            room.addEvent(startTime, endTime, eventName);
            return true;
        }
        return false;
    }

    /**
     * Removes an event to the schedule of a given Room in this RoomManager.
     *
     * @param startTime the start time of the event to be removed to the schedule of a room.
     * @param endTime   the end time of the event to be removed from the schedule of a room.
     * @param roomName  the name of the room to remove the event from.
     * @param eventName the name of the event to be removed
     * @return          a boolean value of true if the event was successfully removed from the room, false otherwise.
     */
    public boolean removeFromRoomSchedule(LocalDateTime startTime, LocalDateTime endTime, String roomName,
                                          String eventName){
        Room room = getRoom(roomName);
        if (room.hasEvent(startTime, endTime, eventName)) {
            room.removeEvent(startTime, endTime);
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
     * Gets a list of all the room names in the system that meet specific requirements.
     *
     * @return  a set containing all of the room names
     */

    public ArrayList<String> getAllRoomsWith(ArrayList<Boolean> constraints){
        ArrayList<String> possibleRooms = new ArrayList<>();
        for (Map.Entry<String, Room> room : this.allRooms.entrySet()){
            Room thisRoom = room.getValue();
            if (thisRoom.hasChairs() == constraints.get(0)
                    && thisRoom.hasTables() == constraints.get(1)
                    && thisRoom.hasProjector() == constraints.get(2)
                    && thisRoom.hasSoundSystem() == constraints.get(3)){
                possibleRooms.add(room.getKey());
            }
        }
        return possibleRooms;
    }

//      ***** Saving method for phase 2
//    /**
//     * Gets the string representation for a Room in this RoomManager.
//     *
//     * @param   roomName the name of the Room to get the String representation of.
//     * @return  the String representation of a Room in this RoomManager
//     */
//    public String getRoomString(String roomName){
//        return getRoom(roomName).toString();
//    }

    /**
     * Gets the string representation for Room in this RoomManager.
     *
     * @param   roomName the name of the Room to get the String representation of the schedule.
     * @return  the String representation of a Room's schedule in this RoomManager.
     */
    public String getRoomSchedule(String roomName){
        return getRoom(roomName).roomScheduleToString();
    }

//      ***** Saving method for phase 2
//    /**
//     * Reschedules an event that is happening inside a Room in this RoomManager.
//     *
//     * @param eventName the name of the event to be rescheduled.
//     * @param currTime  the current start time of the event.
//     * @param newTime   the new start time of the event.
//     * @param roomName  the name of the room where the event is taking place.
//     * @return          true if the event was successfully rescheduled, false otherwise.
//     */
//    public boolean reschedule(LocalDateTime currTime, LocalDateTime newTime, String roomName, String eventName){
//        Room room = getRoom(roomName);
//        if (room.hasEvent(currTime, eventName) && room.canBook(newTime)){
//            room.removeEvent(currTime);
//            room.addEvent(newTime, eventName);
//            return true;
//        }
//        return false;
//    }
}
