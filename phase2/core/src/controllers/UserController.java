package controllers;

import gateways.EventGateway;
import gateways.MessageGateway;
import gateways.RoomGateway;
import gateways.UserGateway;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.MessagePresenter;
import presenters.UserPresenter;
import usecases.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * An abstract Controller class, which all other types of user controllers inherit from.
 *
 */

public abstract class UserController {
    protected EventManager em;
    protected UserManager um;
    protected RoomManager rm;
    protected MessageManager mm;
    protected String username;
    protected Scanner input;
    private final UserPresenter up;
    protected final MessagePresenter mp;
    private EventGateway eg = new EventGateway();
    private UserGateway ug = new UserGateway();
    private RoomGateway rg = new RoomGateway();
    private MessageGateway mg = new MessageGateway();

    /**
     * Constructor for UserController object.
     * @param username current logged in user's username.
     */
    public UserController(String username) {
        this.em = eg.deserializeData();
        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();
        this.mm = mg.deserializeData();
        this.username = username;
        this.up = new UserPresenter();
        this.mp = new MessagePresenter();
    }

    /**
     * serializes the data essentially saving it
     */
    public void saveData(){
        eg.serializeData(em);
        ug.serializeData(um);
        rg.serializeData(rm);
        mg.serializeData(mm);
    }

    /**
     *Returns list of users that the user can send messages to.
     *
     *@return list of speakers and attendees in a string format
     */
    public JSONObject getAllMessageableUsers(){
        return up.getMessageableAttendeesOutput(um.getAllMessageableUsers(username));
    }

    /**
     *Called when user signs up for an event.
     * @param  eventId id of the event user is signing up for.
     *
     */
    public JSONObject signUpEventAttendance(String eventId) {
        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!um.canSignUp(username, eventId, start, end)) {
            return up.alreadySignedUpError();
        } else if (!em.canAddUserToEvent(eventId,username)){
            return up.eventFullCapacityError();
        } else {
            em.addUserToEvent(eventId,username);
            um.signUp(username, eventId, start, end);
            this.saveData();
            return up.signUpResult(em.getEventName(eventId));
        }
    }

    /**
     *Called when user cancels an event they signed up for.
     *
     * @param  eventId id of the event user is signing up for.
     *
     */
    public JSONObject cancelEventAttendance(String eventId) {
        if(em.removeUserFromEvent(eventId, username)) {
            um.cancel(username, eventId);
            this.saveData();
            return up.cancelResult(em.getEventName(eventId));
        }
        return up.notAttendingEventError(em.getEventName(eventId));
    }

    /**
     *Returns list of events the user is attending
     *
     *@return list of events the user is attending in a string format
     */

    public JSONObject getAttendingEventsString() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventDesc.add(em.getEventDescription(eventId));

        return up.getEventsData(eventDesc);
    }

    /**
     *Returns list of events the user is attending
     *
     *@return list of events the user is attending in a string format
     */

    public JSONObject getAttendingEvents() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        return up.getEventsData(new ArrayList<>(schedule.keySet()));
    }

    /**
     *Returns list of all events desc in the conference that user with username can sign up to
     *
     *@return list of all events in the conference in a JSONARRAY format
     */
    public JSONObject getAllEventsCanSignUp(){
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String id : em.getAllEventIds()){
            if(um.canSignUp(username, id, em.getEventStartTime(id), em.getEventEndTime(id))) {
                eventDesc.add(em.getEventDescription(id));
            }
        }
        return up.getEventsData(eventDesc);
    }

    /**
     * Gets a list of all events that have not happened yet
     *
     * @return a list of all events
     */
    public JSONObject getAllEvents() {
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String id : em.getAllEventIds()) {
            if (em.getEventStartTime(id).isAfter(LocalDateTime.now())) {
                eventDesc.add(em.getEventDescription(id));
            }
        }
        return up.getEventsData(eventDesc);
    }

    /**
     * Gets a list of all events IDs user can sign up for
     *
     * @return a list of all events user can sign up for
     */
    public JSONArray getAllSignUpEvents(){
        JSONArray eventDesc = new JSONArray();
        for (String id : em.getAllEventIds()){
            if(em.getEventStartTime(id).isAfter(LocalDateTime.now()) &&
                    um.canSignUp(username, id, em.getEventStartTime(id), em.getEventEndTime(id))) {
                eventDesc.add(id);
            }
        }
        return eventDesc;
    }
    /**
     * Gets all of current user's sent messages.
     *
     * @return List of Strings representing all of the user's sent messages.
     */
    public JSONArray getAllSentMessages(){
        JSONArray messageStrings = new JSONArray();
        List<String> userMessages = um.getSentMessages(username);
        for (String id : userMessages) {
            messageStrings.add(mm.getSentMessageToString(id));
        }
        return messageStrings;
    }

    /**
     * Gets all of current user's received messages.
     *
     * @return List of Strings representing all of the user's received messages.
     */
    public JSONArray getAllReceivedMessages(){
        JSONArray messageStrings = new JSONArray();
        List<String> userMessages = um.getReceivedMessages(username);
        for (String id : userMessages) {
            messageStrings.add(mm.getReceivedMessageToString(id));
        }
        return messageStrings;
    }

    /**
     *Calls the user manager to add a messageId to a user's list
     *
     *@param  messageId id of the message user is adding.
     *@param  recipientName username of the user the message is for.
     */
    public void addMessagesToUser(String recipientName, String messageId) {
        um.receiveMessage(recipientName, messageId);
        um.sendMessage(this.username, messageId);
        this.saveData();
    }

    /**
     *Sends a message to an attendee.
     *
     *
     * @param  recipientName username of the Entities.Attendee the message is for.
     * @param  content the contents of the message being sent.
     */
    public JSONObject sendMessage(String recipientName, String content) {
        if (mm.messageCheck(recipientName, username, content)) {
            String messageId = mm.createMessage(recipientName, username, content);
            addMessagesToUser(recipientName, messageId);
            this.saveData();
            return mp.messageSent(recipientName);
        } else {
            return mp.invalidMessageError();
        }
    }

    /**
     * Adds user with name username to this user's friends list
     * @param username username of the person this user wishes to add
     * @return JSONObject detailing the results
     */
    public JSONObject addFriend(String username){
        um.addFriend(this.username, username);
        this.saveData();
        return up.friendAdded(username);
    }

    /**
     * Removes ueser with name username from this user's friends list
     * @param username username of the person this user wishes to remove
     * @return JSONObject detailing the results
     */
    public JSONObject removeFriend(String username){
        um.removeFriend(this.username, username);
        this.saveData();
        return up.friendRemoved(username);
    }

    /**
     * Declines the friends request from user with name username
     * @param username the username of the friend who sent the request
     * @return JSONObject detailing the results
     */
    public JSONObject declineRequest(String username){
        um.declineRequest(this.username, username);
        this.saveData();
        return up.requestDenied(username);
    }

    /**
     * Sends a friends request to user with name username
     * @param username the username of the user
     * @return JSONObject detailing the results
     */
    public JSONObject sendFriendRequest(String username){
        if (um.canBeFriend(this.username, username)){
            um.sendFriendRequest(this.username, username);
            this.saveData();
            return up.friendRequestSent(username);
        }
        return up.cantAddFriend(username);
    }

    /**
     * Getter for the friends of this user
     * @return JSONObject containing all of the friends of this user
     */
    public JSONObject getFriends(){
        return um.getAllFriendsJson(username);
    }

    public JSONObject getCommonEvents(String username){
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        for (String eventID: um.getEvents(username)){
            for (String eventID2: um.getEvents(this.username)){
                if(eventID.equals((eventID2))){
                    item.put(eventID, em.getEventJson(eventID));
                }
            }
        }

        array.add(item);

        json.put("data", array);

        return json;
    }
    /**
     *logs the user out of the program
     *
     *@return returns the current UserController class.
     */
    public UserController logout() {
        up.logoutMessage();
        return this;
    }
}
