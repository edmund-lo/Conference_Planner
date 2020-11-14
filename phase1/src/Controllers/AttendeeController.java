package Controllers;

import Presenters.*;
import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import static java.lang.Integer.parseInt;

/**
 * A Controller class representing an Controllers.AttendeeController which inherits from Controllers.UserController.
 *
 * @author Echo Li
 * @version 1.0
 *
 */
public class AttendeeController extends UserController {

    /**
     * Constructor for Controllers.AttendeeController object. Uses constructor from Controllers.UserController.
     *
     * @param em  current session's UseCases.EventManager class.
     * @param um  current session's UseCases.UserManager class.
     * @param rm  current session's UseCases.RoomManager class.
     * @param mm  current session's UseCases.MessageManager class.
     * @param username current logged in user's username.
     */
    public AttendeeController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);

        boolean inSession = true;
        while(inSession) {
            AttendeePresenter.displayMenu();
            int option = parseInt(input.nextLine());
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
