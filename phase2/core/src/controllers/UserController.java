package controllers;

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
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public UserController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        this.em = em;
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.username = username;
        this.input = new Scanner(System.in);
        this.up = new UserPresenter();
        this.mp = new MessagePresenter();
    }

    /**
     *UI for when user signs up for an event.
     *
     */
    public void signUpMenu(){
        while (true) {
            up.signUpEventListLabel();
            up.listEvents(getAllEventsCanSignUp());
            up.signUpEventPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option > getAllSignUpEvents().size()) //prevents index out of bounds
                    up.invalidOptionError();
                else
                    signUpEventAttendance(getAllSignUpEvents().get(option - 1)); //option-1 is the index of the selected event
                    break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                up.invalidOptionError();
            }
        }
    }

    /**
     *UI for when user cancels an event they signed up for.
     *
     */
    public void cancelMenu(){
        while(true) {//same structure as signing up
            up.cancelEventListLabel();
            up.listEvents(getAttendingEventsString());
            up.cancelEventPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option > getAttendingEvents().size())
                    up.invalidOptionError();
                else
                    cancelEventAttendance(getAttendingEvents().get(option-1));
                    break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                up.invalidOptionError();
            }
        }
    }

    /**
     * UI for when users want to see all events they're attending
     *
     */
    public void viewEventsMenu(){
        while(true){//presenter displays events
            up.listAllEventsLabel();
            up.listEvents(getAttendingEventsString());
            up.exitlistAllEventsLabel();
            try{
                int option = parseInt(input.nextLine());
                if(option == 0){ //enter 0 to go back
                    break;
                }else{
                    up.invalidOptionError();
                }
            }catch(NumberFormatException e){
                up.invalidOptionError();
            }
        }
    }

    /**
     *UI for when users want to message other users or view their messages.
     *
     */
    public void messageMenu(){
        while (true) {
            mp.messageMenuPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1){//1 to message a user
                    String name;
                    String content;
                    boolean canSend = false;
                    List<String> ms = getAllMessageableUsers();
                    if (ms.size() > 0) { //checks if there are users to message
                        mp.messageUserListLabel();
                        up.listUsers(ms);
                        mp.enterReceiverPrompt();
                        name = input.nextLine();
                        if (um.userExists(name) && !name.equals(username)) { //checks if user is valid
                            canSend = true;
                        } else {
                            mp.invalidUserError();
                        }
                        if (canSend) { //send the message if the user is valid
                            mp.enterMessagePrompt();
                            content = input.nextLine();
                            if (um.isAttendee(name) || um.isSpeaker(name))
                                sendMessage(name, content);
                            else
                                mp.cannotMessageOrganizerError();
                        }
                    }else
                        mp.noMessagableUsers();
                } else if (option == 2){// 2 to view sent messages
                    mp.showSentMessagesLabel();
                    mp.listMessages(getAllSentMessages());
                } else if (option == 3) {// 3 to view received messages
                    mp.showReceivedMessagesLabel();
                    mp.listMessages(getAllReceivedMessages());
                } else
                    up.invalidOptionError();
            } catch (NumberFormatException e) {
                up.invalidOptionError();
            }
        }
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
    public void signUpEventAttendance(String eventId) {
        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!um.canSignUp(username, eventId, start, end)) {
            up.alreadySignedUpError();
            return;
        }else if(em.isEventVip(eventId)){
            if(!um.isVip(username)){
                up.alreadySignedUpError(); //placeholder error
                return;
            }
        }
        else if (!em.canAddUserToEvent(eventId,username)){
            up.eventFullCapacityError();
            return;
        }
        em.addUserToEvent(eventId,username);
        um.signUp(username, eventId, start, end);
        up.signUpResult(em.getEventName(eventId));

    }

    /**
     *Called when user cancels an event they signed up for.
     *
     * @param  eventId id of the event user is signing up for.
     *
     */
    public void cancelEventAttendance(String eventId) {
        if(em.removeUserFromEvent(eventId, username)) {
            um.cancel(username, eventId);
            up.cancelResult(em.getEventName(eventId));
            return;
        }
        up.notAttendingEventError(em.getEventName(eventId));
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
     *Returns list of all events in the conference that user with username can sign up to
     *
     *@return list of all events in the conference in a string format
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
     * Gets a list of all events user can sign up for
     *
     * @return a list of all events user can sign up for
     */
    public List<String> getAllSignUpEvents(){
        ArrayList<String> eventDesc = new ArrayList<>();
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
        JSONArray messages = new JSONArray();
        List<String> userMessages = um.getSentMessages(username);
        if (userMessages.size() == 0) {
            mp.noMessagesReceived();
        } else {
            for (String id : userMessages) {
                messages.add(mm.getSentMessageToString(id));
            }
        }

        return messages;
    }

    /**
     * Gets all of current user's received messages.
     *
     * @return JSONArray of Strings representing all of the user's received messages.
     */
    public JSONArray getAllReceivedMessages(){
        JSONArray messages = new JSONArray();
        List<String> userMessages = um.getReceivedMessages(username);
        if (userMessages.size() == 0) {
            mp.noMessagesReceived();
        } else {
            for (String id : userMessages) {
                messages.add(mm.getReceivedMessageToString(id));
            }
        }
        return messages;
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
     * @param jsonObject A JSONObject containing the recipient name and content
     */
    public void sendMessage(JSONObject jsonObject) {
        String recipientName = jsonObject.get("recipientName").toString();
        String content = jsonObject.get("content").toString();
        if (mm.messageCheck(recipientName, username, content)) {
            String messageId = mm.createMessage(recipientName, username, content);
            addMessagesToUser(recipientName, messageId);
            mp.JSONObjectmessageResult(recipientName);
        } else {
            mp.invalidMessageError();
        }
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
