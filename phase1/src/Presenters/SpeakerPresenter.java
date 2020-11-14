package Presenters;

/**
 * Presenter class that outputs speaker attributes that client-users can view
 * Displays menu options
 * Displays list of speaker's events
 * Prompts request to send message to event attendees
 * confirms and rejects success of sending messages to event attendees based on invalid credentials
 */
public class SpeakerPresenter extends UserPresenter {
    //displays numbered menu options
    public void displayMenu(){
        super.displayMenu();
        System.out.println(" 4. Message Attendees at Events as Speaker" +
                "\n 5. Show Events as Speaker");
    }
    //Prints label for speaker's events
    public void speakerEventsLabel() {
        System.out.println("Here are a list of events you are speaking at:");
    }
    //Prints for user to see that event numbers are required
    public void messageEventAttendeesPrompt() {
        System.out.println("Enter the event numbers separated by a comma:");
    }
    //Confirms success of messaging attendees
    public void messageEventAttendeesResult(String event) {
        System.out.println("Successfully sent message to attendees of " + event + ".");
    }
    //If invalid event provided
    public void invalidEventNumber() {
        System.out.println("Event number is formatted incorrectly!");
    }
    //If messages aren't successfully sent
    public void messageEventAttendeesError(String event) {
        System.out.println("Unable to message attendees of " + event + "!");
    }
}
