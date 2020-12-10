package entities;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An abstract Entity class representing a user.
 *
 * @author Edmund Lo
 *
 */
public abstract class User implements Serializable {
    private final String username;
    private String firstName;
    private String lastName;
    private HashMap<String, LocalDateTime[]> schedule;
    private List<String> sentRequest;
    private List<String> friendRequest;
    private List<String> friendsList;
    private List<String> primaryInbox;
    private List<String> archivedInbox;
    private List<String> trashInbox;

    /**
     * Constructor for User object. Initializes an empty hashmap for a user's schedule and
     * an empty arraylist for a user's sent message IDs and an empty arraylist for a user's received message IDs.
     *
     * @param username the user's username
     * @param firstName the user's firstName
     * @param lastName the user's lastName
     */
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.schedule = new HashMap<>();
        this.sentRequest = new ArrayList<>();
        this.friendRequest = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        this.primaryInbox = new ArrayList<>();
        this.archivedInbox = new ArrayList<>();
        this.trashInbox = new ArrayList<>();
    }

    /**
     * Getter for user's username.
     *
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for user's first name
     * @return user's firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter for user's last name
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for a user's schedule of events that they are attending.
     *
     * @return A hashmap of the containing event IDs as keys and start and end times as values
     */
    public HashMap<String, LocalDateTime[]> getSchedule() {
        return schedule;
    }

    /**
     * Getter for a user's primary inbox.
     *
     * @return An arraylist containing messageThread IDs of all primary messageThreads
     */
    public List<String> getPrimaryInbox() {
        return primaryInbox;
    }

    /**
     * Getter for a user's archived inbox.
     *
     * @return An arraylist containing messageThread IDs of all archived messageThreads
     */
    public List<String> getArchivedInbox() {
        return archivedInbox;
    }


    /**
     * Getter for a user's trash inbox.
     *
     * @return An arraylist containing messageThread IDs of all trash messageThreads
     */
    public List<String> getTrashInbox() {
        return trashInbox;
    }

    /**
     * removes messagethread from user's inbox
     *
     * @param messageThreadId id of the message thread
     */
    public void deleteMessageFromInboxes(String messageThreadId){
        if(this.getPrimaryInbox().contains(messageThreadId)){
            this.primaryInbox.remove(messageThreadId);
        }else if(this.getArchivedInbox().contains(messageThreadId)){
            this.archivedInbox.remove(messageThreadId);
        }else if(this.getTrashInbox().contains(messageThreadId)){
            this.trashInbox.remove(messageThreadId);
        }
    }

    /**
     * Signs up a user for an event.
     *
     * @param eventID the event ID that they want to sign up for
     * @param startTime the event start time
     * @param endTime the event end time
     */
    public void signUp(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime[] time = {startTime, endTime};
        schedule.put(eventID, time);
    }

    /**
     * Removes user from an event.
     *
     * @param eventID the event ID they want to cancel their attendance at
     */
    public void cancel(String eventID) {
        schedule.remove(eventID);
    }

    /**
     * Checks if a user is available at the times of the event they want to sign up for.
     *
     * @param eventID the event ID that they want to sign up for
     * @param startTime the event start time
     * @param endTime the event end time
     * @return true iff they can sign up for the event
     */
    public boolean canSignUp(String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        if (schedule.containsKey(eventID)) {
            return false;
        } else {
            for (LocalDateTime[] time : schedule.values()) {
                if ((time[0].isBefore(startTime) && time[1].isAfter(startTime))) {
                    return false;
                } else if (time[0].isBefore(endTime) && time[1].isAfter(endTime)) {
                    return false;
                } else if (time[0].isEqual(startTime)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Adds messageThreadId of the sent message to user's primaryInbox.
     *
     * Precondition: the messageThreadId exist
     *
     * @param messageThreadId the messageThread Id of the sent message
     */
    public void sendMessage(String messageThreadId) {
        this.primaryInbox.add(messageThreadId);
    }

    /**
     * Adds messageThreadId of the received message to user's primaryInbox.
     *
     * Precondition: the messageThreadId exist
     *
     * @param messageThreadId the messageThreadId of the received message
     */
    public void receiveMessage(String messageThreadId) {
        this.primaryInbox.add(messageThreadId);
    }

    /**
     * Moves messageThreadId of the messageThread to user's archivedInbox.
     *
     * Precondition: the messageThreadId exist in the primary inbox
     *
     * @param messageThreadId the messageThreadId to be archived
     */
    public void archiveToInbox(String messageThreadId) {
        this.archivedInbox.add(messageThreadId);
        this.primaryInbox.remove(messageThreadId);
    }

    /**
     * Moves messageThreadId of the messageThread to user's trashInbox.
     *
     * Precondition: the messageThreadId exist in the primary inbox
     *
     * @param messageThreadId the messageThreadId of the messageThread they want to move to trash bin
     */
    public void moveToTrash(String messageThreadId) {
        this.trashInbox.add(messageThreadId);
        this.primaryInbox.remove(messageThreadId);
    }

    /**
     * Moves messageThreadId of the messageThread back to user's primaryInbox from the archivedInbox.
     *
     * Precondition: the messageThreadId exist in the archivedInbox
     *
     * @param messageThreadId the messageThreadId to be move back
     */
    public void archivedBackToPrimary(String messageThreadId) {
        this.primaryInbox.add(messageThreadId);
        this.archivedInbox.remove(messageThreadId);
    }

    /**
     * Moves messageThreadId of the messageThread back to user's primaryInbox from the trashInbox.
     *
     * Precondition: the messageThreadId exist in the trashInbox
     *
     * @param messageThreadId the messageThreadId of the messageThread they want to move back from the trash bin
     */
    public void trashBackToPrimary(String messageThreadId) {
        this.primaryInbox.add(messageThreadId);
        this.trashInbox.remove(messageThreadId);
    }

    /**
     * toString with the User's username and role
     *
     * @return String of user's username and role
     */
    public String toString() {
        return "Username: " + this.username + "\n" +  "Role: " + this.getClass().getSimpleName() + "\n";
    }

    /**
     * adds person to user's friend list
     *
     * @param username username of the person you wish to add
     */
    public void addFriend(String username){
        friendsList.add(username);
    }

    /**
     * removes person from user's friend list
     *
     * @param username username of the person you wish to remove
     */
    public void removeFriend(String username){
        friendsList.remove(username);
    }

    /**
     * getter for all friend requests
     *
     * @return a list of strings of all the friends requests user has
     */
    public List<String> getFriendRequest(){
        return friendRequest;
    }

    /**
     * getter for the friends list
     *
     * @return user's friends list
     */
    public List<String> getFriendsList() {
        return friendsList;
    }

    /**
     * getter for sent friends requests
     *
     * @return a list of strings of all the sent friend requests from user
     */
    public List<String> getSentRequest() {
        return sentRequest;
    }

    /**
     * Abstract method for converting this entity class to JSONObject format
     *
     * @return the JSONObject version of this class
     */
    public abstract JSONObject convertToJSON();

}
