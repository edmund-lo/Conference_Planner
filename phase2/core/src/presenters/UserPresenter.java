package presenters;

import org.json.simple.JSONObject;

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
    public PresenterUtil<String> pu;

    public UserPresenter(){
        pu = new PresenterUtil<>();
    }

    /**
     * Notifies the user that they have successfully signed up for event
     * @param event prints event name/identifier
     * @return method description in JSONObject format
     */
    public JSONObject signUpResult(String event) {
        return pu.createJSON("success", "You have signed up for " + event);
    }

    /**
     * Notifies user that they have successfully cancelled their sign up to event
     * @param event is a string identifier for the event in interest
     * @return method description in JSONObject format
     */
    public JSONObject cancelResult(String event) {
        return pu.createJSON("success", "You have cancelled " + event);
    }

    /**
     * Notifies user that their input for message is invalid
     * @return method description in JSONObject format
     */
    public JSONObject invalidMessageError() {return pu.createJSON("warning", "Invalid user or message format!");
    }

    /**
     * Notifies user that they must select a valid option
     * @return method description in JSONObject format
     */
    public JSONObject invalidOptionError() {
        return pu.createJSON("warning", "Please enter a valid option!");
    }

    /**
     * Notifies the user that they have already signed up for an event at the specified time
     * @return the method description in JSONObject format
     */
    public JSONObject alreadySignedUpError() {
        return pu.createJSON("error", "You are already signed up for an event at this time!");
    }

    /**
     * Notifies user that the event is already at full capacity.
     * @return the method description in JSONObject
     */
    public JSONObject eventFullCapacityError() {
        return pu.createJSON("error", "The event is already at full capacity!");
    }

    /**
     * Notifies user that they are not signed up for this event
     * @param event event passed in of interest, to determine if user is signed up for it
     * @return the method description in JSONObject format
     */
    public JSONObject notAttendingEventError(String event) {
        return pu.createJSON("error", "You are not signed up for event1 " + event);
    }

    /**
     * Notifies user that logout was successful
     * @return the method description in JSONObject format
     */
    public JSONObject logoutMessage() {
        return pu.createJSON("success", "Logging out...");
    }

    /**
     * Notifies the user that they successfully added friend to their friends list
     * @param username username of the friend added
     * @return the method description in JSONObject format
     */
    public JSONObject friendAdded(String username){
        return pu.createJSON("success", username + " added as friend");
    }

    /**
     *  Notifies the user that they successfully removed friend from their friends list
     * @param username username of the friends removed
     * @return the method description in JSONObject format
     */
    public JSONObject friendRemoved(String username){
        return pu.createJSON("success", username + " removed as friend");
    }

    /**
     * Notifies the user that they're friend request was received
     * @param username username of the receiver of the friend request
     * @return the method description in JSONObject format
     */
    public JSONObject friendRequestSent(String username){
        return pu.createJSON("success", username + " received friend request");
    }

    /**
     * Notifies the user that they already sent a friends request to the specified user
     * @param username username of the user who received the friends request
     * @return the method description in JSONObject format
     */
    public JSONObject cantAddFriend(String username){
        return pu.createJSON("error", username + " already friend or already requested");
    }

    /**
     * Notifies the user that their friend request ahs been declined
     * @param username username of the receiver of the friends request
     * @return the method description in JSONObject format
     */
    public JSONObject requestDenied(String username){
        return pu.createJSON("success", username + "'s request has been declined");
    }

    /**
     * Notifies the user that their request sent to specified user has been removed
     * @param username the username of the receiver of the request
     * @return the method description in JSONObject format
     */
    public JSONObject requestRemoved(String username){
        return pu.createJSON("success", "request to "+username + " has been removed");
    }

    /**
     * Greeting for the user
     * @param firstName first name
     * @param lastName last name
     * @return the method description in JSONObject format
     */
    public JSONObject greeting(String firstName, String lastName) {
        return pu.createJSON("success", "Welcome " + firstName + " " + lastName);
    }

    /**
     * Notifies the user of their number of unread messages
     * @param numUnread number of unread messages
     * @return the method description in JSONObject format
     */
    public JSONObject numberUnreadMessages(int numUnread) {
        return pu.createJSON("success", "You have " + numUnread + " unread messages");
    }
}
