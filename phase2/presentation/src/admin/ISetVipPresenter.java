package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.User;
import java.util.List;

public interface ISetVipPresenter extends IPresenter {
    void changeVipButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<User> getAttendeeUsers();
    void displayUsers(List<User> attendees);
    void displayUserDetails(User attendee);
}
