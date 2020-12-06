package presenters;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * A Presenter class representing an AttendeePresenter which inherits from UserPresenter
 *
 * @author Edmund Lo
 *
 */
public class AttendeePresenter extends UserPresenter{

    public AttendeePresenter() {
        super();
    }

    public JSONObject listVipEvents(ArrayList<String> allVipEvents) {
        return pu.createJSON("success", "VIP Events have been listed", "List of VIP Events", allVipEvents);
    }


    public JSONObject notVipError() {
        return pu.createJSON("failure", "Attendee is not a VIP");
    }

    public JSONObject signUpVipResult(String eventName) {
        return pu.createJSON("success", "You have signed up for the VIP event: " + eventName);
    }


    public JSONObject cancelVipResult(String eventName) {
        return pu.createJSON("success", "You have cancelled your attendance in the VIP event: " + eventName);
    }
}
