package presenters;
import org.json.simple.*;

import java.util.ArrayList;

/**
 * A Presenter class that prints Message related functionality to the user's screen
 */
public class MessagePresenter {

    private PresenterUtil<String> pu;

    public MessagePresenter(){
        pu = new PresenterUtil<>();
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
    public JSONObject messageResult(ArrayList recipients) {
        return pu.createJSON("success", "Message sent to " + recipients);
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

    /**
     * Informs the user that their message was sent successfully to name
     * @param names name of the receiver of the message
     */
    public JSONObject messageSent(ArrayList<String> names){
        return pu.createJSON("success", "Message sent to "+names+" successfully!");
    }
}