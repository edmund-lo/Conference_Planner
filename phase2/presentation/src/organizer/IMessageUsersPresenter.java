package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.User;
import java.util.List;

public interface IMessageUsersPresenter extends IPresenter {
    void sendButtonAction(ActionEvent actionEvent);
    void selectAllAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<User> getAllUsers();
    void displayUserList(List<User> users);
    void updateRecipientList();
}
