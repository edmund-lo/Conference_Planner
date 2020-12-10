package admin.impl;

import adapter.MessageThreadAdapter;
import admin.IDeleteMessagesPresenter;
import admin.IDeleteMessagesView;
import controllers.AdminController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Message;
import model.MessageThread;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;

import java.util.List;

public class DeleteMessagesPresenter implements IDeleteMessagesPresenter {
    private final IDeleteMessagesView view;
    private final AdminController ac;
    private MessageThread selectedMessageThread;

    public DeleteMessagesPresenter(IDeleteMessagesView view) {
        this.view = view;
        this.ac = new AdminController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void deleteButtonAction(ActionEvent actionEvent) {
        JSONObject responseJson = ac.deleteMessageThread(this.selectedMessageThread.getMessageThreadId());
        if (responseJson.get("status").equals("success")) init();
    }

    @Override
    public List<MessageThread> getAllMessages() {
        JSONObject responseJson = ac.getAllMessageThreads();
        return MessageThreadAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public void displayInbox(List<MessageThread> messageThreads) {
        ObservableList<MessageThread> observableInbox = FXCollections.observableArrayList(messageThreads);
        DateTimeUtil.getInstance().setMessageDateTimeCellFactory(this.view.getDeleteMessageTimeColumn());
        this.view.getDeleteMembersColumn().setCellValueFactory(cdf ->
                new SimpleStringProperty(getMessageMembers(cdf.getValue().getRecipientNames())));
        this.view.getDeleteSubjectColumn().setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.view.getDeleteMessageTimeColumn().setCellValueFactory(new PropertyValueFactory<>("messageTime"));
        this.view.getDeleteInbox().setItems(observableInbox);
        this.view.getDeleteInbox().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayMessageThread(newValue));
    }

    @Override
    public void displayMessageThread(MessageThread messageThread) {
        this.selectedMessageThread = messageThread;
        this.view.setDeleteSender(messageThread.getSenderName());
        this.view.setDeleteRecipients(messageThread.getRecipientNames());
        this.view.setDeleteSubject(messageThread.getSubject());
        displayMessageHistory(messageThread, this.view.getDeleteThreadContainer());
    }

    @Override
    public void init() {
        this.view.setDeleteButtonAction(this::deleteButtonAction);
        List<MessageThread> deleteInbox = getAllMessages();
        displayInbox(deleteInbox);
    }

    private void displayMessageHistory(MessageThread messageThread, ScrollPane pane) {
        pane.setContent(null);
        VBox messageTiles = new VBox();
        for (Message message : messageThread.getMessageHistory()) {
            String messageString = message.getSenderName() + ": " + message.getContent() + " (" +
                    DateTimeUtil.getInstance().format(message.getMessageTime()) + ")";
            Text messageText = new Text(messageString);
            HBox tile = new HBox(messageText);
            messageTiles.getChildren().add(tile);
        }
        pane.setContent(messageTiles);
    }

    private String getMessageMembers(List<String> recipients) {
        StringBuilder sb = new StringBuilder();
        for (String name : recipients) {
            sb.append(name);
            sb.append(", ");
        }
        sb.append("me");
        return sb.toString();
    }
}
