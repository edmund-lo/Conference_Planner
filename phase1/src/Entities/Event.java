package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of an event with all details pertaining to it stored inside.
 */
public class Event implements Serializable {
    private String eventName;
    private String eventID;
    private String speakerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ArrayList<String> attendingUsers;

    /**
     * Constructor for Entities.Event
     *
     * @param eventID The randomly generated ID for this Entities.Event
     * @param eventName Name of the event
     * @param startTime Start time of the event
     * @param endTime End time of the event
     */
    public Event(String eventID, String eventName, LocalDateTime startTime,
                 LocalDateTime endTime){
        this.eventID = eventID;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        attendingUsers = new ArrayList<String>();
    }

    /**
     * Getter for ID of the speaker
     *
     * @return The ID of the speaker speaking at this event
     */
    public String getSpeakerName() {
        return speakerName;
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

    /**
     * the toString method for Entities.Event
     *
     * @return a String representation of Entities.Event that contains the event name, time and number of attending users
     */
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if(this.speakerName == null){
            return "Event Name: " + this.eventName + "\n" +
                    "Speaker: Currently Unassigned\n" +
                    "Time: " + dtf.format(this.startTime) + " to " + dtf.format(this.endTime) + "\n" +
                    "Number of Attending Users: " + this.attendingUsers.size();
        }
        return "Event Name: "+this.eventName+"\n" +
                "Speaker: "+this.speakerName+"\n" +
                "Time: "+dtf.format(this.startTime)+" to "+dtf.format(this.endTime)+"\n" +
                "Number of Attending Users: "+this.attendingUsers.size();


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
     * @param userID the ID of the user taht wants to be removed from this event
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
    public void setSpeaker(String speakerName){
        this.speakerName = speakerName;
    }

}
