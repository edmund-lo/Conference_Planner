package Presenters;

import java.util.List;

public class OrganizerPresenter extends UserPresenter {

    public void displayMenu(){
        super.displayMenu();
        System.out.println("4. Create New Options" +
                "\n 5. Organizer Messaging" +
                "\n 6. Schedule a Speaker");
    }

    public void createNewPrompt() {
        System.out.println("Enter 0 to go back, enter 1 to create a new speaker account, or enter 2 to create a new room.");
    }
    public void organizerMessagePrompt() {
        System.out.println("Enter 0 to go back, enter 1 to message all Speakers, or enter 2 to message all Attendees.");
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
    //messageAllSpeakersCmd should interact with this
    public void enterMessagePrompt() {
        System.out.println("Enter your message:");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void eventNumberPrompt() {
        System.out.println("Enter event number for the talk:");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void speakerNamePrompt() {
        System.out.println("Enter the speaker's name:");
    }


    public void speakerCreationResult() {
        System.out.println("Successfully created new speaker account.");
    }
    public void roomCreationResult() {
        System.out.println("Successfully created new room.");
    }
    public void messagedAllSpeakersResult() {
        System.out.println("Successfully sent message to all speakers.");
    }
    public void messagedAllAttendeesResult() {
        System.out.println("Successfully sent message to all attendees.");
    }
    public void scheduleSpeakerResult() {
        System.out.println("This speaker was successfully added to selected event!");
    }

    public void InvalidRoomNameError() {
        System.out.println("Unable to create new room: room's name was not unique.");
    }
    public void InvalidSpeakerNameError() {
        System.out.println("Unable to create new speaker account: speaker's username was not unique.");
    }
    //There is already another speaker speaking at this event.
    public void ExistingSpeakerAtEventError() {
        System.out.println("Another speaker is already speaking at this event.");
    }
    //Entities.Speaker is already speaking at another event
    public void SpeakerUnavailableError() {
        System.out.println("This speaker is already speaking at another event.");
    }
}