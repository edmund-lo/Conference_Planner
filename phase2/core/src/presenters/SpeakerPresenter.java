package presenters;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Presenter class that outputs speaker attributes that client-users can view
 * Displays menu options
 * Displays list of speaker's events
 * Prompts request to send message to event attendees
 * confirms and rejects success of sending messages to event attendees based on invalid credentials
 */
public class SpeakerPresenter extends UserPresenter {

    private PresenterUtil<String> pu;

    public SpeakerPresenter(){
        pu = new PresenterUtil<>();
    }

    /**
     * Notifies user of their success of sending message to attendees of event name event
     * @param event name of the event
     * @return method description in JSONObject format
     */
    public JSONObject messageEventAttendeesResult(String event) {
        return pu.createJSON("success", "Successfully sent message to attendees of " + event + ".");
    }

    /**
     * Notifies the user that all they're sent messages were sent successfully
     */
    public JSONObject messageEventAttendeesMultiEventsResult(){
        return pu.createJSON("success", "Successfully sent messages to all specified event(s)");
    }

    /**
     * Notifies user that their input for the event number is formatted incorrectly
     * @return method description in JSONObject format
     */
    public JSONObject invalidEventNumberError() {
        return pu.createJSON("warning", "Event number is formatted incorrectly!");
    }

    /**
     * Notifies user that they are not speaking at any events
     * @return method description in JSONObject format
     */
    public JSONObject noSpeakerEventsError() {
        return pu.createJSON("error", "You are not speaking at any events!");
    }

    /**
     * Notifies user that they were unable to message attendees of event
     * @param event name of event
     * @return method description in JSONObject format
     */
    public JSONObject messageEventAttendeesError(String event) {
        return pu.createJSON("error", "Unable to message attendees of " + event + "!");
    }

    /**
     * Notifies the user that they entered no events
     * @return JSONObject properly formatted for the presentation module containing the notice
     */
    public JSONObject noEventsGivenError(){
        return pu.createJSON("warning", "No events entered");
    }

    /**
     * getter for all the speaker events in the system and formats it properly for the presentation module
     * @param data all the speaker events
     * @return JSONObject properly formatted for the presentation module containing the data
     */
    public JSONObject getSpeakerEvents(JSONArray data){
        return pu.createJSON("success", "returning speaker events", data);
    }
}
