package Presenters;

public class SpeakerPresenter extends UserPresenter {
    public void displayMenu(){
        super.displayMenu();
        System.out.println("4. Message Attendees at Events as Speaker" +
                "\n 5. Show Events as Speaker");
    }

    public void speakerEventsLabel() {
        System.out.println("Here are a list of events you are speaking at:");
    }

    public void messageEventAttendeesPrompt() {
        System.out.println("Enter the event numbers separated by a comma:");
    }

    public void messageEventAttendeesResult(String event) {
        System.out.println("Successfully sent message to attendees of " + event + ".");
    }

    public void invalidEventNumber() {
        System.out.println("Event number is formatted incorrectly!");
    }
    public void messageEventAttendeesError(String event) {
        System.out.println("Unable to message attendees of " + event + "!");
    }
}
