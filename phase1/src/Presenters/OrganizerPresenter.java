package Presenters;

/**
 * Presenter prints attributes that user of program (if organizer) can do/see.
 */
public class OrganizerPresenter extends UserPresenter {
    //Displays numbered menu options
    public void displayMenu(){
        super.displayMenu();
        System.out.println(" 4. Create New Options" +
                "\n 5. Organizer Messaging" +
                "\n 6. Schedule a Speaker");
    }
    //creates new numbered prompts based on options
    public void createNewPrompt() {
        System.out.println("Press: " +
                "\n 0. Go back" +
                "\n 1. Create a new room" +
                "\n 2. Create a new speaker account");
    }
    //Numbered prompts for organizer's options
    public void organizerMessagePrompt() {
        System.out.println("Press: " +
                "\n 0. Go back " +
                "\n 1. Message all Speakers " +
                "\n 2. Message all Attendees");
    }
    //interacts with createRoom
    public void roomNamePrompt() {
        System.out.println("Enter room's name:");
    }
    //interacts with createRoom
    public void roomCapacityPrompt() {
        System.out.println("Enter room's capacity:");
    }
    public void speakerUsernamePrompt() {
        System.out.println("Enter speaker's username:");
    }
    public void speakerPasswordPrompt() {
        System.out.println("Enter speaker's password:");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void eventNumberPrompt() {
        System.out.println("Enter event number for the talk:");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void speakerNamePrompt() {
        System.out.println("Enter the speaker's name:");
    }

    //confirms success of new speaker account
    public void speakerCreationResult() {
        System.out.println("Successfully created new speaker account.");
    }
    //confirmation of new room creation success
    public void roomCreationResult() {
        System.out.println("Successfully created new room.");
    }
    //confirmation of messaging all speakers
    public void messagedAllSpeakersResult() {
        System.out.println("Successfully sent message to all speakers.");
    }
    //confirmation of messaging all attendees
    public void messagedAllAttendeesResult() {
        System.out.println("Successfully sent message to all attendees.");
    }
    public void scheduleSpeakerResult() {
        System.out.println("This speaker was successfully added to selected event!");
    }
    //invalid room entry
    public void invalidRoomNameError() {
        System.out.println("Unable to create new room: room's name was not unique.");
    }
    //invalid name for speaker
    public void invalidSpeakerNameError() {
        System.out.println("Unable to create new speaker account: speaker's username was not unique.");
    }
    //There is already another speaker speaking at this event.
    public void existingSpeakerAtEventError() {
        System.out.println("Another speaker is already speaking at this event.");
    }
    //Entities.Speaker is already speaking at another event
    public void speakerUnavailableError() {
        System.out.println("This speaker is already speaking at another event.");
    }
}