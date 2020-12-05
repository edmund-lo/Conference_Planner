package presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Presenter prints attributes that user of program (if organizer) can do/see.
 */
public class OrganizerPresenter extends UserPresenter {

    /**
     * Outputs the label that indicates of a successful creation of a speaker account
     */
    public void speakerCreationResult() {
        return pu.createJSON("Success", "Successfully created new speaker account.");
    }

    /**
     * Outputs the label that indicates of a successful creation of a room
     */
    public void roomCreationResult() {
        return pu.createJSON("Success", "Successfully created new room.");
    }

    /**
     * Outputs the label that indicates the success of messaging all speakers
     */
    public void messagedAllSpeakersResult() {
        return pu.createJSON("Success", "Successfully sent message to all speakers.");
    }

    /**
     * Outputs the label that indicates the success of messaging all attendees
     */
    public void messagedAllAttendeesResult() {
        return pu.createJSON("Success", "Successfully sent message to all attendees.");
    }

    /**
     * Outputs the label that indicates the success of adding a speaker to the selected event
     */
    public void scheduleSpeakerResult() {
        return pu.createJSON("Success", "This speaker was successfully added to selected event!");
    }

    /**
     * Outputs the label that indicates the failure of creating a new room
     */
    public void invalidRoomNameError() {
        return pu.createJSON("Error", "Unable to create new room: room's name was not unique.");
    }

    /**
     * Outputs the label that indicates the failure of creating a new speaker account due to username not being unique
     */
    public void invalidSpeakerNameError() {
        return pu.createJSON("Error", "Unable to create new speaker account: speaker's username was not unique.");
    }

    /**
     * Outputs the label that indicates that a speaker is already speaking at this event
     */
    public void existingSpeakerAtEventError() {
        return pu.createJSON("Error", "Another speaker is already speaking at this event.");
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
        System.out.println("Here's a list of speakers that you can assign to this event:");
        for (String x: speakerNames) {
            System.out.println("(" + count + "): " + x+"\n");
            count++;
        }
    }

    /**
     * Outputs a list of all the rooms
     *
     * @param allRooms A set of strings of all rooms
     */
    public void listRooms(ArrayList<String> allRooms) {
        for (String room : allRooms) {
            System.out.println(room);
        }
    }

    /**
     * Outputs the label that indicates there are no rooms created
     */
    public void noRoomError() {
        System.out.println("There are no rooms in the system. You cannot create an event.");
    }

    /**
     * Outputs a room's schedule
     *
     * @param roomSchedule a string representing the room's schedule
     */
    public void listRoomSchedule(String roomSchedule) {
        System.out.println(roomSchedule);
    }

    /**
     * Outputs a label to prompt the user to enter an event name
     */
    public void eventNamePrompt() {
        System.out.println("Enter the event name:");
    }

    /**
     * Outputs a label indicating a field is empty
     */
    public void emptyFieldError() {
        System.out.println("Try again: cannot leave field empty!");
    }

    /**
     * Outputs a label to prompt the user to enter an event time
     */
    public void eventTimePrompt() {
        System.out.println("Enter the event start time (formatted 'yyyy-MM-dd HH:mm'):");
    }

    /**
     * Outputs a label introducing the list of rooms
     */
    public void roomIntroduceListLabel(ArrayList<String> rooms){
        if (rooms.size() == 0) {
            System.out.println("There are no rooms that fit your event requirements.");
        } else {
            System.out.println("Here are the rooms that fit your event requirements: ");
        }
    }

    /**
     * Outputs a label indicating that the room doesn't exist
     */
    public void roomDoesNotExistLabel(){
        System.out.println("That room doesn't exist!");
    }

    /**
     * Outputs a label indicating event is created successfully
     */
    public void eventCreationResult() {
        System.out.println("Successfully created new event.");
    }

    /**
     * Outputs a label indicating event cannot be created
     */
    public void eventFailedCreationError() {
        System.out.println("Unable to create new event: scheduling conflict occurred.");
    }

    /**
     * Outputs a label indicating speaker cannot be created
     */
    public void noEvents() {
        System.out.println("Unable to schedule speaker: There are no events in the system.");
    }

    /**
     * Outputs a label indicating speaker cannot be created
     */
    public void noSpeakers() {
        System.out.println("Unable to schedule speaker: There are no speakers in the system.");
    }

    /**
     * Outputs a label indicating an invalid date input
     */
    public void invalidDateError() {
        System.out.println("Unable to parse date input!");
    }

    /**
     * Outputs a label prompting the user to input the room capacity
     */
    public void roomCapacityPrompt() {
        System.out.println("Enter the capacity of the room:");
    }

    /**
     * Outputs a label prompting the user to input whether the room has chairs or not.
     */
    public void haveChairsPrompt() {
        System.out.println("Does this room have chairs? Type \"Y\" if yes, or \"N\" if No");
    }

    /**
     * Outputs a label prompting the user to input whether the room has tables or not.
     */
    public void haveTablesPrompt() {
        System.out.println("Does this room have tables? Type \"Y\" if yes, or \"N\" if No");
    }

    /**
     * Outputs a label prompting the user to input whether the room has a projector or not.
     */
    public void haveProjectorPrompt() {
        System.out.println("Does this room have a projector? Type \"Y\" if yes, or \"N\" if No");
    }

    /**
     * Outputs a label prompting the user to input whether the room has a projector or not.
     */
    public void haveSoundSystemPrompt() {
        System.out.println("Does this room have a sound system/speakers installed? Type \"Y\" if yes, " +
                "or \"N\" if No");
    }

    /**
     * Outputs an error message telling the user that their input was incorrect
     */
    public void incorrectInputError() {
        System.out.println("Input error: An input you have entered for chairs, tables, projectors or " +
                "sound system was invalid");
    }

    public void needItemPrompt(String item){
        System.out.println("Enter the room requirements for your event to find matching rooms. If you do not need an " +
                "item for an event but can still run the event with the item in the room, type \"Y\" when prompted. " +
                "This will give you more room options.");
        switch (item) {
            case "chair":
                System.out.println("Does your event require chairs? Type \"Y\" for yes or \"N\" for no.");
            case "table":
                System.out.println("Does your event require tables? Type \"Y\" for yes or \"N\" for no.");
            case "projector":
                System.out.println("Does your event require a projector? Type \"Y\" for yes or \"N\" for no.");
            default:
                System.out.println("Does your event require a sound system/speakers? " +
                        "Type \"Y\" for yes or \"N\" for no.");
        }
    }
}