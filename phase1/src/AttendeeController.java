public class AttendeeController extends UserController {
    public AttendeeController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, int userId) {
        super(em, um, rm, mm, userId);
    }
}
