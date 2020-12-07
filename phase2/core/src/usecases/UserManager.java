package usecases;

import entities.*;

import org.json.simple.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * @param firstName the first name
     * @param lastName the last name
     */
    public void createNewAttendee(String username, String firstName, String lastName) {
        Attendee attendee = new Attendee(username, firstName, lastName);
        allUsers.put(username, attendee);
    }

    /**
     * Creates new organizer with username and password.
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     */
    public void createNewOrganizer(String username, String firstName, String lastName) {
        Organizer organizer = new Organizer(username, firstName, lastName);
        allUsers.put(username, organizer);
    }

    /**
     * Creates new speaker with username and password.
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     */
    public void createNewSpeaker(String username, String firstName, String lastName) {
        Speaker speaker = new Speaker(username, firstName, lastName);
        allUsers.put(username, speaker);
    }

    /**
     * Creates new admin with username and password.
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     */
    public void createNewAdmin(String username, String firstName, String lastName) {
        Admin admin = new Admin(username, firstName, lastName);
        allUsers.put(username, admin);
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
        if (allUsers.get(username) instanceof Speaker) {
            Speaker speaker = (Speaker) allUsers.get(username);
            return speaker.canSignUp(eventID, startTime, endTime) &&
                    speaker.canAddSpeakerEvent(eventID, startTime, endTime);
        }
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
     * Removes all users from an event.
     *
     * @param usernames the usernames of all users attending the event
     * @param eventID the event ID to cancel their attendance in
     */
    public void cancelAll(List<String> usernames, String eventID) {
        for (String username : usernames) {
            allUsers.get(username).cancel(eventID);
        }
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
        return speaker.canSignUp(eventID, startTime, endTime) &&
                speaker.canAddSpeakerEvent(eventID, startTime, endTime);
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
     * Adds message ID of the received message to user's inbox.
     *
     * @param username the username of the receiver
     * @param messageID the message ID
     */
    public void archiveMessage(String username, String messageID) { allUsers.get(username).archiveToInbox(messageID); }

    /**
     * Deletes message ID of the sent message from user's list of sent messages.
     *
     * @param username the username of the sender
     * @param messageID the message ID
     */
    public void deleteSentMessage(String username, String messageID) {
        allUsers.get(username).deleteSentMessage(messageID);
    }

    /**
     * Deletes messageID of the received message from user's list of received messages.
     *
     * @param username the username of the receiver
     * @param messageID the messageID
     */
    public void deleteReceivedMessage(String username, String messageID) {
        allUsers.get(username).deleteReceivedMessage(messageID);
    }

    /**
     * Deletes messageID of the received message from user's inbox.
     *
     * @param username the username of the receiver
     * @param messageID the messageID
     */
    public void deleteMessage(String username, String messageID) {
        allUsers.get(username).deleteFromInbox(messageID);
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
    public List<String> getSentMessages(String username) {
        return allUsers.get(username).getSentMessages();
    }

    /**
     * Gets the user's received messages.
     *
     * @param username the username of the receiver
     * @return An arraylist of the user's received messages
     */
    public List<String> getReceivedMessages(String username) {
        return allUsers.get(username).getReceivedMessages();
    }

    /**
     * Gets the user's inbox messages.
     *
     * @param username the username of the receiver
     * @return An arraylist of the user's received messages
     */
    public List<String> getInboxMessages(String username) {
        return allUsers.get(username).getInboxMessages();
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
            String[] info = {user.getUsername(), user.getFirstName(), user.getLastName(), user.getClass().getSimpleName()};
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
     * Checks if user is an admin.
     *
     * @param username the username
     * @return iff user is speaker
     */
    public boolean isAdmin(String username) {
        return allUsers.get(username) instanceof Admin;
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

    /**
     * Gets the usernames of all the vips.
     *
     * @return an arraylist containing the usernames of all vips
     */
    public ArrayList<String> getAllVipNames() {
        ArrayList<String> vips = new ArrayList<>();
        for (User user : allUsers.values()) {
            if (user instanceof Attendee) {
                if (((Attendee) user).isVip()) {
                    vips.add(user.getUsername());
                }
            }
        }
        return vips;
    }

    /**
     * Sets the attendee as a vip
     *
     * @param username the username
     */
    public void setAttendeeAsVip(String username) {
        Attendee attendee = (Attendee) allUsers.get(username);
        attendee.setVipStatus(true);
    }

    /**
     * Sets the attendee as not a vip
     *
     * @param username the username
     */
    public void setAttendeeAsNotVip(String username) {
        Attendee attendee = (Attendee) allUsers.get(username);
        attendee.setVipStatus(false);
    }

    /**
     * Checks if an attendee is a vip
     *
     * @param username the username
     * @return true iff the attendee is a vip
     */
    public boolean isVip(String username) {
        Attendee attendee = (Attendee) allUsers.get(username);
        return attendee.isVip();
    }

    /**
     * Gets the role of the user
     *
     * @param username the username
     * @return the role of the user
     */
    public String getUserRole(String username) {
        return allUsers.get(username).getClass().getSimpleName();
    }

    /**
     * toString for the user's username and user's role
     *
     * @return an arraylist of strings of user's username and role
     */
    public ArrayList<String> userToString() {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user: allUsers.values()){
            usernames.add(user.toString());
        }

        return usernames;
    }

    public boolean canBeFriend(String user, String friend){
        if (allUsers.get(friend).getFriendRequest().contains(user))
            return false;
        if (allUsers.get(user).getFriendRequest().contains(friend))
            return false;
        if (allUsers.get(friend).getFriendsList().contains(user))
            return false;
        if (allUsers.get(user).getFriendsList().contains(friend))
            return false;
        return true;
    }

    public void sendFriendRequest(String user, String friend){
        allUsers.get(friend).getFriendRequest().add(user);
    }

    public void addFriend(String user, String friend){
        allUsers.get(user).addFriend(friend);
        allUsers.get(friend).addFriend(user);
    }

    public void removeFriend(String user, String friend){
        allUsers.get(user).removeFriend(friend);
        allUsers.get(friend).removeFriend(user);
    }

    public void declineRequest(String user, String friend){
        allUsers.get(user).getFriendRequest().remove(friend);
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject getAllFriendsJson(String username){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allUsers.get(username).getFriendsList())
            item.put(ID, allUsers.get(ID).convertToJSON());

        array.add(item);

        json.put("Users", array);

        return json;
    }

    public Set<String> getEvents(String username){
        return allUsers.get(username).getSchedule().keySet();
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONObject getAllUsersJson(){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for(String ID: allUsers.keySet())
            item.put(ID, allUsers.get(ID).convertToJSON());

        array.add(item);

        json.put("Users", array);

        return json;
    }
}
