import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class UserController {
    protected EventManager em;
    protected UserManager um;
    protected RoomManager rm;
    protected MessageManager mm;
    protected String username;

    public UserController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        this.em = em;
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.username = username;
    }

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

    public boolean cancelEventAttendance(String eventId) {
        if(em.removeUserFromEvent(eventId, username)) {
            um.cancel(eventId, username);
            System.out.println("You have cancelled you attendance for " + em.getEventById(eventId).toString());
            return true;
        }
        System.out.println("You are not signed up for " + em.getEventById(eventId).toString());
        return false;
    }

    public List<String> getAttendingEvents() {
        HashMap<String, LocalDateTime[]> schedule = um.getSchedule(username);
        ArrayList<String> eventDesc = new ArrayList<>();
        for (String eventId : schedule.keySet())
            eventDesc.add(em.getEventById(eventId).toString());

        return eventDesc;
    }

    public List<String> getAllEvents(){
        HashMap<String, Event> events = em.getAllEvents();
        ArrayList<String> eventDesc = new ArrayList<>();
        for (Event event : events.values())
            eventDesc.add(event.toString());

        return eventDesc;
    }

    protected void addMessagesToUser(String username, String messageId) {
        um.addMessageToUser(username, messageId);
        um.addMessageToUser(this.username, messageId);
    }

    public List<String> getMessages() {
        HashMap<String, Message> messages = mm.getAllMessages();
        ArrayList<String> messageDesc = new ArrayList<>();
        for (Message message: messages.values())
            messageDesc.add(message.toString());

        return messageDesc;
    }

    public boolean messageSingleAttendee(String recipientName, String content) {
        Message message = mm.createMessage(username, recipientName, content, LocalDateTime.now());
        String messageId = mm.sendToAttendee(recipientName, username, message);
        addMessagesToUser(username, messageId);

        System.out.println("Message sent to " + recipientName);
        return true;
    }

    public boolean messageSingleSpeaker(String recipientName, String content) {
        Message message = mm.createMessage(username, recipientName, content, LocalDateTime.now());
        String messageId = mm.sendToSpeaker(recipientName, username, message);
        addMessagesToUser(username, messageId);

        System.out.println("Message sent to " + recipientName);
        return true;
    }

    public boolean logout() {
        return true;
    }
}
