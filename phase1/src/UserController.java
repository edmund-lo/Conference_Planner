import java.util.ArrayList;
import java.util.List;

public abstract class UserController {
    private EventManager em;
    private UserManager um;
    private RoomManager rm;
    private MessageManager mm;
    private String userId;

    public UserController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        this.em = em;
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.userId = username;
    }

    public boolean signUpEventAttendance(int eventId) {
        return true;
    }

    public boolean cancelEventAttendance(int eventId) {
        return true;
    }

    public List<String> getAttendingEvents() {
        return new ArrayList<>();
    }

    public List<String> getAllEvents() {
        return new ArrayList<>();
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
