import java.util.ArrayList;
import java.util.List;

public class SpeakerController extends UserController {
    public SpeakerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    public boolean messageEventsAttendees(List<Integer> eventIds) {
        return true;
    }

    public boolean messageEventAttendees(int eventId) {
        return true;
    }

    public List<String> getSpeakerEvents() {
        return new ArrayList<>();
    }
}
