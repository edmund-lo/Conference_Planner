package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Implementation of an event with all details pertaining to it stored inside.
 */
public class Event implements Serializable {
    private String eventName;
    private String eventID;
    private ArrayList<String> speakerNames;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ArrayList<String> attendingUsers;
    private String roomName;
    private int capacity;
    private boolean needsChairs;
    private boolean needsTables;
    private boolean needsProjector;
    private boolean needsSoundSystem;

    /**
     * Constructor for Entities.Event
     *
     * @param eventID The randomly generated ID for this Entities.Event
     * @param eventName Name of the event
     * @param startTime Start time of the event
     * @param endTime End time of the event
     * @param roomName Name of the room the event is in
     */
    public Event(String eventID, String eventName, LocalDateTime startTime, LocalDateTime endTime, String roomName,
                 boolean needsChairs, boolean needsTables, boolean needsProjector, boolean needsSoundSystem,
                 int capacity){
        this.eventID = eventID;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.capacity = capacity;
        this.speakerNames = new ArrayList<>();
        this.attendingUsers = new ArrayList<>();
        this.needsChairs = needsChairs;
        this.needsTables = needsTables;
        this.needsProjector = needsProjector;
        this.needsSoundSystem = needsSoundSystem;

    }

    /**
     * Getter for ID of the speaker
     *
     * @return The ID of the speaker speaking at this event
     */
    public ArrayList<String> getSpeakerNames() {
        return speakerNames;
    }

    /**
     * getter for ID of the event
     *
     * @return The ID of this event
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * getter for the list of attending users
     *
     * @return An arraylist with IDs of all users attending this event
     */
    public ArrayList<String> getAttendingUsers() {
        return attendingUsers;
    }

    /**
     * getter for the name of this event
     *
     * @return the name of this event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * getter for the start time of this event
     *
     * @return the start time of this event
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * getter for the end time of this event
     *
     * @return the end time of this event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void changeRoomName(String roomName){
        this.roomName = roomName;
    }

    /**
     * getter for the room name of this event
     *
     * @return the room name of this event
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * the toString method for Entities.Event
     *
     * @return a String representation of Entities.Event that contains the event name, time and number of attending users
     */
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter hourMin = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder speakers = new StringBuilder();
        if(speakerNames.size() == 0) {
            speakers.append("No speakers");
        }else{
            String prefix = "";
            for(String name: this.speakerNames){
                speakers.append(prefix);
                prefix = ",";
                speakers.append(name);
            }
        }
        return "Event Name: "+this.eventName+"\n" +
                "Speaker(s): "+speakers.toString()+"\n" +
                "Time: "+dtf.format(this.startTime)+" - "+hourMin.format(this.endTime)+"\n" +
                "Number of Attending Users: "+this.attendingUsers.size() + "\n" +
                "Room Name: " + this.roomName;


    }

    /**
     * Adds the user with ID userID to the attending users list for this event
     *
     * @param userID the ID of the user that wants to be added to this event
     */
    public void addUserToEvent(String userID){
        attendingUsers.add(userID);
    }

    /**
     * Removes the user with ID userID from the attending users list for this event. The user must be in the attending
     * users list.
     *
     * @param userID the ID of the user that wants to be removed from this event
     * @return True iff the user was removed successfully and false if not
     */
    public boolean removeUserFromEvent(String userID){
        if(attendingUsers.contains(userID)){
            attendingUsers.remove(userID);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Changes the time of when the event occurs. That is, changing the start and end time of the event.
     *
     * @param startTime the new start time of the event
     * @param endTime the new end time of the event
     */
    public void changeTime(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * setter for the speakerID
     *
     * @param speakerName the ID of the speaker that wants to be added to Entities.Event
     */
    public void addSpeaker(String speakerName){
        this.speakerNames.add(speakerName);
    }

    public void removeSpeaker(String speakerName){
        this.speakerNames.remove(speakerName);
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean needsChairs() {
        return this.needsChairs;
    }

    public boolean needsTables() {
        return this.needsTables;
    }

    public boolean needsProjector() {
        return this.needsProjector;
    }

    public boolean needsSoundSystem() {
        return this.needsSoundSystem;
    }

    public JSONObject convertToJSON() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("type", "Event");

        item.put("event name", eventName);
        item.put("id", eventId);
        item.put("speaker name", speakerName);
        item.put("start", startTime);
        item.put("end", endTime);
        item.put("users", attendingUsers);
        item.put("room name", roomName);
        item.put("capacity", capacity)
        item.put("Chairs", needsChairs);
        item.put("Tables", needsTables);
        item.put("Projector", needsProjector);
        item.put("SoundSystem", needsSoundSystem);

        array.add(item);

        json.put("data", array);

        return json;
    }
}
