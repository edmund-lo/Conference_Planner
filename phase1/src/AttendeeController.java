import static java.lang.Integer.parseInt;

public class AttendeeController extends UserController {
    public AttendeeController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);

        boolean inSession = true;
        while(inSession) {
            AttendeePresenter.displayMenu();
            int option = parseInt(sc.nextLine());
            switch(option) {
                case 0:
                    logout();
                    inSession = false;
                    break;
                case 1:
                    signUpMenu();
                    break;
                case 2:
                    cancelMenu();
                    break;
                case 3:
                    messageMenu();
                    break;
                default:
                    System.out.println("Please enter a valid option!");
                    break;
            }
        }
    }
}
