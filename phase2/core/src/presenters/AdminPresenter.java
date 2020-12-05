package presenters;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class AdminPresenter extends UserPresenter {

    public AdminPresenter() {
        super();
    }

    public JSONObject attendeeCreationResult() {
        return pu.createJSON("success", "Attendee has been created");
    }

    public JSONObject organizerCreationResult() {
        return pu.createJSON("success", "Organizer has been created");
    }

    public JSONObject speakerCreationResult() {
        return pu.createJSON("success", "Speaker has been created");
    }

    public JSONObject usedNameError() {
        return pu.createJSON("failure", "Username already being used");
    }

    public JSONObject listVips(ArrayList<String> allVipNames) {
        return pu.createJSON("success", "VIPS have been listed", "List of VIPS", allVipNames);
    }

    public JSONObject setVipResult() {
        return pu.createJSON("success", "Attendee is now a VIP");
    }

    public JSONObject alreadyVipError() {
        return pu.createJSON("failure", "Attendee is already a VIP");
    }

    public JSONObject setNotVipResult() {
        return pu.createJSON("success", "Attendee is now not a VIP");
    }

    public JSONObject alreadyNotVipError() {
        return pu.createJSON("failure", "Attendee is already not a VIP");
    }

    public JSONObject invalidAttendeeNameError() {
        return pu.createJSON("failure", "Attendee does not exist");
    }
}
