package Presenters;

import java.util.List;
import java.util.Set;

/**
 * Presenter prints attributes that user of program (if organizer) can do/see.
 */
public class OrganizerPresenter extends UserPresenter {

    /**
     * Outputs the display menu for an organizer
     */
    public void displayMenu(String organizer, String username){
        super.displayMenu(organizer, username);
        System.out.println(" 5. Create New Options" +
                "\n 6. Organizer Messaging" +
                "\n 7. Schedule a Speaker");
    }

    /**
     * Outputs the input menu for the "create new" menu
     */
    public void createNewPrompt() {
        System.out.println("Press: " +
                "\n 0. Go back" +
                "\n 1. Create a new room" +
                "\n 2. Create a new speaker account" +
                "\n 3. Create a new event");
    }

    /**
     *Outputs the input menu for the "message" menu
     */
    public void organizerMessagePrompt() {
        System.out.println("Press: " +
                "\n 0. Go back " +
                "\n 1. Message all Speakers " +
                "\n 2. Message all Attendees");
    }

    /**
     * Outputs label that prompts the user to enter the room's name
     */
    public void roomNamePrompt() {
        System.out.println("Enter room's name:");
    }

    /**
     * Outputs label that prompts the user to enter the room's capacity
     */
    public void roomCapacityPrompt() {
        System.out.println("Enter room's capacity:");
    }

    /**
     * Outputs label that prompts the user to enter the speaker's username
     */
    public void speakerUsernamePrompt() {
        System.out.println("Enter speaker's username:");
    }

    /**
     * Outputs the label that prompts the user to enter the speaker's password
     */
    public void speakerPasswordPrompt() {
        System.out.println("Enter speaker's password:");
    }

    /**
     * Outputs the label that introduces the list of all events
     */
    public void speakerListAllEventsPrompt(){
        System.out.println("Here is a list of all the events:");
    }

    /**
     * Outputs the label that prompts the user to enter the number of the event they wish to add a speaker to
     */
    public void eventNumberPrompt() {
        System.out.println("Enter the number of what event you wish to add a speaker to:");
    }

    /**
     * Outputs the label that prompts the user to enter the number of which speaker they wish to assign
     */
    public void speakerNamePrompt() {
        System.out.println("Enter the number of what speaker you wish to assign:");
    }

    /**
     * Outputs the label that indicates of a successful creation of a speaker account
     */
    public void speakerCreationResult() {
        System.out.println("Successfully created new speaker account.");
    }

    /**
     * Outputs the label that indicates of a successful creation of a room
     */
    public void roomCreationResult() {
        System.out.println("Successfully created new room.");
    }

    /**
     * Outputs the label that indicates the success of messaging all speakers
     */
    public void messagedAllSpeakersResult() {
        System.out.println("Successfully sent message to all speakers.");
    }

    /**
     * Outputs the label that indicates the success of messaging all attendees
     */
    public void messagedAllAttendeesResult() {
        System.out.println("Successfully sent message to all attendees.");
    }

    /**
     * Outputs the label that indicates the success of adding a speaker to the selected event
     */
    public void scheduleSpeakerResult() {
        System.out.println("This speaker was successfully added to selected event!");
    }

    /**
     * Outputs the label that indicates the failure of creating a new room
     */
    public void invalidRoomNameError() {
        System.out.println("Unable to create new room: room's name was not unique.");
    }

    /**
     * Outputs the label that indicates the failure of creating a new speaker account due to username not being unique
     */
    public void invalidSpeakerNameError() {
        System.out.println("Unable to create new speaker account: speaker's username was not unique.");
    }

    /**
     * Outputs the label that indicates that a speaker is already speaking at this event
     */
    public void existingSpeakerAtEventError() {
        System.out.println("Another speaker is already speaking at this event.");
    }

    /**
     * Outputs the label that indicates the a speaker is already speaking at another event
     */
    public void speakerUnavailableError() {
        System.out.println("This speaker is already speaking at another event.");
    }

    /**
     * Outputs a list of all speaker names in numbered order
     *
     * @param speakerNames the list of all speaker names
     */
    public void listSpeakers(List<String> speakerNames){
        int count = 1;
        for (String x: speakerNames) {
            System.out.println("("+count + "): " + x+"\n");
            count++;
        }
    }

    public void listRooms(Set<String> allRooms) {
        for (String room : allRooms) {
            System.out.println(room);
        }
    }

    public void noRoomError() {
        System.out.println("There are no rooms in the system. You cannot create an event.");
    }

    public void listRoomSchedule(String roomSchedule) {
        System.out.println(roomSchedule);
    }

    public void eventNamePrompt() {
        System.out.println("Enter the event name:");
    }

    public void emptyFieldError() {
        System.out.println("Try again: cannot leave field empty!");
    }

    public void eventTimePrompt() {
        System.out.println("Enter the event start time (formatted 'yyyy-MM-dd HH:mm'):");
    }

    public void eventCreationResult() {
        System.out.println("Successfully created new event.");
    }

    public void eventFailedCreationResult() {
        System.out.println("Unable to create new event: scheduling conflict occurred.");
    }

    public void invalidDateError() {
        System.out.println("Unable to parse date input!");
    }
}