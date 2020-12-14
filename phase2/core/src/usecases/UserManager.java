package usecases;

import entities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *	A Use Case class that manages Users.
 *
 * @author Edmund Lo, dylan (requestSchedule)
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
     * Gets the User attribute given the username of this user.
     *
     * @return the user attribute
     */
    public User getUser(String username) {
        return this.allUsers.get(username);
    }

    /**
     * Gets all the Users.
     *
     * @return a hash map of all users with usernames as keys and User attribute as values
     */
    public HashMap<String, User> getAllUsers() {
        return this.allUsers;
    }

    /**
     * Creates new attendee with username and password.
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     */
    public void createNewAttendee(String username, String firstName, String lastName, boolean vip) {
        Attendee attendee = new Attendee(username, firstName, lastName, vip);
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
        if(allUsers.containsKey(username)){
            Speaker speaker = (Speaker) allUsers.get(username);
            return speaker.canSignUp(eventID, startTime, endTime) &&
                    speaker.canAddSpeakerEvent(eventID, startTime, endTime);
        }
        return false;
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
     * Adds messageThreadId of the sent message to user's primary inbox.
     *
     * @param username the username of the sender
     * @param messageThreadId the message ID
     */
    public void sendMessage(String username, String messageThreadId) {
        allUsers.get(username).sendMessage(messageThreadId);
    }

    /**
     * Adds messageThreadId of the received message to user's primary inbox.
     *
     * @param username the username of the receiver
     * @param messageThreadId the message ID
     */
    public void receiveMessage(String username, String messageThreadId) {
        allUsers.get(username).receiveMessage(messageThreadId);
    }

    /**
     * Change the current status of a messageThread for a specific user.
     *
     * @param username the username of the receiver
     * @param messageThreadId the message ID
     */
    public void changeReadForMes(String username, String messageThreadId) {
        allUsers.get(username).changeRead(messageThreadId);
    }

    /**
     * Change the current status of a messageThread for a specific user to false.
     *
     * @param username the username of the receiver
     * @param messageThreadId the message ID
     */
    public void unreadForMes(String username, String messageThreadId) {
        allUsers.get(username).unread(messageThreadId);
    }

    /**
     * Archive messageThreadId of the message to user's inbox.
     *
     * @param username the username of the receiver
     * @param messageThreadId the message ID
     */
    public void archiveMessage(String username, String messageThreadId) {
        allUsers.get(username).archiveToInbox(messageThreadId);
    }

    /**
     * Moves messageThreadId of the message from user's primary inbox to trash box.
     *
     * @param username the username of the sender
     * @param messageThreadId the message ID
     */
    public void moveToTrashInbox(String username, String messageThreadId) {
        allUsers.get(username).moveToTrash(messageThreadId);
    }

    /**
     * delete messageThreadId of the message from any of three user's inboxes.
     *
     * @param messageThreadId the message ID
     */

    public void deleteMessageFromUsers(String messageThreadId){
        for(User user : this.allUsers.values()){
            user.deleteMessageFromInboxes(messageThreadId);
        }
    }

    /**
     * Moves messageThreadId of the message from user's archived inbox back to primary inbox.
     *
     * @param username the username of the receiver
     * @param messageThreadId the messageID
     */
    public void backFromArchived(String username, String messageThreadId) {
        allUsers.get(username).archivedBackToPrimary(messageThreadId);
    }

    /**
     * Deletes messageThreadId of the message from user's trash inbox back to primary inbox.
     *
     * @param username the username of the receiver
     * @param messageThreadId the messageID
     */
    public void backFromTrash(String username, String messageThreadId) {
        allUsers.get(username).trashBackToPrimary(messageThreadId);
    }

    /**
     * Gets the user's primary inbox messages.
     *
     * @param username the username of the user
     * @return An arraylist of the user's primary messages
     */
    public HashMap<String, Boolean> getPrimaryMessages(String username) {
        return allUsers.get(username).getPrimaryInbox();
    }

    /**
     * Gets the user's archived messages.
     *
     * @param username the username of the user
     * @return An arraylist of the user's Archived messages
     */
    public HashMap<String, Boolean> getArchivedMessages(String username) {
        return allUsers.get(username).getArchivedInbox();
    }

    /**
     * Gets the user's trash messages.
     *
     * @param username the username of the user
     * @return An arraylist of the user's trash messages
     */
    public HashMap<String, Boolean> getTrashMessages(String username) {
        return allUsers.get(username).getTrashInbox();
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
     * Gets users first and last name
     * @param username the username
     * @return string array of first and last name
     */
    public String[] getUserInfo(String username) {
        return new String[] {allUsers.get(username).getFirstName(), allUsers.get(username).getLastName()};
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
     * Checks if user is a speaker.
     *
     * @param username the username
     * @return iff user is speaker
     */
    public boolean isSpeaker(String username) {
        return allUsers.get(username) instanceof Speaker;
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
                if ((user).isVip()) {
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
     * checks if user can be added to friend's list
     *
     * @param user the username of user
     * @param friend the username of friend being added
     * @return boolean for whether the user can be added
     */
    public boolean canSendRequest(String user, String friend){
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

    /**
     * sends friend request from user to friend
     *
     * @param user the username of user
     * @param friend the username of friend being added
     *
     */
    public void sendFriendRequest(String user, String friend){
        allUsers.get(friend).getFriendRequest().add(user);
        allUsers.get(user).getSentRequest().add(friend);
    }

    /**
     * adds friend to user's friends list and vice versa
     *
     * @param user the username of user
     * @param friend the username of friend being added
     *
     */
    public void addFriend(String user, String friend){
        allUsers.get(user).getFriendRequest().remove(friend);
        allUsers.get(friend).getSentRequest().remove(user);
        allUsers.get(user).addFriend(friend);
        allUsers.get(friend).addFriend(user);
    }

    /**
     * removes friend from user's friend list
     *
     * @param user the username of user
     * @param friend the username of friend being added
     *
     */
    public void removeFriend(String user, String friend){
        allUsers.get(user).removeFriend(friend);
        allUsers.get(friend).removeFriend(user);
    }

    /**
     * declines friend request from friend to user
     *
     * @param user the username of user
     * @param friend the username of friend being added
     *
     */
    public void declineRequest(String user, String friend){
        allUsers.get(friend).getSentRequest().remove(user);
        allUsers.get(user).getFriendRequest().remove(friend);
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllFriendsJson(String username){
        JSONArray array = new JSONArray();

        for(String user: allUsers.get(username).getFriendsList())
            array.add(allUsers.get(user).convertToJSON());

        return array;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllNonFriendsJson(String username){
        List<String> friends = allUsers.get(username).getFriendsList();
        List<String> sent = allUsers.get(username).getSentRequest();
        List<String> received = allUsers.get(username).getFriendRequest();

        JSONArray array = new JSONArray();

        for(String user: allUsers.keySet()) {
            if (!friends.contains(user) && !user.equals(allUsers.get(username).getUsername())){
                if (!sent.contains(user) && !received.contains(user)){
                    array.add(allUsers.get(user).convertToJSON());
                }
            }
        }

        return array;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllSentRequestsJson(String username){
        JSONArray array = new JSONArray();

        for(String user: allUsers.get(username).getSentRequest())
            array.add(allUsers.get(user).convertToJSON());

        return array;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllFriendsRequestsJson(String username){
        JSONArray array = new JSONArray();

        for(String user: allUsers.get(username).getFriendRequest())
            array.add(allUsers.get(user).convertToJSON());

        return array;
    }

    /**
     * Gets a user's attending events
     *
     * @param username the username
     * @return a set containing event ids
     */
    public Set<String> getEvents(String username){
        return allUsers.get(username).getSchedule().keySet();
    }


//    /** Not enough time to fully implement schedule download feature
//     * Formats a schedule based on three types of requested schedules
//     * @param day as type LocalDateTime for formatting
//     *           input a day of which a user would like to see events on
//     * This method should print all events at the specified day
//     * @Overload Request by Day
//     * @author dylan
//     */
//    public void requestSchedule(String username, LocalDateTime day) {
////        String formatted_schedule = "";
////        SimpleDateFormat dtx = new SimpleDateFormat();
////        String
////        upperDay = day.toUpperCase();
////        formatted_day = upperDay.format()
////        DayOfWeek requestedDay = upperDay.format()
////        DayOfWeek requestedDay = day.getDayOfWeek();
////
////        day.getDayOfWeek().;
//    }

//    /**
//     * Requests schedule by Speaker
//     * Similar implementation to Room toString though key value pairs are assigned in reverse
//     * Assumes a valid speaker is passed
//     * @param username
//     * @param speaker
//     * @Overload request by Speaker
//     * @author dylan, @version 1.0
//     * @return formatted schedule of all events that Speaker is present at
//     */
//    //Potential Update could be avoiding passing in a username
//    public String requestSchedule(String username, Speaker speaker) {
//        if(speaker.getSpeakerSchedule().size() == 0) {
//            return "This speaker has no talks currently";
//        }
//
//        StringBuilder ret = new StringBuilder("Schedule of Speaker " + speaker.getUsername() + ":" + "\n");
//        DateTimeFormatter dayTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
//        DateTimeFormatter hourMin = DateTimeFormatter.ofPattern("HH:mm");
//        for (Map.Entry<String, LocalDateTime[]> event : speaker.getSpeakerSchedule().entrySet()) {
//            String eventStartTime = dayTime.format(event.getValue()[0]);
//            String eventName = event.getKey();
//            ret.append(eventStartTime);
//            ret.append("-");
//            ret.append(hourMin.format(event.getValue()[1]));
//            ret.append(" -- ");
//            ret.append(eventName);
//            ret.append("\n");
//        }
//        return ret.toString();
//    }

//    /**
//     * Returns a formatted schedule with constraints based on timeframe
//     * This method prints the events of the time constraints that only appear in the User's schedule
//     * @param username Username of the user
//     * @param event_time the time range requirement for events of interest
//     * @author dylan
//     * @return a formatted schedule (String) of the events by ID at the required time frame
//     */
//    //request schedule based on time constraint for the events that the username has in their schedule
//    //Events can go for more than a day
//    public String requestScheduleByTime(String username, LocalDateTime[] event_time) {
//
//        DateTimeFormatter dayTime = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        DateTimeFormatter hourMin = DateTimeFormatter.ofPattern("HH:mm");
//        String eventStartTime = hourMin.format(event_time[0]);
//        String endTime = hourMin.format(event_time[1]);
//        StringBuilder ret = new StringBuilder("Here is your schedule of events by their IDs from time " + eventStartTime
//                + "-" + endTime + ":" + "\n");
//        for (Map.Entry<String, LocalDateTime[]> scheduleByTime: this.allUsers.get(username).getSchedule().entrySet()) {
//            String userScheduleStartTime = hourMin.format(scheduleByTime.getValue()[0]);
//            String userScheduleEndTime = hourMin.format(scheduleByTime.getValue()[1]);
//            String eventName = scheduleByTime.getKey();
//            if ((eventStartTime.equals(userScheduleStartTime)) && (endTime.equals(userScheduleEndTime))) {
//                ret.append(dayTime.format(scheduleByTime.getValue()[0]));
//                ret.append(" -- ");
//                ret.append(eventName);
//                ret.append("\n");
//            }
//        }
//         return ret.toString();
//    }
//    /**
//     * Requests schedule by Day
//     * Assumes a valid eventDay is passed
//     * @param username
//     * @param eventDay Specified Day constraint on schedule
//     * @author dylan, @version 1.0
//     * @return formatted schedule of all events that Speaker is present at
//     */
//    //fix assumption that events can not go for longer than a day
//    public String requestScheduleByDay(String username, LocalDateTime[] eventDay) {
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
//        String formatted_day = sdf.format(eventDay);
//        DateTimeFormatter dayTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
//        //DateTimeFormatter hourMin = DateTimeFormatter.ofPattern("HH:mm");
//
//        StringBuilder ret = new StringBuilder("Here are the events in your schedule on "
//                + formatted_day + "'s:" + "\n");
//        for (Map.Entry<String, LocalDateTime[]> scheduleByDay: this.allUsers.get(username).getSchedule().entrySet()) {
//            String formatted_sbd = sdf.format(scheduleByDay);
//            String formatted_eventDay = sdf.format(eventDay);
//
//            if(formatted_sbd.equals(formatted_eventDay)) {
//                String event_StartTime = dayTime.format(scheduleByDay.getValue()[0]);
//                String eventName = scheduleByDay.getKey();
//                ret.append(event_StartTime);
//                ret.append("-");
//                ret.append(dayTime.format(scheduleByDay.getValue()[1]));
//                ret.append(" -- ");
//                ret.append(eventName);
//                ret.append("\n");
//            }
//        }
//        return ret.toString();
//    }


    /**
     * Gets a user Json
     *
     * @param username the username
     * @return a Json object of the user
     */
    public JSONObject getUserJson(String username){
        return this.allUsers.get(username).convertToJSON();
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllUsersJson(){
        JSONArray array = new JSONArray();

        for(String username: allUsers.keySet())
            array.add(allUsers.get(username).convertToJSON());

        return array;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllAttendeesJson(){
        JSONArray array = new JSONArray();

        for(String username: allUsers.keySet())
            if(isAttendee(username)) {
                array.add(allUsers.get(username).convertToJSON());
            }

        return array;
    }

    /**
     * @return A JSONObject that contains the JSON representation of this class
     */
    @SuppressWarnings("unchecked")
    public JSONArray getAllSpeakersJson(){
        JSONArray array = new JSONArray();

        for(String username: allUsers.keySet())
            if(isSpeaker(username)) {
                array.add(allUsers.get(username).convertToJSON());
            }

        return array;
    }

    /**
     * for checking if the user can be friends with another user
     * @param friend the friend
     * @param user the user
     * @return True iff the user can be friends with the other user
     */
    public boolean canBeFriends(String friend, String user) {
        return !(allUsers.get(friend).getFriendsList().contains(user) |
                allUsers.get(user).getFriendsList().contains(friend));
    }

    /**
     * getter for all users not including self in JSON format
     * @param self the user requesting this
     * @return JSONArray where each entry is the JSON representation of each user
     */
    public JSONArray getAllUsersNotSelfJson(String self) {
        JSONArray array = new JSONArray();

        for(String username: allUsers.keySet())
            if(!allUsers.get(username).getUsername().equals(self)) {
                array.add(allUsers.get(username).convertToJSON());
            }
        return array;
    }
}
