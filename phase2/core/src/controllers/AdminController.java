package controllers;

import presenters.AdminPresenter;
import usecases.*;

import static java.lang.Integer.parseInt;

/**
 * A Controller class representing an AdminController which inherits from UserController.
 *
 * @author Edmund Lo
 *
 */
public class AdminController extends UserController{

    /**
     * Constructor for AdminController object. Uses constructor from UserController.
     *
     * @param em  current session's EventManager class.
     * @param um  current session's UserManager class.
     * @param rm  current session's RoomManager class.
     * @param mm  current session's MessageManager class.
     * @param username current logged in user's username.
     */
    public AdminController(EventManager em, UserManager um, RoomManager rm, MessageManager mm, String username) {
        super(em, um, rm, mm, username);
        AdminPresenter ap = new AdminPresenter();

        boolean inSession = true;
        // Enters a while loop that allows the user to continuously use Organizer and Attendee functions
        while(inSession) { //loop until user logs out
            ap.displayMenu("Admin", username);
            String option = input.nextLine();
            switch(option) {
                case "0":
                    logout();
                    inSession = false;
                    break;
                case "1":
                    signUpMenu();
                    break;
                case "2":
                    cancelMenu();
                    break;
                case "3":
                    messageMenu();
                    break;
                case "4":
                    viewEventsMenu();
                    break;
                case "5":
                    createNewAccountMenu();
                    break;
                default:
                    ap.invalidOptionError();
                    break;
            }
        }
    }

    /**
     * Called when user chooses to display new create account options
     */
    public void createNewAccountMenu() {
        while (true) { //loop until valid input
            ap.createNewPrompt();
            try {
                int option = parseInt(input.nextLine());
                if (option == 0)
                    break;
                else if (option == 1)
                    createAttendeeAccountCmd();
                else if (option == 2)
                    createOrganizerAccountCmd();
                else if (option == 3)
                    createSpeakerAccountCmd();
                else
                    ap.invalidOptionError();
            } catch (NumberFormatException e) {
                ap.invalidOptionError();
            }
        }
    }

    /**
     * Called when user chooses to create a new attendee user account.
     */
    public void createAttendeeAccountCmd() {
        ap.attendeeUsernamePrompt();
        String username = input.nextLine();
        ap.attendeePasswordPrompt();
        String password = input.nextLine();
        createAttendeeAccount(username, password);
    }

    /**
     * Creates a new attendee account after performing necessary checks.
     *
     * @param username String representing new attendee's username.
     * @param password String representing new attendee's password.
     */
    public void createAttendeeAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewAttendee(username, password); //create new attendee
            ap.attendeeCreationResult();
            return; //exit method
        }
        ap.invalidAttendeeNameError();
    }

    /**
     * Called when user chooses to create a new organizer user account.
     */
    public void createOrganizerAccountCmd() {
        ap.organizerUsernamePrompt();
        String username = input.nextLine();
        ap.organizerPasswordPrompt();
        String password = input.nextLine();
        createOrganizerAccount(username, password);
    }

    /**
     * Creates a new organizer account after performing necessary checks.
     *
     * @param username String representing new organizer's username.
     * @param password String representing new organizer's password.
     */
    public void createOrganizerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewOrganizer(username, password); //create new organizer
            ap.organizerCreationResult();
            return; //exit method
        }
        ap.invalidOrganizerNameError();
    }

    /**
     * Called when user chooses to create a new speaker user account.
     */
    public void createSpeakerAccountCmd() {
        ap.speakerUsernamePrompt();
        String username = input.nextLine();
        ap.speakerPasswordPrompt();
        String password = input.nextLine();
        createSpeakerAccount(username, password);
    }

    /**
     * Creates a new speaker account after performing necessary checks.
     *
     * @param username String representing new speaker's username.
     * @param password String representing new speaker's password.
     */
    public void createSpeakerAccount(String username, String password) {
        if (um.checkUniqueUsername(username)) { //ensures the username is unique
            um.createNewSpeaker(username, password); //create new speaker
            ap.speakerCreationResult();
            return; //exit method
        }
        ap.invalidSpeakerNameError();
    }

    public void viewAllUsers() {
        while(true){//presenter displays all users
            ap.listAllUsersLabel();
            ap.listUsers(um.userToString());
            ap.exitlistAllUsersLabel();
            try{
                int option = parseInt(input.nextLine());
                if(option == 0){ //enter 0 to go back
                    break;
                }else{
                    ap.invalidOptionError();
                }
            }catch(NumberFormatException e){
                ap.invalidOptionError();
            }
        }
    }

    public void viewAllVips() {
        while(true){//presenter displays all vips
            ap.listAllVipsLabel();
            ap.listVips(um.getAllVipNames());
            ap.exitlistAllVipsLabel();
            try{
                int option = parseInt(input.nextLine());
                if(option == 0){ //enter 0 to go back
                    break;
                }else{
                    ap.invalidOptionError();
                }
            }catch(NumberFormatException e){
                ap.invalidOptionError();
            }
        }
    }

    public void setAttendeeAsVipCmd() {

    }

    public void setAttendeeAsVip(String username) {
        if (!um.isVip(username)) {
            um.setAttendeeAsVip(username);
            ap.setVipResult();
            return;
        } else if (um.isVip(username)) {
            ap.alreadyVipLabel();
            return;
        }
        ap.invalidAttendeeNameError();
    }

    public void setAttendeeAsNotVipCmd() {

    }

    public void setAttendeeAsNotVip() {
        if (um.isVip(username)) {
            um.setAttendeeAsNotVip(username);
            ap.setNotVipResult();
            return;
        } else if (!um.isVip(username)) {
            ap.alreadyNotVipLabel();
            return;
        }
        ap.invalidAttendeeNameError();
    }

}
