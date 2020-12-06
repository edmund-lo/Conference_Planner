package attendee.impl;

import attendee.IMessagingView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Message;

import java.util.List;

public class MessagingView implements IMessagingView {
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Message> primaryInbox;
    @FXML
    private TableView<Message> archivedInbox;
    @FXML
    private TableView<Message> trashInbox;
    @FXML
    private TableColumn<Message, String> primaryMembersColumn;
    @FXML
    private TableColumn<Message, String> primarySubjectColumn;
    @FXML
    private TableColumn<Message, String> archivedMembersColumn;
    @FXML
    private TableColumn<Message, String> archivedSubjectColumn;
    @FXML
    private TableColumn<Message, String> trashMembersColumn;
    @FXML
    private TableColumn<Message, String> trashSubjectColumn;
    @FXML
    private ScrollPane primaryThreadContainer;
    @FXML
    private ScrollPane archivedThreadContainer;
    @FXML
    private ScrollPane trashThreadContainer;
    @FXML
    private Text primarySender;
    @FXML
    private Text primaryRecipients;
    @FXML
    private Text primarySubject;
    @FXML
    private Text archivedSender;
    @FXML
    private Text archivedRecipients;
    @FXML
    private Text archivedSubject;
    @FXML
    private Text trashSender;
    @FXML
    private Text trashRecipients;
    @FXML
    private Text trashSubject;
    @FXML
    private TextArea content;
    @FXML
    private Text resultText;

    @FXML
    public void executeAddReply(ActionEvent event) {
        if (replyButtonAction != null) replyButtonAction.handle(event);
    }
    @FXML
    public void executeAddNewMessage(ActionEvent event) {
        if (newMessageButtonAction != null) newMessageButtonAction.handle(event);
    }
    @FXML
    public void executeAddArchive(ActionEvent event) {
        if (moveToArchiveButtonAction != null) moveToArchiveButtonAction.handle(event);
    }
    @FXML
    public void executeAddDelete(ActionEvent event) {
        if (moveToTrashButtonAction != null) moveToTrashButtonAction.handle(event);
    }
    @FXML
    public void executeAddPrimaryFirst(ActionEvent event) {
        if (moveToPrimaryFirstButtonAction != null) moveToPrimaryFirstButtonAction.handle(event);
    }
    @FXML
    public void executeAddPrimarySecond(ActionEvent event) {
        if (moveToPrimarySecondButtonAction != null) moveToPrimarySecondButtonAction.handle(event);
    }
    @FXML
    public void initialize() { this.presenter = new MessagingPresenter(this); }

    private MessagingPresenter presenter;
    private EventHandler<ActionEvent> replyButtonAction;
    private EventHandler<ActionEvent> newMessageButtonAction;
    private EventHandler<ActionEvent> moveToArchiveButtonAction;
    private EventHandler<ActionEvent> moveToTrashButtonAction;
    private EventHandler<ActionEvent> moveToPrimaryFirstButtonAction;
    private EventHandler<ActionEvent> moveToPrimarySecondButtonAction;
    private Stage stage;
    private String sessionUsername;
    private String sessionUserType;

    @Override
    public void setPrimarySender(String sender) {
        this.primarySender.setText(sender);
    }

    @Override
    public void setPrimaryRecipientNames(List<String> recipientNames) {
        this.primaryRecipients.setText(recipientListToString(recipientNames));
    }

    @Override
    public void setPrimarySubject(String subject) {
        this.primarySubject.setText(subject);
    }

    @Override
    public void setArchivedSender(String sender) {
        this.archivedSender.setText(sender);
    }

    @Override
    public void setArchivedRecipientNames(List<String> recipientNames) {
        this.archivedRecipients.setText(recipientListToString(recipientNames));
    }

    @Override
    public void setArchivedSubject(String subject) {
        this.archivedSubject.setText(subject);
    }

    @Override
    public void setTrashSender(String sender) {
        this.trashSender.setText(sender);
    }

    @Override
    public void setTrashRecipientNames(List<String> recipientNames) {
        this.trashRecipients.setText(recipientListToString(recipientNames));
    }

    @Override
    public void setTrashSubject(String subject) {
        this.trashSubject.setText(subject);
    }

    @Override
    public String getContent() {
        return this.content.getText();
    }

    @Override
    public void setContent(String content) {
        this.content.setText(content);
    }

    @Override
    public void setResultText(String resultText) {
        this.resultText.setText(resultText);
    }

    @Override
    public TableView<Message> getPrimaryInbox() {
        return this.primaryInbox;
    }

    @Override
    public TableColumn<Message, String> getPrimaryMembersColumn() {
        return this.primaryMembersColumn;
    }

    @Override
    public TableColumn<Message, String> getPrimarySubjectColumn() {
        return this.primarySubjectColumn;
    }

    @Override
    public ScrollPane getPrimaryThreadContainer() {
        return this.primaryThreadContainer;
    }

    @Override
    public TableView<Message> getArchivedInbox() {
        return this.archivedInbox;
    }

    @Override
    public TableColumn<Message, String> getArchivedMembersColumn() {
        return this.archivedMembersColumn;
    }

    @Override
    public TableColumn<Message, String> getArchivedSubjectColumn() {
        return this.archivedSubjectColumn;
    }

    @Override
    public ScrollPane getArchivedThreadContainer() {
        return this.archivedThreadContainer;
    }

    @Override
    public TableView<Message> getTrashInbox() {
        return this.trashInbox;
    }

    @Override
    public TableColumn<Message, String> getTrashMembersColumn() {
        return this.trashMembersColumn;
    }

    @Override
    public TableColumn<Message, String> getTrashSubjectColumn() {
        return this.trashSubjectColumn;
    }

    @Override
    public ScrollPane getTrashThreadContainer() {
        return this.trashThreadContainer;
    }

    @Override
    public TabPane getTabPane() {
        return this.tabPane;
    }

    @Override
    public EventHandler<ActionEvent> getNewMessageButtonAction() {
        return this.newMessageButtonAction;
    }

    @Override
    public void setNewMessageButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.newMessageButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getReplyButtonAction() {
        return this.replyButtonAction;
    }

    @Override
    public void setReplyButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.replyButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMoveToPrimaryFirstButtonAction() {
        return this.moveToPrimaryFirstButtonAction;
    }

    @Override
    public void setMoveToPrimaryFirstButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.moveToPrimaryFirstButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMoveToPrimarySecondButtonAction() {
        return this.moveToPrimarySecondButtonAction;
    }

    @Override
    public void setMoveToPrimarySecondButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.moveToPrimarySecondButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMoveToTrashButtonAction() {
        return this.moveToTrashButtonAction;
    }

    @Override
    public void setMoveToTrashButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.moveToTrashButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMoveToArchivedButtonAction() {
        return this.moveToArchiveButtonAction;
    }

    @Override
    public void setMoveToArchivedButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.moveToArchiveButtonAction = eventHandler;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }

    @Override
    public String getSessionUserType() {
        return this.sessionUserType;
    }

    @Override
    public void setSessionUserType(String userType) {
        this.sessionUserType = userType;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Text getResultTextControl() {
        return this.resultText;
    }

    private String recipientListToString(List<String> recipientNames) {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String name : recipientNames) {
            sb.append(prefix);
            sb.append(name);
            prefix = ", ";
        }
        return sb.toString();
    }
}
