package attendee;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import model.User;

import java.util.List;

public interface IFriendsPresenter extends IPresenter {
    void removeFriendButtonAction(ActionEvent actionEvent);
    void removeRequestButtonAction(ActionEvent actionEvent);
    void addFriendButtonAction(ActionEvent actionEvent);
    void acceptButtonAction(ActionEvent actionEvent);
    void declineButtonAction(ActionEvent actionEvent);
    List<User> getUsers(String type);
    List<ScheduleEntry> getCommonEvents(String username);
    void displayUserList(List<User> userList, String type);
    void displayUserDetails(User user, String type);
    void displayCommonEvents(List<ScheduleEntry> commonEvents);
    void setResultText(String resultText, String status, int index);
}
