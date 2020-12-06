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
    private List<String> friendRequest;
    private List<String> friendsList;
    private List<String> sentMessages;
    private List<String> receivedMessages;
    private List<String> inbox;

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
        this.friendRequest = new ArrayList<>();
        this.friendsList = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        this.inbox = new ArrayList<>();
    }

    /**
     * Getter for user's username.
     *
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

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
     * Getter for a user's sent messages.
     *
     * @return An arraylist containing message IDs of all sent messages
     */
    public List<String> getSentMessages() {
        return sentMessages;
    }

    /**
     * Getter for a user's received messages.
     *
     * @return An arraylist containing message IDs of all received messages
     */
    public List<String> getReceivedMessages() {
        return receivedMessages;
    }

    public List<String> getInboxMessages() {
        return inbox;
    }

    /**
     * Getter for a user's inbox.
     *
     * @return An arraylist containing message IDs of all saved messages
     */
    public List<String> getIndex() {
        return inbox;
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
     * Adds message ID of the sent message to user's list of sent messages.
     *
     * @param messageID the message ID of the sent message
     */
    public void sendMessage(String messageID) {
        sentMessages.add(messageID);
    }

    /**
     * Adds message ID of the received message to user's list of received messages.
     *
     * @param messageID the message ID of the received message
     */
    public void receiveMessage(String messageID) {
        receivedMessages.add(messageID);
    }

    /**
     * Adds message ID of the received message to user's inbox.
     *
     * @param messageID the message ID to be add
     */
    public void archiveToInbox(String messageID) { inbox.add(messageID); }

    /**
     * Deletes message ID of the sent message from user's list of sent messages.
     *
     * @param messageID the message ID of the sent message they want to delete
     */
    public void deleteSentMessage(String messageID) {
        sentMessages.remove(messageID);
    }

    /**
     * Deletes message ID of the received message from user's list of received messages.
     *
     * @param messageID the message ID of the received message they want to delete
     */
    public void deleteReceivedMessage(String messageID) {
        receivedMessages.remove(messageID);
    }

    /**
     * Deletes message ID of the received message from user's inbox.
     *
     * @param messageID the message ID in the inbox that they want to delete
     */
    public void deleteFromInbox(String messageID) {
        inbox.remove(messageID);
    }

    /**
     * toString with the User's username and role
     *
     * @return String of user's username and role
     */
    public String toString() {
        return "Username: " + this.username + "\n" +  "Role: " + this.getClass().getSimpleName() + "\n";
    }

    public void addFriend(String username){
        friendsList.add(username);
    }

    public void removeFriend(String username){
        friendsList.remove(username);
    }

    public List<String> getFriendRequest(){
        return friendRequest;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public abstract JSONObject convertToJSON();

}
