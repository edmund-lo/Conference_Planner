package presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.simple.*;

/**
 * Presenter prints attributes that user of program (if organizer) can do/see.
 */
public class OrganizerPresenter extends UserPresenter {

    private PresenterUtil pu;

    public OrganizerPresenter(){
        PresenterUtil pu = new PresenterUtil();
        this.pu = pu;
    }
    /**
     * Outputs the label that indicates of a successful creation of a speaker account
     */
    public JSONObject speakerCreationResult() {
        return pu.createJSON("Success", "Successfully created new speaker account.");
    }

    /**
     * Outputs the label that indicates of a successful creation of a room
     */
    public JSONObject roomCreationResult() {
        return pu.createJSON("Success", "Successfully created new room.");
    }

    /**
     * Outputs the label that indicates the success of messaging all speakers
     */
    public JSONObject messagedAllSpeakersResult() {
        return pu.createJSON("success", "Successfully sent message to all speakers.");
    }

    /**
     * Outputs the label that indicates the success of messaging all attendees
     */
    public JSONObject messagedAllAttendeesResult() {
        return pu.createJSON("success", "Successfully sent message to all attendees.");
    }

    /**
     * Outputs the label that indicates the success of adding a speaker to the selected event
     */
    public JSONObject scheduleSpeakerResult() {
        return pu.createJSON("success", "This speaker was successfully added to selected event!");
    }

    /**
     * Outputs the label that indicates the failure of creating a new room
     */
    public JSONObject invalidRoomNameError() {
        return pu.createJSON("error", "Unable to create new room: room's name was not unique.");
    }

    /**
     * Outputs the label that indicates the failure of creating a new speaker account due to username not being unique
     */
    public JSONObject invalidSpeakerNameError() {
        return pu.createJSON("error", "Unable to create new speaker account: speaker's username was not unique.");
    }

    /**
     * Outputs the label that indicates that a speaker is already speaking at this event
     */
    public JSONObject existingSpeakerAtEventError() {
        return pu.createJSON("error", "Another speaker is already speaking at this event.");
    }

    /**
     * Outputs the label that indicates the a speaker is already speaking at another event
     */
    public JSONObject speakerUnavailableError() {
        System.out.println("This speaker is already speaking at another event.");
    }

    /**
     * Outputs a list of all speaker names in numbered order
     *
     * @param speakerNames the list of all speaker names
     */
    public JSONObject listSpeakers(List<String> speakerNames){
        return pu.createJSON("success", "Speakers have been listed", "List of Speakers", speakerNames)
    }

    /**
     * Outputs a list of all the rooms
     *
     * @param allRooms A set of strings of all rooms
     */
    public JSONObject listRooms(ArrayList<String> allRooms) {
        return pu.createJSON("success", "Rooms have been listed", "List of Rooms", allRooms)
    }

    /**
     * Outputs the label that indicates there are no rooms created
     */
    public JSONObject noRoomError() {
        return pu.createJSON("error", "There are no rooms in the system. You cannot create an event.");
    }

    /**
     * Outputs a label indicating a field is empty
     */
    public JSONObject emptyFieldError() {
        return pu.createJSON("warning", "Try again: cannot leave field empty!");
    }

    /**
     * Outputs a label introducing the list of rooms
     */
    public JSONObject roomIntroduceListLabel(ArrayList<String> rooms){
        if (rooms.size() == 0) {
            return pu.createJSON("error", "There are no rooms that fit your event requirements.");
        } else {
            return pu.createJSON("success", "Here are the rooms that fit your event requirements: ");
        }
    }

    /**
     * Outputs a label indicating that the room doesn't exist
     */
    public JSONObject roomDoesNotExistLabel(){
        return pu.createJSON("error", "That room doesn't exist!");
    }

    /**
     * Outputs a label indicating event is created successfully
     */
    public JSONObject eventCreationResult() {
        return pu.createJSON("success", "Successfully created new event.");
    }

    /**
     * Outputs a label indicating event cannot be created
     */
    public JSONObject eventFailedCreationError() {
        return pu.createJSON("error", "Unable to create new event: scheduling conflict occurred.");
    }

    /**
     * Outputs a label indicating speaker cannot be created
     */
    public JSONObject noEvents() {
        return pu.createJSON("error", "Unable to schedule speaker: There are no events in the system.");
    }

    /**
     * Outputs a label indicating speaker cannot be created
     */
    public JSONObject noSpeakers() {
        return pu.createJSON("error", "Unable to schedule speaker: There are no speakers in the system.");
    }

    /**
     * Outputs a label indicating an invalid date input
     */
    public JSONObject invalidDateError() {
        return pu.createJSON("warning", "Unable to parse date input!");
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
    public JSONObject incorrectInputError() {
        return pu.createJSON("warning", "Input error: An input you have entered for chairs, tables, projectors or " +
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