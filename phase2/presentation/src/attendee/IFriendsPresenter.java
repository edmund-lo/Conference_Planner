package attendee;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import model.User;

import java.util.List;

public interface IFriendsPresenter extends IPresenter {
    void removeFriendButtonAction(ActionEvent actionEvent);
    void addFriendButtonAction(ActionEvent actionEvent);
    List<User> getUsers(String type);
    List<ScheduleEntry> getCommonEvents(String username);
    void displayUserList(List<User> userList, boolean friend);
    void displayUserDetails(User user, boolean friend);
    void displayCommonEvents(List<ScheduleEntry> commonEvents);
    void setResultText(String resultText, String status, int index);
}
