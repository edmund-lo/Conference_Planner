package Presenters;
import java.util.List;

/**
 * A Presenter class that prints Message related functionality to the user's screen
 */
public class MessagePresenter {
    /**
     * Displays the user's options in the form of a messaging menu.
     */
    public void messageMenuPrompt() {
        System.out.println("Enter :" +
                "\n 0. Go back" +
                "\n 1. Message a user" +
                "\n 2. View the messages you sent" +
                "\n 3. View the messages you received");
    }

    /**
     * Prompts the user to enter the username of the person they would like to send a message to.
     */
    public void enterReceiverPrompt() {
        System.out.println("Enter the user you wish to message:");
    }

    /**
     * Prompts the user to enter a message.
     */
    public void enterMessagePrompt() {
        System.out.println("Enter the message you wish to send:");
    }

    /**
     * Labels the users in the system that can be messaged by the current user.
     */
    public void messageUserListLabel() {
        System.out.println("Here is a list of all users you can message:");
    }

    /**
     * Informs the user that they have no messages.
     */
    public void noMessagesLabel() {
        System.out.println("You have no messages.");
    }

    /**
     * Informs the user that there are no other users in the system that they can message.
     */
    public void noMessagableUsers(){
        System.out.println("There are no users to message.");
    }

    /**
     * Informs the user that their message was sent to the specified recipient.
     */
    public void messageResult(String recipient) {
        System.out.println("Message sent to " + recipient);
    }

    /**
     * Informs the user that the user that they tried to message was invalid.
     */
    public void invalidUserError() {
        System.out.println("The user you entered was invalid.");
    }

    /**
     * Informs the user that they cannot message an organizer.
     */
    public void cannotMessageOrganizerError() {
        System.out.println("You cannot message an Organizer!");
    }

    /**
     * Informs the user that have either entered an invalid user to message, or that their message was incorrectly
     * formatted.
     */
    public void invalidMessageError() {System.out.println("Invalid user or message format!");
    }

    /**
     * Informs the user of how many messages they have.
     */
    public void showNumMessages(int numMessages, String sentOrReceived) {
        if (numMessages == 1) {
            System.out.println("You have " + numMessages + " " + sentOrReceived + " message.");
        } else {
            System.out.println("You have " + numMessages + " " + sentOrReceived + " messages.");
        }
    }

    /**
     * Displays the messages that the user has.
     */
    public void listMessages(List<String> messages) {
        for (String x : messages) {
            System.out.println(x);
        }
    }

    /**
     * Labels the user's sent messages.
     */
    public void showSentMessagesLabel() {
        System.out.println("Here are your sent messages: ");
    }

    /**
     * Labels the user's received messages.
     */
    public void showReceivedMessagesLabel() {
        System.out.println("Here are your received messages: ");
    }
}