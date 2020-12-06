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

import static java.lang.Integer.parseInt;

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

    /**
     * Constructor for UserController object.
     * @param username current logged in user's username.
     */
    public UserController(String username) {
        EventGateway eg = new EventGateway();
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        MessageGateway mg = new MessageGateway();

        this.em = eg.deserializeData();
        this.um = ug.deserializeData();
        this.rm = rg.deserializeData();
        this.mm = mg.deserializeData();

        this.username = username;
        this.up = new UserPresenter();
        this.mp = new MessagePresenter();
    }

    /**
     *Returns list of users that the user can send messages to.
     *
     *@return list of speakers and attendees in a string format
     */
    public ArrayList<String> getAllMessageableUsers(){
        return um.getAllMessageableUsers(username);
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
            return up.cancelResult(em.getEventName(eventId));
        }
        return up.notAttendingEventError(em.getEventName(eventId));
    }

    /**
     *Returns list of events the user is attending
     *
     *@return list of events the user is attending in a string format
     */

    public List<String> getAttendingEventsString() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventDesc.add(em.getEventDescription(eventId));

        return eventDesc;
    }

    /**
     *Returns list of events the user is attending
     *
     *@return list of events the user is attending in a string format
     */

    public List<String> getAttendingEvents() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        return new ArrayList<>(schedule.keySet());
    }

    /**
     *Returns list of all events desc in the conference that user with username can sign up to
     *
     *@return list of all events in the conference in a JSONARRAY format
     */
    public List<String> getAllEventsCanSignUp(){
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String id : em.getAllEventIds()){
            if(um.canSignUp(username, id, em.getEventStartTime(id), em.getEventEndTime(id))) {
                eventDesc.add(em.getEventDescription(id));
            }
        }
        return eventDesc;
    }

    /**
     * Gets a list of all events
     *
     * @return a list of all events
     */
    public List<String> getAllEvents(){
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String id : em.getAllEventIds()){
            eventDesc.add(em.getEventDescription(id));
        }
        return eventDesc;
    }

    /**
     * Gets a list of all events IDs user can sign up for
     *
     * @return a list of all events user can sign up for
     */
    public JSONArray getAllSignUpEvents(){
        JSONArray eventDesc = new JSONArray();
        for (String id : em.getAllEventIds()){
            if(um.canSignUp(username, id, em.getEventStartTime(id), em.getEventEndTime(id))) {
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
            return mp.messageSent(recipientName);
        } else {
            return mp.invalidMessageError();
        }
    }

    public JSONObject addFriend(String username){
        um.addFriend(this.username, username);
        return up.friendAdded(username);
    }

    public JSONObject removeFriend(String username){
        um.removeFriend(this.username, username);
        return up.friendRemoved(username);
    }

    public JSONObject decineRequest(String username){
        um.declineRequest(this.username, username);
        return up.requestDenied(username);
    }

    public JSONObject sendFriendRequest(String username){
        if (um.canBeFriend(this.username, username)){
            um.sendFriendRequest(this.username, username);
            return up.friendRequestSent(username);
        }
        return up.cantAddFriend(username);
    }

    public JSONObject getFriends(){
        return um.getAllFriendsJson(username);
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
