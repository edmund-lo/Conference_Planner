package Presenters;
import java.util.List;

/**
 * Presenter class that prints Message related functionality to the user's screen
 * Displays numbered Message options
 * Prints statement to ask user to specify receiver
 * Displays list of messages
 */
public class MessagePresenter {

    //prompt for messaging options
    public void messageMenuPrompt() {
        System.out.println("Press :" +
                "\n 0. Go back" +
                "\n 1. Message a user" +
                "\n 2. View the messages you sent" +
                "\n 3. View the messages you received");
    }
    //prompt for receiver option
    public void enterReceiverPrompt() {
        System.out.println("Enter the user you wish to message:");
    }
    //prompt for writing message
    public void enterMessagePrompt() {
        System.out.println("Enter the message you wish to send:");
    }
    //Introduces list of users that can be messaged
    public void messageUserListLabel() {
        System.out.println("Here is a list of all users you can message:");
    }
    //Prints that there are no messages
    public void noMessagesLabel() {
        System.out.println("You have no messages.");
    }
    //Tells user there is no one to message
    public void noMessagableUsers(){
        System.out.println("There are no users to message.");
    }
    //prints user that message is sent to
    public void messageResult(String recipient) {
        System.out.println("Message sent to " + recipient);
    }
    //prints error for invalid user
    public void invalidUserError() {
        System.out.println("The user you entered was invalid.");
    }
    //invalid messaging to organizer
    public void cannotMessageOrganizerError() {
        System.out.println("You cannot message an Organizer!");
    }
    // invalid message format
    public void invalidMessageError() {System.out.println("Invalid user or message format!");
    }

    public void showNumMessages(int numMessages, String sentOrReceived) {
        if (numMessages == 1) {
            System.out.println("You have " + numMessages + " " + sentOrReceived + " message.");
        } else {
            System.out.println("You have " + numMessages + " " + sentOrReceived + " messages.");
        }
    }

    //display messages, iterating as a for-loop
    public void listMessages(List<String> messages) {
        for (String x : messages) {
            System.out.println(x);
        }
    }

    public void showSentMessagesLabel() {
        System.out.println("Here are your sent messages: ");
    }

    public void showReceivedMessagesLabel() {
        System.out.println("Here are your received messages: ");
    }
}