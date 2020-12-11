package speaker.impl;

import adapter.ScheduleAdapter;
import common.UserAccountHolder;
import controllers.SpeakerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ScheduleEntry;
import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import speaker.ISpeakerEventsPresenter;
import speaker.ISpeakerEventsView;
import util.DateTimeUtil;
import util.TextResultUtil;
import java.util.List;

public class SpeakerEventsPresenter implements ISpeakerEventsPresenter {
    private final ISpeakerEventsView view;
    private final SpeakerController sc;
    private final ObservableSet<CheckBox> selectedRecipients = FXCollections.observableSet();

    public SpeakerEventsPresenter(ISpeakerEventsView view) {
        this.view = view;
        getUserData();
        this.sc = new SpeakerController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void sendButtonAction(ActionEvent actionEvent) {
        clearResultText();

        JSONObject queryJson = constructMessageJson();
        //JSONObject responseJson = sc.messageEventsAttendeesCmd(queryJson);
        JSONObject responseJson = new JSONObject();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
        if (status.equals("warning") || status.equals("error")) {
            TextResultUtil.getInstance().addPseudoClass(status, this.view.getSubjectField());
            TextResultUtil.getInstance().addPseudoClass(status, this.view.getContentArea());
        }
    }

    @Override
    public List<ScheduleEntry> getAllSpeakerEvents() {
        JSONObject responseJson = sc.getSpeakerEvents();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        return ScheduleAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
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

    @Override
    public void getUserData() {
        UserAccountHolder holder = UserAccountHolder.getInstance();
        UserAccount account = holder.getUserAccount();
        this.view.setSessionUsername(account.getUsername());
        this.view.setSessionUserType(account.getUserType());
    }

    @SuppressWarnings("unchecked")
    private JSONObject constructMessageJson() {
        JSONObject queryJson = new JSONObject();
        JSONArray recipients = new JSONArray();
        for (CheckBox cb : selectedRecipients) {
            if (cb.isSelected())
                recipients.add(cb.getText());
        }
        queryJson.put("sender", this.view.getSender());
        queryJson.put("recipients", recipients);
        queryJson.put("subject", this.view.getSubject());
        queryJson.put("content", this.view.getContent());
        return queryJson;
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getSubjectField());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getContentArea());
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
