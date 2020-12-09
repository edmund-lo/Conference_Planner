package controllers;

import gateways.*;
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
    protected UserAccountManager uam;
    protected String username;
    protected Scanner input;
    private final UserPresenter up;
    protected final MessagePresenter mp;
    private EventGateway eg = new EventGateway();
    private UserGateway ug = new UserGateway();
    private RoomGateway rg = new RoomGateway();
    private MessageGateway mg = new MessageGateway();
    private UserAccountGateway uag = new UserAccountGateway();

    /**
     * Constructor for UserController object.
     * @param username current logged in user's username.
     */
    public UserController(String username) {
        this.em = eg.deserializeData();
        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();
        this.mm = mg.deserializeData();
        this.uam = uag.deserializeData();
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
        uag.serializeData(uam);
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

    public JSONArray getAllEventsList() {
        JSONArray events = new JSONArray();
        for (String id : em.getAllEventIds()) {
            if (em.getEventStartTime(id).isAfter(LocalDateTime.now())) {
                events.add(em.getEventJson(id));
            }
        }
        return events;
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
     * Gets all of current user's JSON primary messages.
     *
     * @return List of Strings representing all of the user's primary messages.
     */
    public JSONArray getAllPrimaryMessages(){
        JSONArray messageStrings = new JSONArray();
        List<String> userMessageIds = um.getPrimaryMessages(username);
        for (String id : userMessageIds) {
            messageStrings.add(mm.getOneMessageThreadToJson(id));
        }
        return messageStrings;
    }

    /**
     * Gets all of current user's JSON archived messages.
     *
     * @return List of Strings representing all of the user's archived messages.
     */
    public JSONArray getAllArchivedMessages(){
        JSONArray messageStrings = new JSONArray();
        List<String> userMessageIds = um.getArchivedMessages(username);
        for (String id : userMessageIds) {
            messageStrings.add(mm.getOneMessageThreadToJson(id));
        }
        return messageStrings;
    }

    /**
     * Gets all of current user's JSON trash messages.
     *
     * @return List of Strings representing all of the user's trash messages.
     */
    public JSONArray getAllTrashMessages(){
        JSONArray messageStrings = new JSONArray();
        List<String> userMessageIds = um.getTrashMessages(username);
        for (String id : userMessageIds) {
            messageStrings.add(mm.getOneMessageThreadToJson(id));
        }
        return messageStrings;
    }

    /**
     *Calls the user manager to add a messageId to a user's list
     *
     * @param  recipientNames usernames of the user the message is for.
     * @param  messageThreadId id of the message user is adding.
     */
    public void addMessagesToUser(ArrayList<String> recipientNames, String messageThreadId) {
        um.sendMessage(this.username, messageThreadId);
        for(String recipientName : recipientNames) {
            um.receiveMessage(recipientName, messageThreadId);
        }
        this.saveData();
    }

    /**
     * Change the status of the messageThread, given it's Id
     *
     * @param  messageThreadId id of the message user want to change.
     */

    public void changeMessageStatus(String messageThreadId){
        if (mm.checkMessageStatus(messageThreadId)){
            mm.unreadMessage(messageThreadId);
        }else{mm.readMessage(messageThreadId);}
    }

    /**
     *Sends a message to an attendee.
     *
     * @param  content the contents of the message being sent.
     * @param  recipientNames usernames of the Entities.Attendee the message is for.
     */
    public JSONObject sendMessage(String content, ArrayList recipientNames, String subject) {
        if (mm.messageCheck(content, username, recipientNames)) {
            String messageThreadId = mm.createMessage(content, username, recipientNames, subject);
            addMessagesToUser(recipientNames, messageThreadId);
            this.saveData();
            return mp.messageSent(recipientNames);
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
     * Removes user with username from this user's friends list
     * @param username username of the person this user wishes to remove
     * @return JSONObject detailing the results
     */
    public JSONObject removeFriend(String username){
        um.removeFriend(this.username, username);
        this.saveData();
        return up.friendRemoved(username);
    }

    /**
     * Removes user with username from this user's sent friend requests list
     * @param username username of the person this user wishes to remove
     * @return JSONObject detailing the results
     */
    public JSONObject removeFriendRequest(String username){
        um.declineRequest(username, this.username);
        this.saveData();
        return up.requestRemoved(username);
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

    /**
     * Getter for the friend requests of this user
     * @return JSONObject containing all of the friends of this user
     */
    public JSONObject getFriendRequests(){return um.getAllFriendsRequestsJson(username);}

    public JSONObject getNonFriends(){
        return um.getAllNonFriendsJson(username);
    }

    public JSONObject getSentRequests(){
        return um.getAllSentRequestsJson(username);
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
