package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.Message;

import java.util.List;

public interface IDeleteMessagesPresenter extends IPresenter {
    void removeButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<Message> getMessages();
    void displayMessages(List<Message> messages);
    void displayMessageDetails(Message message);
}
