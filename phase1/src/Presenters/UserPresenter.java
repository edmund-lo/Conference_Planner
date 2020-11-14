package Presenters;

import java.util.List;

public class UserPresenter {
    //type of menu options:
    // go back, sign up for an event with a number
    // cancel an event with a known number
    // message a user, view list of messages
    public void displayMenu() {
        System.out.println("Select Option " +
                "\n 0. Logout" +
                "\n 1. Sign Up for Events" +
                "\n 2. Cancel Attendance from Events" +
                "\n 3. Message Users / View Messages");
    }

    public void signUpEventListLabel() {
        System.out.println("Here are a list of events you can sign up for:");
    }
    public void cancelEventListLabel() {
        System.out.println("Here are a list of events you can cancel attendance from:");
    }
    public void messageUserListLabel() {
        System.out.println("Here is a list of all users you can message:");
    }

    public void signUpEventPrompt() {
        System.out.println("Enter 0 to go back or enter a number to select an event to sign up for:");
    }
    public void cancelEventPrompt() {
        System.out.println("Enter 0 to go back or enter a number to select an event to cancel:");
    }
    public void messageMenuPrompt() {
        System.out.println("Enter 0 to go back, enter 1 to message a user, or enter 2 to view your messages.");
    }
    public void enterReceiverPrompt() {
        System.out.println("Enter the user you wish to message:");
    }
    public void enterMessagePrompt() {
        System.out.println("Enter the message you wish to send:");
    }

    public void listEvents(List<String> events) {
        int count = 1;
        for (String x: events) {
            System.out.println(count + ": " + x);
        }
    }
    public void listUsers(List<String> users) {
        for (String x : users) {
            System.out.println(x);
        }
    }

    public void signUpResult(String event) {
        System.out.println("You have signed up for " + event);
    }
    public void cancelResult(String event) {
        System.out.println("You have cancelled you attendance for " + event);
    }
    public void messageResult(String recipient) {
        System.out.println("Message sent to " + recipient);
    }

    public void invalidUserError() {
        System.out.println("The user you entered was invalid. Enter a valid option.");
    }
    public void cannotMessageOrganizerError() {
        System.out.println("You cannot message an Organizer!");
    }
    public void invalidOptionError() {
        System.out.println("Please enter a valid option!");
    }
    public void alreadySignedUpError() {
        System.out.println("You are already signed up for an event at this time!");
    }
    public void eventFullCapacityError() {
        System.out.println("The event is already at full capacity!");
    }
    public void notAttendingEventError(String event) {
        System.out.println("You are not signed up for " + event);
    }

    public void logoutMessage() {
        System.out.println("Logging out...");
    }
}
