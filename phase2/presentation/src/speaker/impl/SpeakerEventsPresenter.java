package speaker.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
//import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ScheduleEntry;
import speaker.ISpeakerEventsPresenter;
import speaker.ISpeakerEventsView;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class SpeakerEventsPresenter implements ISpeakerEventsPresenter {
    private ISpeakerEventsView view;
    private final ObservableSet<CheckBox> selectedRecipients = FXCollections.observableSet();
    //private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public SpeakerEventsPresenter(ISpeakerEventsView view) {
        this.view = view;
        init();
    }

    @Override
    public void sendButtonAction(ActionEvent actionEvent) {
        clearResult();

        if (selectedRecipients.isEmpty())
            setResult("You have not selected any recipients!");
        else {
            List<String> recipients = new ArrayList<>();
            for (CheckBox cb : selectedRecipients) {
                if (cb.isSelected())
                    recipients.add(cb.getText());
            }
            //call sc.sendEventMessage
        }
    }

    @Override
    public void setResult(String result) {
        this.view.setResultMsg(result);
    }

    @Override
    public List<ScheduleEntry> getAllSpeakerEvents() {
        //List<String[]> resultJson = sc.getSpeakerEvents method
        //List<ScheduleEntry> speakerEvents = ScheduleAdapter.adapt(resultJson);
        List<ScheduleEntry> speakerEvents = new ArrayList<>();
        return speakerEvents;
    }

    @Override
    public void displaySpeakerEvents(List<ScheduleEntry> speakerSchedule) {
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(this.view.getEventStartColumn());
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(this.view.getEventEndColumn());
        this.view.getEventNameColumn().setCellValueFactory(new PropertyValueFactory<>("eventName"));
        this.view.getRoomNameColumn().setCellValueFactory(new PropertyValueFactory<>("roomName"));
        this.view.getEventStartColumn().setCellValueFactory(new PropertyValueFactory<>("start"));
        this.view.getEventEndColumn().setCellValueFactory(new PropertyValueFactory<>("end"));

        ObservableList<ScheduleEntry> observableSchedule = FXCollections.observableArrayList(speakerSchedule);
        this.view.getEventsTable().setItems(observableSchedule);
        this.view.getEventsTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleSelect(newValue));
    }

    @Override
    public void displayEventDetails(ScheduleEntry event) {
        this.view.setSummaryEventName(event.getEventName());
        this.view.setSummaryRoomName(event.getEventName());
        this.view.setSummaryAttendees(event.getEventName());
        this.view.setSummaryAmenities(event.getEventName());
        this.view.setSummaryDuration(event.getDuration());
        this.view.setSummaryStart(event.getStart());
        this.view.setSummaryEnd(event.getEnd());
        this.view.setSummarySpeakers(event.getEventName());
        this.view.setSummaryCapacity(event.getCapacity());
    }

    @Override
    public void displayRecipients(ScheduleEntry event) {
        this.view.getRecipientFlowPane().getChildren().clear();

        if (!event.getAttendees().equals("")) {
            String[] attendees = event.getAttendees().split(", ");
            for (String attendee : attendees) {
                CheckBox cb = new CheckBox(attendee);
                cb.setSelected(true);
                configureCheckBox(cb);
                this.view.getRecipientFlowPane().getChildren().add(cb);
            }
        } else {
            Text noAttendeesText = new Text("This event has no attendees yet");
            this.view.getRecipientFlowPane().getChildren().add(noAttendeesText);
        }
    }

    @Override
    public void init() {
        this.view.setSendButtonAction(this::sendButtonAction);
        this.view.setSender(this.view.getSessionUsername());
        List<ScheduleEntry> speakerEvents = getAllSpeakerEvents();
        displaySpeakerEvents(speakerEvents);
    }

    private void clearResult() {
        this.view.setResultMsg("");
    }

    private void handleSelect(ScheduleEntry event) {
        displayEventDetails(event);
        displayRecipients(event);
    }

    private void configureCheckBox(CheckBox checkBox) {
        if (checkBox.isSelected())
            selectedRecipients.add(checkBox);

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected)
                selectedRecipients.add(checkBox);
            else
                selectedRecipients.remove(checkBox);
        });
    }
}
