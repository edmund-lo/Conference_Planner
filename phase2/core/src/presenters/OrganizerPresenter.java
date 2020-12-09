package presenters;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Presenter prints attributes that user of program (if organizer) can do/see.
 */
public class OrganizerPresenter extends UserPresenter {

    private final PresenterUtil<String> pu;

    public OrganizerPresenter(){
        pu = new PresenterUtil<>();
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
        return pu.createJSON("error", "This speaker is already speaking at another event.");
    }

    /**
     * Outputs a list of all speaker names in numbered order
     *
     * @param speakerNames the list of all speaker names
     */
    public JSONObject listSpeakers(List<String> speakerNames){
        return pu.createJSON("success", "Speakers have been listed", "List of Speakers", speakerNames);
    }

    /**
     * Outputs a list of all the rooms
     *
     * @param allRooms A set of strings of all rooms
     */
    public JSONObject listRooms(List<String> allRooms) {
        return pu.createJSON("success", "Rooms have been listed", "List of Rooms", allRooms);
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
        return pu.createJSON("warning", "The start or end time you entered was invalid.");
    }

    /**
     * Outputs an error message telling the user that their input was incorrect
     */
    public JSONObject incorrectInputError() {
        return pu.createJSON("warning", "Input error: An input you have entered for chairs, tables, projectors or " +
                "sound system was invalid");
    }

    public JSONObject selectionNotValid(){
        return pu.createJSON("error", "This room cannot accommodate the event.");
    }

    public JSONObject invalidCapacityError() {
        return pu.createJSON("warning", "The capacity you entered was invalid.");
    }
    /**
     * Success message for successfully rescheduling an event
     */
    public JSONObject rescheduleSuccess(){
        return pu.createJSON("success", "Event rescheduled successfully!");
    }

    /**
     * Notifies user of their failure to reschedule event in roomName
     * @param roomName the name of the event they wish to reschedule
     * @return method description in JSONObject format
     */
    public JSONObject rescheduleFailure(String roomName){
        return pu.createJSON("error", "Event could not be rescheduled in "+roomName);
    }

    public JSONObject cannotAccommodate() {
        return pu.createJSON("error", "The capacity you entered is greater than the room can " +
                "accommodate.");
    }

    public JSONObject cannotChangeCap(int capacity){
        return pu.createJSON("error", "The event already has " + capacity + " or more people signed up. " +
                "If you would like to decrease the event's capacity, cancel the event and create one.");
    }

    public JSONObject changeCapResult(){
        return pu.createJSON("success", "The event capacity has been changed successfully.");
    }

    public JSONObject cancelResult(){
        return pu.createJSON("success", "The event has been cancelled successfully");
    }

    public JSONObject listRoomSchedule(List<String> eventIDs){
        return pu.createJSON("success", "Room schedule has been listed", "Room Schedule", eventIDs);
    }

    public JSONObject listUsers(List<String> speakerNames){
        return pu.createJSON("success", "Users have been listed", "List of Users", speakerNames);
    }
}
