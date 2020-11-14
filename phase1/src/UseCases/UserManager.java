package UseCases;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import Entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *	A Use Case class that manages Users.
 *
 * @author Edmund Lo
 *
 */

public class UserManager implements Serializable {
    private HashMap<String, User> allUsers;

    /**
     * Constructor for UserManager object. Initializes empty HashMap.
     *
     */
    public UserManager() {
        this.allUsers = new HashMap<>();
    }

    /**
     * Creates new attendee with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public void createNewAttendee(String username, String password) {
        Attendee attendee = new Attendee(username, password);
        allUsers.put(username, attendee);
    }

    /**
     * Creates new organizer with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public void createNewOrganizer(String username, String password) {
        Organizer organizer = new Organizer(username, password);
        allUsers.put(username, organizer);
    }

    /**
     * Creates new speaker with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public void createNewSpeaker(String username, String password) {
        Speaker speaker = new Speaker(username, password);
        allUsers.put(username, speaker);
    }

    /**
     * Checks if user is available during the time of the event they want to sign up for.
     *
     * @param username the username
     * @param eventID the event ID they want to sign up for
     * @param startTime the event start time
     * @param endTime the event end time
     * @return true iff user is available to sign up
     */
    public boolean canSignUp(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        return allUsers.get(username).canSignUp(eventID, startTime, endTime);
    }

    /**
     * Signs up user for an event.
     *
     * @param username the username
     * @param eventID the event ID they want to sign up for
     * @param startTime the event start time
     * @param endTime the event end time
     */
    public void signUp(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        allUsers.get(username).signUp(eventID, startTime, endTime);
    }

    /**
     * Remove user from an event.
     *
     * @param username the username
     * @param eventID the event ID they want to cancel their attendance in
     */
    public void cancel(String username, String eventID) {
        allUsers.get(username).cancel(eventID);
    }

    /**
     * Checks if speaker is available to speak at the event time.
     *
     * @param username the username
     * @param eventID the event ID that they will speak at
     * @param startTime the event start time
     * @param endTime the event end time
     * @return true iff the speaker is available to speak at the event
     */
    public boolean canAddSpeakerEvent(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        Speaker speaker = (Speaker) allUsers.get(username);
        return speaker.canAddSpeakerEvent(eventID, startTime, endTime);
    }

    /**
     * Assigns the speaker to speak at an event.
     *
     * @param username the username
     * @param eventID the event ID they will speak at
     * @param startTime the event start time
     * @param endTime the event end time
     */
    public void addSpeakerEvent(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        Speaker speaker = (Speaker) allUsers.get(username);
        speaker.addSpeakerEvent(eventID, startTime, endTime);
    }

    /**
     * Remove the speaker from speaking at an event.
     *
     * @param username the username
     * @param eventID the event ID they are speaking at
     */
    public void cancelSpeakerEvent(String username, String eventID) {
        Speaker speaker = (Speaker) allUsers.get(username);
        speaker.cancelSpeakerEvent(eventID);
    }

    /**
     * Adds message ID of the sent message to user's list of sent messages.
     *
     * @param username the username of the sender
     * @param messageID the message ID
     */
    public void sendMessage(String username, String messageID) {
        allUsers.get(username).sendMessage(messageID);
    }

    /**
     * Adds message ID of the received message to user's list of received messages.
     *
     * @param username the username of the receiver
     * @param messageID the message ID
     */
    public void receiveMessage(String username, String messageID) {
        allUsers.get(username).receiveMessage(messageID);
    }

    /**
     * Returns all the users that they can message.
     *
     * @param username the username
     * @return An arraylist with Strings of the usernames and roles of the users that they can message
     */
    public ArrayList<String> getAllMessageableUsers(String username) {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user: allUsers.values()){
            if ((user instanceof Speaker || user instanceof Attendee) && !user.getUsername().equals(username))
                usernames.add(user.toString());
        }

        return usernames;
    }

    /**
     * Gets the user's sent messages.
     *
     * @param username the username of the sender
     * @return An arraylist of the user's sent messages
     */
    public ArrayList<String> getSentMessages(String username) {
        return allUsers.get(username).getSentMessages();
    }

    /**
     * Gets the user's received messages.
     *
     * @param username the username of the receiver
     * @return An arraylist of the user's received messages
     */
    public ArrayList<String> getReceivedMessages(String username) {
        return allUsers.get(username).getReceivedMessages();
    }

    /**
     * Gets the schedule of all the events the user is attending.
     *
     * @param username the username
     * @return A hashmap of the event IDs as keys and start and end times as values
     */
    public HashMap<String, LocalDateTime[]> getSchedule(String username) {
        return allUsers.get(username).getSchedule();
    }

    /**
     * Gets the speaker's schedule of all events they are speaking at.
     *
     * @param username the username
     * @return A hashmap of the event IDs as keys and start and end times as values
     */
    public HashMap<String, LocalDateTime[]> getSpeakerSchedule(String username) {
        Speaker speaker = (Speaker) allUsers.get(username);
        return speaker.getSpeakerSchedule();
    }

    /**
     * Gets all usernames of all users.
     *
     * @return A set containing all usernames.
     */
    public Set<String> getAllUsernames() {
        return allUsers.keySet();
    }

    /**
     * Gets all usernames of all speakers.
     *
     * @return An arraylist containing all speaker names
     */
    public ArrayList<String> getAllSpeakerNames() {
        ArrayList<String> speakers = new ArrayList<>();
        for (User user : allUsers.values()) {
           if (user instanceof Speaker) {
               speakers.add(user.getUsername());
           }
        }
        return speakers;
    }

    /**
     * Gets info on all users' username, password and role.
     *
     * @return An arraylist of Strings containing each user's username, password and role
     */
    public ArrayList<String[]> getAccountInfo() {
        ArrayList<String[]> accountInfo = new ArrayList<>();
        for (User user : allUsers.values()) {
            String[] info = {user.getUsername(), user.getPassword(), user.getClass().getSimpleName()};
            accountInfo.add(info);
        }
        return accountInfo;
    }

    /**
     * Checks if username is unique.
     *
     * @param username the username
     * @return true iff username is unique
     */
    public boolean checkUniqueUsername(String username) {
        for (User user : allUsers.values()) {
            if (username.equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if user's login is correct.
     *
     * @param username the username
     * @param password the password
     * @return true iff user's login is correct
     */
    public boolean checkLogin(String username, String password) {
        for (User user : allUsers.values()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user is an attendee.
     *
     * @param username the username
     * @return iff user is attendee
     */
    public boolean isAttendee(String username) {
        return allUsers.get(username) instanceof Attendee;
    }

    /**
     * Checks if user is an organizer.
     *
     * @param username the username
     * @return iff user is organizer
     */
    public boolean isOrganizer(String username) {
        return allUsers.get(username) instanceof Organizer;
    }

    /**
     * Checks if user is a speaker.
     *
     * @param username the username
     * @return iff user is speaker
     */
    public boolean isSpeaker(String username) {
        return allUsers.get(username) instanceof Speaker;
    }

    /**
     * Checks if a user with that username exists.
     *
     * @param username the username
     * @return true iff that user exists
     */
    public boolean userExists(String username) {
        return allUsers.get(username) != null;
    }

}
