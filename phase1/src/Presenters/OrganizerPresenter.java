package Presenters;

import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
public abstract class OrganizerPresenter {

    //interacts with messageAllSpeakers and messageAllAttendees
    public void messageWho(){
        System.out.println("Press the corresponding number for who you would like to receive a message. \nType \"1\" to message all Speakers "
                +
                "\"2\" to message all Attendees. \n If not, press enter to continue");
    }
    //messageAllSpeakersCmd should interact with this
    public void writeMessage() {
        System.out.println("Enter your message: ");
    }
    //interacts with createRoom
    public void createRoom() {
        System.out.println("Enter your room name: ");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void eventNumber() {
        System.out.println("Enter event number for the talk: ");
    }
    //interacts with scheduleSpeaker in Controllers.OrganizerController
    public void enterSpeaker() {
        System.out.println("Enter the speaker's name: ");
    }
    //There is already another speaker speaking at this event.
    public void invalidSpeakerAnotherSpeakerAtThisEvent() {
        System.out.println("Another speaker is already speaking at this event.");
    }
    //Entities.Speaker is already speaking at another event
    public void invalidSpeakerSpeakingAtAnotherEvent() {
        System.out.println("This speaker is already speaking at another event.");
    }

    public void successfulSpeakerAddition() {
        System.out.println("This speaker was successfully added to this event!");
    }

    public static void displayMenu(){
        System.out.println("Select Option " +
                "\n 0. Logout" +
                "\n 1. Sign Up for Events" +
                "\n 2. Cancel Events" +
                "\n 3. Entities.Message Menu" +
                "\n 4. Entities.Message All Attendees" +
                "\n 5. Entities.Message All Speakers" +
                "\n 6. Schedule a Entities.Speaker" +
                "\n 7. Create a Entities.Room");
    }

    public void messagedAllSpeakers() {
        System.out.println("Successfully sent message to all speakers.");
    }
    public void messagedAllAttendees() {
        System.out.println("Successfully sent message to all attendees.");
    }
    public void InvalidRoomCreation() {System.out.println("Unable to create new " +
            "room: room name was not unique.");}
    public void successfulRoomCreation() {System.out.println("Successfully created new room.");}
    //Iterates over the list of events and prints them.
    public void viewEvents(List<String> e) {

        System.out.println("Here are the list of events: ");
        for (int i = 0;  i < e.size(); i++) {
            System.out.println(e.get(i));
        }

    }
}