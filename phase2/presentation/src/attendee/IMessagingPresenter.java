package attendee;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.Message;

import java.util.List;

public interface IMessagingPresenter extends IPresenter {
    void sendButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<Message> getMessages();
    void displayMessages(List<Message> messages);
    void displayMessageDetails(Message message);
}
