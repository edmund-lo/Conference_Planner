package Presenters;
import java.util.List;

/**
 * Presenter class that prints Message related functionality to the user
 * Displays numbered Message options
 * Prints statement to ask user to specify receiver
 * Displays list of messages
 */
public class MessagePresenter {
    public void writeMessage() {
        System.out.println("Write your message here: ");
    }
    //displays menu options
    public void displayMessageOptions() {
        System.out.println("Press:" +
                "\n 1. Display all messages. " +
                "\n 2. Snd a message. " +
                "\n Press enter to continue. ");
    }
    //This needs work, determine if separate method to send to all Speakers and Attendees
    public void specifyReceiverOfMessage() {
        System.out.println("Enter:" +
                "\n 1. Send message to a Speaker. " +
                "\n 2. Send message to an Attendee." +
                "\n 3. Send message to All Speakers. " +
                "\n 4. Send message to All Attendees.");
    }
    //Introduces following messages
    public void showMessagesLabel() {
        System.out.println("Here are your messages: ");
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