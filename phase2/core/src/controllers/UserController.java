package controllers;

import gateways.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import presenters.MessagePresenter;
import presenters.UserPresenter;
import usecases.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
     * deserialize all data
     */
    public void deserializeData() {
        this.em = eg.deserializeData();
        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();
        this.mm = mg.deserializeData();
        this.uam = uag.deserializeData();
    }

    /**
     *Called when user signs up for an event.
     * @param  eventId id of the event user is signing up for.
     *
     */
    public JSONObject signUpEventAttendance(String eventId) {
        this.deserializeData();

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
        this.deserializeData();

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
    public JSONObject getAttendingEvents() {
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventID: um.getEvents(username)){
            array.add(em.getEventJson(eventID));
        }

        return up.getAttendingEvents(array);
    }

    /**
     *Returns list of all events desc in the conference that user with username can sign up to
     *
     *@return list of all events in the conference in a JSONArray format
     */
    public JSONObject getAllEventsCanSignUp(){
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventID: em.getAllEventIds()){
            LocalDateTime startTime = em.getEventStartTime(eventID);
            if(em.getEventStartTime(eventID) != null && startTime.isAfter(LocalDateTime.now())){
                if(um.canSignUp(username, eventID, startTime, em.getEventEndTime(eventID))) {
                    array.add(em.getEventJson(eventID));
                }
            }
        }

        return up.getAllEventsCanSignUp(array);
    }

    /**
     * Gets a list of all events that have not happened yet
     *
     * @return a list of all events
     */
    public JSONObject getAllEvents() {
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventID: em.getAllEventIds()){
            LocalDateTime startTime = em.getEventStartTime(eventID);
            if(startTime != null && startTime.isAfter(LocalDateTime.now())) {
                array.add(em.getEventJson(eventID));
            }
        }

        return up.getAllEvents(array);
    }

    /**
     * Gets all of current user's JSON primary messages.
     *
     * @return List of Strings representing all of the user's primary messages.
     */
    public JSONObject getAllPrimaryMessages(){
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String id: um.getPrimaryMessages(this.username).keySet()){
            array.add(mm.getMessageThreadJson(id));
        }

        return up.getAllPrimaryMessages(array);
    }

    /**
     * Gets all of current user's JSON archived messages.
     *
     * @return List of Strings representing all of the user's archived messages.
     */
    public JSONObject getAllArchivedMessages(){
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String id: um.getArchivedMessages(this.username).keySet()){
            array.add(mm.getMessageThreadJson(id));
        }

        return up.getAllArchivedMessages(array);
    }

    /**
     * Gets all of current user's JSON trash messages.
     *
     * @return List of Strings representing all of the user's trash messages.
     */
    public JSONObject getAllTrashMessages(){
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String id: um.getTrashMessages(this.username).keySet()){
            array.add(mm.getMessageThreadJson(id));
        }

        return up.getAllTrashMessages(array);
    }

    /**
     * Gets number of user's unread messages
     *
     * @return JSONObject of number of unread messages
     */
    public JSONObject getUnreadMessages() {
        this.deserializeData();

        int numUnread = 0;
        for(boolean read : um.getAllUsers().get(this.username).getPrimaryInbox().values()){
            if(!read){ numUnread += 1; }
        }
        for(boolean read : um.getAllUsers().get(this.username).getArchivedInbox().values()){
            if(!read){ numUnread += 1; }
        }
        for(boolean read : um.getAllUsers().get(this.username).getTrashInbox().values()){
            if(!read){ numUnread += 1; }
        }
        return up.numberUnreadMessages(numUnread);
    }

    /**
     *Calls the user manager to add a messageId to a user's list
     *
     * @param  recipientNames usernames of the user the message is for.
     * @param  messageThreadId id of the message user is adding.
     */
    public void addMessagesToUser(ArrayList<String> recipientNames, String messageThreadId) {
        this.deserializeData();

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
        this.deserializeData();

        um.changeReadForMes(username, messageThreadId);
        this.saveData();
    }

    /**
     *Sends a message to an attendee.
     *
     * @param  register the JSON Object which stores info about the message going to send.
     */
    public JSONObject sendMessage(JSONObject register) {
        this.deserializeData();

        String content = String.valueOf(register.get("content"));
        ArrayList recipientNames = (ArrayList)(register.get("recipients"));
        String subject = String.valueOf(register.get("subject"));
        String sender = String.valueOf(register.get("sender"));
        if (mm.messageCheck(content, username, recipientNames)) {
            String messageThreadId = mm.createMessage(content, sender, recipientNames, subject);
            addMessagesToUser(recipientNames, messageThreadId);
            this.saveData();
            return mp.messageSent(recipientNames);
        } else {
            return mp.invalidMessageError();
        }
    }

    /**
     * Replies a message to an attendee.
     *
     * @param  messageThreadId the messageThreadId that the user want to reply to.
     * @param  content the
     */
    public JSONObject replyMessage(String messageThreadId, String content) {
        this.deserializeData();

        if (mm.messageThreadExists(messageThreadId)) {
            String sender = mm.reply(this.username, messageThreadId, content);
            this.saveData();
            return mp.messageReplied(sender);
        } else {
            return mp.invalidMessageIdError();
        }
    }

    /**
     * Moves messageThreadId of the message from user's primary inbox to trash box.
     *
     * @param messageThreadId the message ID
     */

    public void moveToTrash(String messageThreadId){
        um.moveToTrashInbox(this.username, messageThreadId);
    }

    /**
     * Moves messageThreadId of the message from user's primary inbox to archived inbox.
     *
     * @param messageThreadId the message ID
     */

    public void moveToArchive(String messageThreadId){
        um.archiveMessage(this.username, messageThreadId);
    }

    /**
     * Moves messageThreadId of the message from user's archived/trash inbox back to primary inbox.
     * It will first check where the message belongs to.
     *
     * @param messageThreadId the message ID
     */

    public void moveToPrimary(String messageThreadId){
        if(um.getUser(this.username).getArchivedInbox().containsKey(messageThreadId)){
            um.backFromArchived(this.username, messageThreadId);
        }else if(um.getUser(this.username).getTrashInbox().containsKey(messageThreadId)){
            um.backFromTrash(this.username, messageThreadId);
        }

    }

    /**
     * Adds user with name username to this user's friends list
     * @param username username of the person this user wishes to add
     * @return JSONObject detailing the results
     */
    public JSONObject addFriend(String username){
        this.deserializeData();
        if (um.canBeFriends(this.username, username)){
            um.addFriend(this.username, username);
            this.saveData();
            return up.friendAdded(username);
        }
        return up.cantAddFriend(username);
    }

    /**
     * Removes user with username from this user's friends list
     * @param username username of the person this user wishes to remove
     * @return JSONObject detailing the results
     */
    public JSONObject removeFriend(String username){
        this.deserializeData();

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
        this.deserializeData();

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
        this.deserializeData();

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
        this.deserializeData();

        if (um.canSendRequest(this.username, username)){
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
        this.deserializeData();
        return up.getFriends(um.getAllFriendsJson(username));
    }

    /**
     * Getter for the friend requests of this user
     * @return JSONObject containing all of the friend requests of this user
     */
    public JSONObject getFriendRequests(){
        this.deserializeData();
        return up.getFriends(um.getAllFriendsRequestsJson(username));
    }

    /**
     * Getter for the non friends of this user
     * @return JSONObject containing all of the non friends of the user
     */
    public JSONObject getNonFriends(){
        this.deserializeData();
        return up.getNonFriends(um.getAllNonFriendsJson(username));
    }

    /**
     * Getter for the sent requests of this user
     * @return JSONObject containing all of the sent requests of the suer
     */
    public JSONObject getSentRequests(){
        this.deserializeData();
        return up.getSentRequests(um.getAllSentRequestsJson(username));
    }

    /**
     * Getter for all common events
     * @param username the username
     * @return JSONObject containing all common events
     */
    public JSONObject getCommonEvents(String username){
        this.deserializeData();

        JSONArray array = new JSONArray();

        for (String eventID: um.getEvents(username)){
            for (String eventID2: um.getEvents(this.username)){
                if(eventID.equals((eventID2))){
                    array.add(em.getEventJson(eventID));
                }
            }
        }

        return up.getCommonEvents(array);
    }

    /**
     * Getter for user greeting
     * @return JSONObject of the user greeting
     */
    public JSONObject getUser() {
        this.deserializeData();
        return up.greeting(um.getUserInfo(this.username)[0], um.getUserInfo(this.username)[1]);
    }
}
