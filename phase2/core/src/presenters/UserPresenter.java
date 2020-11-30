package presenters;

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

    /**
     * Displays menu options for a logged-in user based on their capabilities.
     * @param s string to specify type of user at sign-in period
     * @param username the username of the given user
     */
    public void displayMenu(String s, String username) {
        System.out.println("Hello, " + username + " (" + s + ")!\nSelect Option " +
                "\n 0. Logout" +
                "\n 1. Sign Up for Events" +
                "\n 2. Cancel Attendance from Events" +
                "\n 3. Message Users / View Messages" +
                "\n 4. View all events you're attending");
    }

    /**
     * Outputs prompt for signup for an event
     */
    //Numbered options during signup
    public void signUpEventPrompt() {
        System.out.println("Enter 0 to go back or enter a number to select an event to sign up for:");
    }

    /**
     * Outputs prompt for cancelling event
     */
    //prompts user option for cancelling event
    public void cancelEventPrompt() {
        System.out.println("Enter a number to select an event to cancel:" +
                "\n Press 0 to go back.");
    }

    /**
     * Introduces list of events that can be signed up for
     */
    public void signUpEventListLabel() {
        System.out.println("Here are a list of events you can sign up for:");
    }

    /**
     * Introduces list of events that are attending
     */
    public void listAllEventsLabel(){
        System.out.println("Here is a list of all the events you are attending:");
    }

    /**
     * Prints label to exit back to previous menu option
     */
    public void exitlistAllEventsLabel(){
        System.out.println("Enter 0 to go back");
    }

    /**
     * Introduces list of events that can be cancelled
     */
    public void cancelEventListLabel() {
        System.out.println("Here are a list of events you can cancel attendance from:");
    }



    /**
     * list of events iterated and printed
     * @param events passes in list of events to iterate through and print
     */
    public void listEvents(List<String> events) {
        int count = 1;
        for (String x: events) {
            System.out.println("("+count + "): " + x+"\n");
            count++;
        }
    }

    /**
     * list of users iterated and printed
     * @param users list of users iterated through and printed for the user.
     */
    public void listUsers(List<String> users) {
        for (String x : users) {
            System.out.println(x);
        }
    }

    /**
     * confirm signup result
     * @param event prints event name/identifier
     */
    public void signUpResult(String event) {
        System.out.println("You have signed up for event " + event);
    }

    /**
     * Cancel attendance for a given (passed in) event
     * @param event is a string identifier for the event in interest
     */
    public void cancelResult(String event) {
        System.out.println("You have cancelled your attendance for event " + event);
    }

    /**
     * Prints that there was invalid use of messaging or user format
     */
    public void invalidMessageError() {System.out.println("Invalid user or message format!");
    }

    /**
     * Prints to user that an invalid option was entered
     */
    public void invalidOptionError() {
        System.out.println("Please enter a valid option!");
    }

    /**
     * Print message that user has already signed-up
     */
    public void alreadySignedUpError() {
        System.out.println("You are already signed up for an event at this time!");
    }

    /**
     * Prints to user notifying that the event is already at full capacity.
     */
    public void eventFullCapacityError() {
        System.out.println("The event is already at full capacity!");
    }

    /**
     * Prints statement that user is not signed up for the event
     * @param event event passed in of interest, to determine if user is signed up for it
     */
    public void notAttendingEventError(String event) {
        System.out.println("You are not signed up for event1 " + event);
    }

    /**
     * Print statement at logout
     */
    public void logoutMessage() {
        System.out.println("Logging out...");
    }
}
