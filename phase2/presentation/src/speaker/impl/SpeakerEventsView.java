package speaker.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ScheduleEntry;
import speaker.ISpeakerEventsView;
import util.DateTimeUtil;

import java.time.Duration;
import java.time.LocalDateTime;

public class SpeakerEventsView implements ISpeakerEventsView {
    @FXML
    private TableView<ScheduleEntry> eventTable;
    @FXML
    private TableColumn<ScheduleEntry, String> eventNameColumn;
    @FXML
    private TableColumn<ScheduleEntry, String> roomNameColumn;
    @FXML
    private TableColumn<ScheduleEntry, LocalDateTime> eventStartColumn;
    @FXML
    private TableColumn<ScheduleEntry, LocalDateTime> eventEndColumn;
    @FXML
    private Text summaryEventName;
    @FXML
    private Text summaryCapacity;
    @FXML
    private Text summaryRoomName;
    @FXML
    private Text summaryStart;
    @FXML
    private Text summaryEnd;
    @FXML
    private Text summaryDuration;
    @FXML
    private Text summaryAmenities;
    @FXML
    private Text summarySpeakers;
    @FXML
    private Text summaryAttendees;
    @FXML
    private Text resultText;
    @FXML
    private TextField sender;
    @FXML
    private FlowPane recipients;
    @FXML
    private TextArea content;

    @FXML
    public void executeAddSend(ActionEvent event) {
        if (sendButtonAction != null) sendButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new SpeakerEventsPresenter(this);
    }

    private SpeakerEventsPresenter presenter;
    private EventHandler<ActionEvent> sendButtonAction;
    private Stage stage;
    private String sessionUsername;
    private String sessionUserType;

    @Override
    public TableView<ScheduleEntry> getEventsTable() {
        return this.eventTable;
    }

    @Override
    public TableColumn<ScheduleEntry, String> getEventNameColumn() {
        return this.eventNameColumn;
    }

    @Override
    public TableColumn<ScheduleEntry, String> getRoomNameColumn() {
        return this.roomNameColumn;
    }

    @Override
    public TableColumn<ScheduleEntry, LocalDateTime> getEventStartColumn() {
        return this.eventStartColumn;
    }

    @Override
    public TableColumn<ScheduleEntry, LocalDateTime> getEventEndColumn() {
        return this.eventEndColumn;
    }

    @Override
    public FlowPane getRecipientFlowPane() {
        return this.recipients;
    }

    @Override
    public void setSummaryEventName(String eventName) {
        this.summaryEventName.setText(eventName);
    }

    @Override
    public void setSummaryCapacity(int capacity) {
        this.summaryCapacity.setText("" + capacity);
    }

    @Override
    public void setSummaryRoomName(String roomName) {
        this.summaryRoomName.setText(roomName);
    }

    @Override
    public void setSummaryStart(LocalDateTime start) {
        this.summaryStart.setText(DateTimeUtil.getInstance().format(start));
    }

    @Override
    public void setSummaryEnd(LocalDateTime end) {
        this.summaryEnd.setText(DateTimeUtil.getInstance().format(end));
    }

    @Override
    public void setSummaryDuration(Duration duration) {
        this.summaryDuration.setText(DateTimeUtil.getInstance().formatDuration(duration));
    }

    @Override
    public void setSummaryAmenities(String amenities) {
        this.summaryAmenities.setText(amenities);
    }

    @Override
    public void setSummaryAttendees(String attendees) {
        this.summaryAttendees.setText(attendees);
    }

    @Override
    public void setSummarySpeakers(String speakers) {
        String otherSpeakers = speakers.replace(getSessionUsername() + ",", "");
        if (otherSpeakers.equals(getSessionUsername())) otherSpeakers = "";
        this.summarySpeakers.setText(otherSpeakers);
    }

    @Override
    public String getSender() {
        return this.sender.getText();
    }

    @Override
    public void setSender(String sender) {
        this.sender.setText(sender);
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
    public void setResultMsg(String resultMsg) {
        this.resultText.setText(resultMsg);
    }

    @Override
    public EventHandler<ActionEvent> getSendButtonAction() {
        return this.sendButtonAction;
    }

    @Override
    public void setSendButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.sendButtonAction = eventHandler;
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
}
