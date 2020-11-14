package Controllers;

import Presenters.MessagePresenter;
import Presenters.UserPresenter;
import UseCases.*;

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
    private MessagePresenter mp;

    /**
     * Constructor for Controllers.UserController object.
     *
     * @param em  current session's UseCases.EventManager class.
     * @param um  current session's UseCases.UserManager class.
     * @param rm  current session's UseCases.RoomManager class.
     * @param mm  current session's UseCases.MessageManager class.
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
            up.listEvents(getAllEvents());
            up.signUpEventPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option > em.getAllEventIds().size())
                    up.invalidOptionError();
                else
                    signUpEventAttendance(em.getAllEventIds().get(option - 1));
                    break;
            } catch (NumberFormatException e) {
                up.invalidOptionError();
            }
        }
    }

    /**
     *UI for when user cancels an event they signed up for.
     *
     */
    public void cancelMenu(){
        while(true) {
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
            } catch (NumberFormatException e) {
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
            up.messageMenuPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1){
                    String name;
                    String content;
                    boolean canSend = false;
                    up.messageUserListLabel();
                    up.listUsers(getAllMessageableUsers());
                    up.enterReceiverPrompt();
                    name = input.nextLine();
                    if (um.userExists(name)) {
                        canSend = true;
                    }else {
                        up.invalidUserError();
                    }
                    if (canSend) {
                        up.enterMessagePrompt();
                        content = input.nextLine();
                        if (um.isAttendee(name) || um.isSpeaker(name))
                            sendMessage(name, content);
                        else
                            up.cannotMessageOrganizerError();
                    }
                } else if (option == 2){
                    mp = new MessagePresenter();
                    mp.showMessagesLabel();
                    mp.listMessages(getAllMessages());
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
    public boolean signUpEventAttendance(String eventId) {
        LocalDateTime start = em.getEventStartTime(eventId);
        LocalDateTime end = em.getEventEndTime(eventId);
        if (!um.canSignUp(username, eventId, start, end)) {
            up.alreadySignedUpError();
            return false;
        } else if (!em.canAddUserToEvent(eventId,username)){
            up.eventFullCapacityError();
            return false;
        } else {
            em.addUserToEvent(eventId,username);
            um.signUp(username, eventId, start, end);
            up.signUpResult(em.getEventName(eventId));
            return true;
        }
    }

    /**
     *Called when user cancels an event they signed up for.
     *
     * @param  eventId id of the event user is signing up for.
     *
     */
    public boolean cancelEventAttendance(String eventId) {
        if(em.removeUserFromEvent(eventId, username)) {
            um.cancel(username, eventId);
            up.cancelResult(em.getEventName(eventId));
            return true;
        }
        up.notAttendingEventError(em.getEventName(eventId));
        return false;
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
     *Returns list of all events in the conference
     *
     *@return list of all events in the conference in a string format
     */
    public List<String> getAllEvents(){
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String id : em.getAllEventIds()){
            if(um.canSignUp(username, id, em.getEventStartTime(id), em.getEventEndTime(id))) {
                eventDesc.add(em.getEventDescription(id));
            }
        }

        return eventDesc;
    }

    /**
     *Returns list of all messages the user received
     *
     *@return list of all messages the user received in string format
     */
    public List<String> getAllMessages(){
        List<String> messageStrings = new ArrayList<>();
        List<String> userMessages = um.getUserMessages(username);
        if (userMessages.size() == 0) {
            up.noMessagesLabel();
        } else {
            System.out.println("You have " + userMessages.size() + " messages.");
            for (String id : userMessages) {
                messageStrings.add(mm.getMessageToString(id));
            }
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
        um.addMessageToUser(recipientName, messageId);
        um.addMessageToUser(this.username, messageId);
    }

    /**
     *Sends a message to an attendee.
     *
     *@param  recipientName username of the Entities.Attendee the message is for.
     *@param  content the contents of the message being sent.
     *
     *@return returns true if the message was sent successfully.
     */
    public boolean sendMessage(String recipientName, String content) {
        if (mm.messageCheck(recipientName, username, content)) {
            String messageId = mm.createMessage(recipientName, username, content);
            up.messageResult(recipientName);
            addMessagesToUser(recipientName, messageId);
        } else {
            up.invalidMessageError();
        }

        return true;
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
