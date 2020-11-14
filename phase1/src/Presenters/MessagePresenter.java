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
        System.out.println("Press \"1\" if you would like to display all messages. " +
                "\n Press \"2\" if you would like to send a message. \n If not, press enter to continue. ");
    }
    //This needs work, determine if separate method to send to all Speakers and Attendees
    public void specifyReceiverOfMessage() {
        System.out.println("Enter \"1\" if you would like to send a message to a Entities.Speaker. " +
                "\nPress \"2\" to send a message to an Entities.Attendee." +
                "\nPress \"3\" to send a message to All Speakers. \nPress \"4\" to send a message to All Attendees.");
    }
    //Shows message labels
    public void showMessagesLabel() {
        System.out.println("Here are your messages: ");
    }

    //display messages, iterating as a for-loop
    public void listMessages(List<String> messages) {
        for (String x : messages) {
            System.out.println(x);
        }
    }
}