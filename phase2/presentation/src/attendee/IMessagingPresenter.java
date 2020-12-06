package attendee;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.Message;

import java.util.List;

public interface IMessagingPresenter extends IPresenter {
    void replyButtonAction(ActionEvent actionEvent);
    void newMessageButtonAction(ActionEvent actionEvent);
    void moveToTrashButtonAction(ActionEvent actionEvent);
    void moveToArchivedButtonAction(ActionEvent actionEvent);
    void moveToPrimaryButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<Message> getInbox(String type);
    void displayInbox(List<Message> messages, String type);
    void displayMessageThread(Message message, String type);
}
