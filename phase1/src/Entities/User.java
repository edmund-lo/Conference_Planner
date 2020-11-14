package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract Entity class representing a user.
 *
 * @author Edmund Lo
 *
 */
public abstract class User implements Serializable {
    private String username;
    private String password;
    private HashMap<String, LocalDateTime[]> schedule;
    private ArrayList<String> sentMessages;
    private ArrayList<String> receivedMessages;

    /**
     * Constructor for User object. Initializes an empty hashmap for a user's schedule and
     * an empty arraylist for a user's sent message IDs and an empty arraylist for a user's received message IDs.
     *
     * @param username the user's username
     * @param password the user's password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.schedule = new HashMap<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
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
     * Getter for user's password.
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
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
    public ArrayList<String> getSentMessages() {
        return sentMessages;
    }

    /**
     * Getter for a user's received messages.
     *
     * @return An arraylist containing message IDs of all received messages
     */
    public ArrayList<String> getReceivedMessages() {
        return receivedMessages;
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
                if ((time[0].isBefore(startTime) && time[1].isAfter(startTime)) ||
                        (time[0].isBefore(endTime) && time[1].isAfter(endTime))) {
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
     * toString with the User's username and role
     *
     * @return String of user's username and role
     */
    public String toString() {
        return "Username: " + this.username + "\n" +  "Role: " + this.getClass().getSimpleName() + "\n";
    }

}
