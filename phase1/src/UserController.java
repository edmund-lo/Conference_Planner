import java.util.ArrayList;
import java.util.List;

public abstract class UserController {
    private EventManager em;
    private UserManager um;
    private RoomManager rm;
    private MessageManager mm;
    private int userId;

    public UserController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, int userId) {
        this.em = em;
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.userId = userId;
    }

    public boolean signUpEventAttendance(int eventId) {
        return true;
    }

    public boolean cancelEventAttendance(int eventId) {
        return true;
    }

    public String getAttendingEvents() {
        return "";
    }

    public String getAllEvents() {
        return "";
    }

    public List<String> getMessages() {
        return new ArrayList<>();
    }

    public boolean messageSingleAttendee(int recipientId) {
        return true;
    }

    public boolean messageSingleSpeaker(int recipientId) {
        return true;
    }
}
