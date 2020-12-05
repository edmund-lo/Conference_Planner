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

    private PresenterUtil pu;

    public SpeakerPresenter(){
        PresenterUtil pu = new PresenterUtil();
        this.pu = pu;
    }

    /**
     * Outputs confirmation success of sending message to attendees of event name event
     *
     * @param event name of the event
     */
    public JSONObject messageEventAttendeesResult(String event) {
        return pu.createJSON("success", "Successfully sent message to attendees of " + event + ".");
    }

    /**
     * Outputs label that indicates error for an invalid event number
     */
    public JSONObject invalidEventNumberError() {
        return pu.createJSON("warning", "Event number is formatted incorrectly!");
    }

    /**
     * Outputs error that the speaker has no events that he/she is speaking at.
     */
    public JSONObject noSpeakerEventsError() {
        return pu.createJSON("error", "You are not speaking at any events!");
    }

    /**
     * Outputs error label for failing to message all attendees of event name event
     *
     * @param event name of event
     */
    public JSONObject messageEventAttendeesError(String event) {
        return pu.createJSON("error", "Unable to message attendees of " + event + "!");
    }
}
