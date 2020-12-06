package presenters;
import org.json.simple.*;

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
}
