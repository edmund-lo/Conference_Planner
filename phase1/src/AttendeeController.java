import static java.lang.Integer.parseInt;

public class AttendeeController extends UserController {
    public AttendeeController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);

        boolean inSession = true;
        while(inSession) {
            AttendeePresenter.displayMenu();
            int option = parseInt(sc.nextLine());
            switch(option) {
                case 1:

                    break;
                default:
                    System.out.println("Please enter a valid option!");
                    break;
            }
        }
    }
}
