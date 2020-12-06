package attendee.impl;

import adapter.MessageAdapter;
import attendee.IMessagingPresenter;
import attendee.IMessagingView;
//import controllers.AttendeeController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Message;
import model.ScheduleEntry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.TextResultUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MessagingPresenter implements IMessagingPresenter {
    private IMessagingView view;
//    private AttendeeController ac;
    private Message selectedPrimaryMessage;
    private Message selectedArchivedMessage;
    private Message selectedTrashMessage;

    public MessagingPresenter(IMessagingView view) {
        this.view = view;
        //this.ac = new AttendeeController();
        init();
    }

    @Override
    public void replyButtonAction(ActionEvent actionEvent) {
        clearResultText();


    }

    @Override
    public void newMessageButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void moveToTrashButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void moveToArchivedButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void moveToPrimaryButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public List<Message> getInbox(String type) {
        JSONObject responseJson = new JSONObject();
        switch (type) {
            case "primary":
                //responseJson = ac.getPrimaryMessages();
                break;
            case "archived":
                //responseJson = ac.getArchivedMessages();
                break;
            case "trash":
                //responseJson = ac.getTrashMessages();
                break;
        }
        return MessageAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public void displayInbox(List<Message> messages, String type) {
        switch (type) {
            case "primary":
                //this.view.getPrimaryMembersColumn().setCellValueFactory(new PropertyValueFactory<>());
                this.view.getPrimarySubjectColumn().setCellValueFactory(new PropertyValueFactory<>("subject"));
                break;
            case "archived":
                //responseJson = ac.getArchivedMessages();
                break;
            case "trash":
                //responseJson = ac.getTrashMessages();
                break;
        }
    }

    @Override
    public void displayMessageThread(Message message, String type) {

    }

    @Override
    public void init() {
        this.view.setMoveToArchivedButtonAction(this::moveToArchivedButtonAction);
        this.view.setMoveToTrashButtonAction(this::moveToTrashButtonAction);
        this.view.setMoveToPrimaryFirstButtonAction(this::moveToPrimaryButtonAction);
        this.view.setMoveToPrimarySecondButtonAction(this::moveToPrimaryButtonAction);
        this.view.setReplyButtonAction(this::replyButtonAction);
        this.view.setNewMessageButtonAction(this::newMessageButtonAction);
        refreshAllInboxes();
    }

    private void refreshAllInboxes() {
        List<Message> primaryInbox = getInbox("primary");
        displayInbox(primaryInbox, "primary");
        List<Message> archivedInbox = getInbox("archived");
        displayInbox(archivedInbox, "archived");
        List<Message> trashInbox = getInbox("trash");
        displayInbox(trashInbox, "trash");
    }

    /*private void setScheduleDateTimeCellFactory(TableColumn<Message, String> column) {
        column.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(List<String> dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty)
                    setText(null);
                else
                    this.setText(format(dateTime));
            }
        });
    }*/

    private String getMessageMembers(List<String> recipients) {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String name : recipients) {
            sb.append(prefix);
            sb.append(name);
            prefix = ", ";
        }
        return sb.toString();
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
    }
}
