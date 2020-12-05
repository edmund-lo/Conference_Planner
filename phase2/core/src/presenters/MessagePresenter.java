package presenters;
import java.util.List;
import org.json.simple.*;
/**
 * A Presenter class that prints Message related functionality to the user's screen
 */
public class MessagePresenter {

    private PresenterUtil pu;

    public MessagePresenter(){
        PresenterUtil pu = new PresenterUtil();
        this.pu = pu;
    }

    /**
     * Informs the user that there are no other users in the system that they can message.
     */
    public JSONObject noMessagableUsers(){
        return pu.createJSON("error", "There are no users to message.");
    }

    /**
     * Informs the user that their message was sent to the specified recipient.
     */
    public JSONObject messageResult(String recipient) {
        return pu.createJSON("success", "Message sent to " + recipient);
    }

    /**
     * Informs the user that the user that they tried to message was invalid.
     */
    public JSONObject invalidUserError() {
        return pu.createJSON("warning", "The user you entered was invalid.");
    }

    /**
     * Informs the user that they cannot message an organizer.
     */
    public JSONObject cannotMessageOrganizerError() {
        return pu.createJSON("error", "You cannot message an Organizer!");
    }

    /**
     * Informs the user that have either entered an invalid user to message, or that their message was incorrectly
     * formatted.
     */
    public JSONObject invalidMessageError() {return pu.createJSON("warning", "Invalid user or message format!");
    }
}