import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Implementation of an event with all details pretaining to it stored inside.
 */
public class Event {
    private String eventName;
    private String eventID;
    private String speakerID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> attendingUsers;

    /**
     * Constructor for Event
     *
     * @param eventID The randomly generated ID for this Event
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
    public String getSpeakerID() {
        return speakerID;
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
    public List<String> getAttendingUsers() {
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
     * the toString method for Event
     *
     * @return a String representation of Event that contains the event name, time and number of attending users
     */
    public String toString(){
        return "Event Name: "+this.eventName+"\n" +
                "Time: "+String.valueOf(this.startTime.getHour())+" to "+String.valueOf(this.endTime.getHour())+" on "+
                String.valueOf(this.startTime.getDayOfMonth())+"/"+String.valueOf(this.startTime.getMonthValue())+"\n" +
                "# of Attending Users: "+String.valueOf(this.attendingUsers.size());
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
     * @param speakerID the ID of the speaker that wants to be added to Event
     */
    public setSpeaker(String speakerID){
        this.speakerID = speakerID;
    }

}
