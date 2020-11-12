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

    }

    /**
     *Called when user signs up for an event.
     * @param  eventId id of the event user is signing up for.
     *
     */
    public boolean signUpEventAttendance(String eventId) {
        if(!um.canSignUp(username, eventId, em.getEventById(eventId).getStartTime(), em.getEventById(eventId).getEndTime())){
            System.out.println("You are already signed up for an event at this time.");
            return false;
        }else if(!em.canAddUserToEvent(eventId,username)){
            System.out.println("The event is already at full capacity");
            return false;
        }else{
            em.addUserToEvent(eventId,username);
            um.signUp(username, eventId, em.getEventById(eventId).getStartTime(), em.getEventById(eventId).getEndTime());

            System.out.println("You have signed up for " + em.getEventById(eventId).toString());
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
            um.cancel(eventId, username);
            System.out.println("You have cancelled you attendance for " + em.getEventById(eventId).toString());
            return true;
        }
        System.out.println("You are not signed up for " + em.getEventById(eventId).toString());
        return false;
    }

    /**
     *Returns list of events the user is attending
     *
     *@return list of events the user is attending in a string format
     */

    public List<String> getAttendingEvents() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventDesc.add(em.getEventById(eventId).toString());

        return eventDesc;
    }

    /**
     *Returns list of all events in the conference
     *
     *@return list of all events in the conference in a string format
     */
    public List<String> getAllEvents(){
        HashMap<String, Event> events = em.getAllEvents();
        ArrayList<String> eventDesc = new ArrayList<>();
        for (Event event : events.values())
            eventDesc.add(event.toString());

        return eventDesc;
    }

    /**
     *Calls the user manager to add a messageId to a user's list
     *
     *@param  messageId id of the message user is adding.
     *@param  username username of the user the message is for.
     */
    protected void addMessagesToUser(String username, String messageId) {
        um.addMessageToUser(username, messageId);
        um.addMessageToUser(this.username, messageId);
    }

    /**
     *Returns list of all messages the user recieved
     *
     *@return list of all messages the user recieved in string format
     */
    public List<String> getMessages() {
        HashMap<String, Message> messages = mm.getAllMessages();
        ArrayList<String> messageDesc = new ArrayList<>();
        for (Message message: messages.values())
            messageDesc.add(message.toString());

        return messageDesc;
    }

    /**
     *Sends a message to an attendee.
     *
     *@param  recipientName username of the Attendee the message is for.
     *@param  content the contents of the message being sent.
     *
     *@return returns true if the message was sent successfully.
     */
    public boolean messageSingleAttendee(String recipientName, String content) {
        String messageId = mm.sendToAttendee(recipientName, username, content);
        addMessagesToUser(username, messageId);

        System.out.println("Message sent to " + recipientName);
        return true;
    }

    /**
     *Sends a message to a speaker.
     *
     *@param  recipientName username of the Speaker the message is for.
     *@param  content the contents of the message being sent.
     *
     *@return returns true if the message was sent successfully.
     */
    public boolean messageSingleSpeaker(String recipientName, String content) {
        String messageId = mm.sendToSpeaker(recipientName, username, content);
        addMessagesToUser(username, messageId);

        System.out.println("Message sent to " + recipientName);
        return true;
    }

    /**
     *logs the user out of the program
     *
     *@return returns the current usercontroller class.
     */
    public UserController logout() {
        System.out.println("Logging out...");
        return this;
    }
}
