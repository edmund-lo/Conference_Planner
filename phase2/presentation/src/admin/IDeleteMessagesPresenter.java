package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.MessageThread;

import java.util.List;

public interface IDeleteMessagesPresenter extends IPresenter {
    void removeButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<MessageThread> getMessages();
    void displayMessages(List<MessageThread> messageThreads);
    void displayMessageDetails(MessageThread messageThread);
}
