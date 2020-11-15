package Presenters;

import java.util.List;

/**
 * Presenter outputs for client type User
 */

public class UserPresenter {
    //type of menu options:
    // go back, sign up for an event with a number
    // cancel an event with a known number
    // message a user, view list of messages
    public void displayMenu() {
        System.out.println("\nSelect Option " +
                "\n 0. Logout" +
                "\n 1. Sign Up for Events" +
                "\n 2. Cancel Attendance from Events" +
                "\n 3. Message Users / View Messages" +
                "\n 4. View all events you're attending");
    }
    //Numbered options during signup
    public void signUpEventPrompt() {
        System.out.println("Enter 0 to go back or enter a number to select an event to sign up for:");
    }
    //prompts user option for cancelling event
    public void cancelEventPrompt() {
        System.out.println("Enter a number to select an event to cancel:" +
                "\n Press 0 to go back.");
    }

    //list of events iterated and printed
    public void listEvents(List<String> events) {
        int count = 1;
        for (String x: events) {
            System.out.println("("+count + "): " + x+"\n");
            count++;
        }
    }
    //list of users iterated and printed
    public void listUsers(List<String> users) {
        for (String x : users) {
            System.out.println(x);
        }
    }
    //Introduces list of events that can be signed up for
    public void signUpEventListLabel() {
        System.out.println("Here are a list of events you can sign up for:");
    }
    //Introduces list of events that can be cancelled
    public void cancelEventListLabel() {
        System.out.println("Here are a list of events you can cancel attendance from:");
    }
    //Introduces list of events that are attending
    public void listAllEventsLabel(){
        System.out.println("Here is a list of all the events you are attending:");
    }
    public void exitlistAllEventsLabel(){
        System.out.println("Press 0 to go back");
    }
    //confirm signup result
    public void signUpResult(String event) {
        System.out.println("You have signed up for event " + event);
    }
    //cancel attendance for passed in event
    public void cancelResult(String event) { System.out.println("You have cancelled your attendance for event " + event);
    }
    //invalid option
    public void invalidOptionError() {
        System.out.println("Please enter a valid option!");
    }
    //signed-up already error
    public void alreadySignedUpError() {
        System.out.println("You are already signed up for an event at this time!");
    }
    //full capacity error print statement
    public void eventFullCapacityError() {
        System.out.println("The event is already at full capacity!");
    }
    //not attending or not signed up for event error (passes in Event event to determine
    public void notAttendingEventError(String event) {
        System.out.println("You are not signed up for event1 " + event);
    }
    //print statement at logout
    public void logoutMessage() {
        System.out.println("Logging out...");
    }
}
