package presenters;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter outputs for client type User
 * Displays menu options, prompts to signup or cancel an event
 * Prompts message options
 *
 */

public class UserPresenter {
    //type of menu options:
    // go back, sign up for an event with a number
    // cancel an event with a known number
    // message a user, view list of messages
    public UserPresenter(){
        PresenterUtil pu = new PresenterUtil();
    }
    /**
     * list of events iterated and printed
     * @param events passes in list of events to iterate through and print
     */
    public void listEvents(List<String> events) {
        return pu.createJSON("success", "Events have been listed", "List of Events", events)
    }

    /**
     * list of users iterated and printed
     * @param users list of users iterated through and printed for the user.
     */
    public JSONObject listUsers(List<String> users) {
        return pu.createJSON("success", "Users have been listed", "List of Users", users)
    }

    /**
     * confirm signup result
     * @param event prints event name/identifier
     */
    public void signUpResult(String event) {
        ArrayList<String>() e = new ArrayList<String>();
        e.add(event);
        return pu.createJSON("success", "You have signed up for " + event, "Event", e);
    }

    /**
     * Cancel attendance for a given (passed in) event
     * @param event is a string identifier for the event in interest
     */
    public void cancelResult(String event) {
        ArrayList<String>() e = new ArrayList<String>();
        e.add(event);
        return pu.createJSON("success", "You have cancelled " + event, "Event", e);
    }

    /**
     * Prints that there was invalid use of messaging or user format
     */
    public void invalidMessageError() {return pu.createJSON("Warning", "Invalid user or message format!");
    }

    /**
     * Prints to user that an invalid option was entered
     */
    public void invalidOptionError() {
        return pu.createJSON("Warning", "Please enter a valid option!");
    }

    /**
     * Print message that user has already signed-up
     */
    public void alreadySignedUpError() {
        return pu.createJSON("Error", "You are already signed up for an event at this time!");
    }

    /**
     * Prints to user notifying that the event is already at full capacity.
     */
    public void eventFullCapacityError() {
        return pu.createJSON("Error", "The event is already at full capacity!");
    }

    /**
     * Prints statement that user is not signed up for the event
     * @param event event passed in of interest, to determine if user is signed up for it
     */
    public void notAttendingEventError(String event) {
        return pu.createJSON("Error", "You are not signed up for event1 " + event);
    }

    /**
     * Print statement at logout
     */
    public void logoutMessage() {
        return pu.createJSON("Success", "Logging out...");
    }

    public void listAllUsersLabel() {
    }

    public void exitlistAllUsersLabel() {
    }
}
