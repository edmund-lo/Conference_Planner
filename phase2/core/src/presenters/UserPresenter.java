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
    private PresenterUtil pu;

    public UserPresenter(){
        PresenterUtil pu = new PresenterUtil();
        this.pu = pu;
    }
    /**
     * list of events iterated and printed
     * @param events passes in list of events to iterate through and print
     */
    public JSONObject listEvents(List<String> events) {
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
    public JSONObject signUpResult(String event) {
        return pu.createJSON("success", "You have signed up for " + event);
    }

    /**
     * Cancel attendance for a given (passed in) event
     * @param event is a string identifier for the event in interest
     */
    public JSONObject cancelResult(String event) {
        return pu.createJSON("success", "You have cancelled " + event);
    }

    /**
     * Prints that there was invalid use of messaging or user format
     */
    public JSONObject invalidMessageError() {return pu.createJSON("warning", "Invalid user or message format!");
    }

    /**
     * Prints to user that an invalid option was entered
     */
    public JSONObject invalidOptionError() {
        return pu.createJSON("warning", "Please enter a valid option!");
    }

    /**
     * Print message that user has already signed-up
     */
    public JSONObject alreadySignedUpError() {
        return pu.createJSON("error", "You are already signed up for an event at this time!");
    }

    /**
     * Prints to user notifying that the event is already at full capacity.
     */
    public JSONObject eventFullCapacityError() {
        return pu.createJSON("error", "The event is already at full capacity!");
    }

    /**
     * Prints statement that user is not signed up for the event
     * @param event event passed in of interest, to determine if user is signed up for it
     */
    public JSONObject notAttendingEventError(String event) {
        return pu.createJSON("error", "You are not signed up for event1 " + event);
    }

    /**
     * Print statement at logout
     */
    public JSONObject logoutMessage() {
        return pu.createJSON("success", "Logging out...");
    }

    public void listAllUsersLabel() {
    }

    public void exitlistAllUsersLabel() {
    }
}
