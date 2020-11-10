public class OrganizerController extends UserController {
    public OrganizerController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
    }

    public boolean createSpeakerAccount(String username, String password, String name) {
        return true;
    }

    public boolean messageAllSpeakers(String message) {
        return true;
    }

    public boolean messageAllAttendees(String message) {
        return true;
    }

    public boolean createRoom(String roomName, int capacity) {
        return true;
    }

    public boolean scheduleSpeaker(String speakerName, int eventId) {
        return true;
    }

    /*public boolean cancelEvent(int eventId) {
        return true;
    }

    public boolean rescheduleEvent(int eventId) {
        return true;
    }*/
}
