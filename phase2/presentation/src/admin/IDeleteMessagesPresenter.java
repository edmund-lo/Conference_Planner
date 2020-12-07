package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.MessageThread;

import java.util.List;

public interface IDeleteMessagesPresenter extends IPresenter {
    void deleteButtonAction(ActionEvent actionEvent);
    List<MessageThread> getAllMessages();
    void displayInbox(List<MessageThread> messageThreads);
    void displayMessageThread(MessageThread messageThread);
}
