package organizer.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import organizer.ICreateEventView;
import util.DateTimePicker;
import util.DateTimeUtil;
import util.NumberTextField;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateEventView implements ICreateEventView {
    @FXML
    private TextField eventName;
    @FXML
    private NumberTextField capacity;
    @FXML
    private ComboBox<String> roomName;
    @FXML
    private CheckBox amenity1;
    @FXML
    private CheckBox amenity2;
    @FXML
    private CheckBox amenity3;
    @FXML
    private DateTimePicker eventStart;
    @FXML
    private DateTimePicker eventEnd;
    @FXML
    private Text errorMsg;
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
    private Text summaryAmenities;
    @FXML
    private Text resultText;
    @FXML
    private TitledPane step1;
    @FXML
    private TitledPane step2;
    @FXML
    private TitledPane step3;
    @FXML
    private VBox tableContainer;

    @FXML
    public void executeAddFindRooms(ActionEvent event) {
        if (findRoomsButtonAction != null) findRoomsButtonAction.handle(event);
    }
    @FXML
    public void executeAddPreviousFirst(ActionEvent event) {
        if (previousFirstButtonAction != null) previousFirstButtonAction.handle(event);
    }
    @FXML
    public void executeAddPreviewRoom(ActionEvent event) {
        if (previewRoomButtonAction != null) previewRoomButtonAction.handle(event);
    }
    @FXML
    public void executeAddEventSummary(ActionEvent event) {
        if (summaryButtonAction != null) summaryButtonAction.handle(event);
    }
    @FXML
    public void executeAddPreviousSecond(ActionEvent event) {
        if (previousSecondButtonAction != null) previousSecondButtonAction.handle(event);
    }
    @FXML
    public void executeAddCreateEvent(ActionEvent event) {
        if (createEventButtonAction != null) createEventButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new CreateEventPresenter(this);
    }

    private CreateEventPresenter presenter;
    private EventHandler<ActionEvent> findRoomsButtonAction;
    private EventHandler<ActionEvent> previousFirstButtonAction;
    private EventHandler<ActionEvent> previewRoomButtonAction;
    private EventHandler<ActionEvent> summaryButtonAction;
    private EventHandler<ActionEvent> previousSecondButtonAction;
    private EventHandler<ActionEvent> createEventButtonAction;
    private Stage stage;
    private String sessionUsername;
    private int sessionUserType;

    @Override
    public String getEventName() {
        return this.eventName.getText();
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName.setText(eventName);
    }

    @Override
    public int getCapacity() {
        return this.capacity.getNumber().intValue();
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity.setNumber(BigDecimal.valueOf(capacity));
    }

    @Override
    public boolean getAmenity(int index) {
        return getAmenityBox(index).isSelected();
    }

    @Override
    public void setAmenity(int index, boolean checked) {
        CheckBox amenity = new CheckBox();

        amenity.setSelected(checked);
    }

    @Override
    public String getRoomName() {
        return this.roomName.getValue();
    }

    @Override
    public void setRoomName(String roomName) {
        this.roomName.setValue(roomName);
    }

    @Override
    public LocalDateTime getStart() {
        return this.eventStart.getDateTimeValue();
    }

    @Override
    public void setStart(LocalDateTime start) {
        this.eventStart.setDateTimeValue(start);
    }

    @Override
    public LocalDateTime getEnd() {
        return this.eventEnd.getDateTimeValue();
    }

    @Override
    public void setEnd(LocalDateTime end) {
        this.eventEnd.setDateTimeValue(end);
    }

    @Override
    public void setErrorMsg(String error) {
        this.errorMsg.setText(error);
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
    public void setSummaryAmenities(String amenities) {
        this.summaryAmenities.setText(amenities);
    }

    @Override
    public void setResultMsg(String result) {
        this.resultText.setText(result);
    }

    @Override
    public ComboBox<String> getRoomComboBox() {
        return this.roomName;
    }

    @Override
    public DateTimePicker getStartPicker() {
        return this.eventStart;
    }

    @Override
    public DateTimePicker getEndPicker() {
        return this.eventEnd;
    }

    @Override
    public CheckBox getAmenityBox(int index) {
        CheckBox amenity = null;
        if (index == 1)
            amenity = this.amenity1;
        else if (index == 2)
            amenity =  this.amenity2;
        else if (index == 3)
            amenity =  this.amenity3;
        return amenity;
    }

    @Override
    public TitledPane getTitledPane(int index) {
        TitledPane pane = null;
        if (index == 1)
            pane = this.step1;
        else if (index == 2)
            pane =  this.step2;
        else if (index == 3)
            pane =  this.step3;
        return pane;
    }

    @Override
    public VBox getTableContainer() {
        return this.tableContainer;
    }

    @Override
    public EventHandler<ActionEvent> getFindRoomsButtonAction() {
        return this.findRoomsButtonAction;
    }

    @Override
    public void setFindRoomsButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.findRoomsButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getPreviousFirstButtonAction() {
        return this.previousFirstButtonAction;
    }

    @Override
    public void setPreviousFirstButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.previousFirstButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getPreviewRoomButtonAction() {
        return previewRoomButtonAction;
    }

    @Override
    public void setPreviewRoomButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.previewRoomButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getSummaryButtonAction() {
        return summaryButtonAction;
    }

    @Override
    public void setSummaryButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.summaryButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getPreviousSecondButtonAction() {
        return this.previousSecondButtonAction;
    }

    @Override
    public void setPreviousSecondButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.previousSecondButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getCreateEventButtonAction() {
        return this.createEventButtonAction;
    }

    @Override
    public void setCreateEventButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createEventButtonAction = eventHandler;
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

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }

    @Override
    public int getSessionUserType() {
        return this.sessionUserType;
    }

    @Override
    public void setSessionUserType(int userType) {
        this.sessionUserType = userType;
    }
}
