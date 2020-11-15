package Presenters;
import UseCases.UserManager;
import Controllers.SpeakerController;
/**
 * Presenter class that outputs speaker attributes that client-users can view
 * Displays menu options
 * Displays list of speaker's events
 * Prompts request to send message to event attendees
 * confirms and rejects success of sending messages to event attendees based on invalid credentials
 */
public class SpeakerPresenter extends UserPresenter {
    /**
     * Outputs the main menu display. Note that it is the same as the user menu with 2 extra options
     */

    public void displayMenu(String speaker, String username){
        super.displayMenu(speaker, username);
        System.out.println(" 5. Message Attendees at Events as Speaker" +
                "\n 6. Show Events you are speaking at");
    }

    /**
     * Outputs label for introducing list of events speaker is speaking at
     */
    public void speakerEventsLabel() {
        System.out.println("Here are a list of events you are speaking at:");
    }

    /**
     * Outputs label for instructions on how to input event numbers for messaging Event attendees
     */
    public void messageEventAttendeesPrompt() {
        System.out.println("Enter the event numbers separated by a comma:");
    }

    /**
     * Outputs confirmation success of sending message to attendees of event name event
     *
     * @param event name of the event
     */
    public void messageEventAttendeesResult(String event) {
        System.out.println("Successfully sent message to attendees of " + event + ".");
    }

    /**
     * Outputs label that indicates error for an invalid event number
     */
    public void invalidEventNumberError() {
        System.out.println("Event number is formatted incorrectly!");
    }

    /**
     * Outputs error label for failing to message all attendees of event name event
     *
     * @param event name of event
     */
    public void messageEventAttendeesError(String event) {
        System.out.println("Unable to message attendees of " + event + "!");
    }
}
