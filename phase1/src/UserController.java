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

    public List<String> getMessages() {
        return new ArrayList<>();
    }

    public boolean messageSingleAttendee(String recipientName, String content) {
        return true;
    }

    public boolean messageSingleSpeaker(String recipientName, String content) {
        return true;
    }

    public boolean logout() {
        return true;
    }
}
