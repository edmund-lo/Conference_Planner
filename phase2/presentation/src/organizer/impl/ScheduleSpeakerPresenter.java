package organizer.impl;

import adapter.ScheduleAdapter;
import adapter.UserAdapter;
import controllers.OrganizerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import organizer.IScheduleSpeakerPresenter;
import organizer.IScheduleSpeakerView;
import util.DateTimeUtil;
import util.TextResultUtil;
import java.util.List;

public class ScheduleSpeakerPresenter implements IScheduleSpeakerPresenter {
    private OrganizerController oc;
    private IScheduleSpeakerView view;
    private ScheduleEntry selectedEvent;

    public ScheduleSpeakerPresenter(IScheduleSpeakerView view) {
        this.view = view;
        this.oc = new OrganizerController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        clearResultText();

        String speaker = this.view.getAvailableSpeakerChoiceBox().getValue();
        //JSONObject responseJson = oc.scheduleSpeaker(selectedEvent.getEventId(), speaker);
        JSONObject responseJson = new JSONObject();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        if (responseJson.get("status").equals("success"))
            handleSelect(this.selectedEvent);
    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public List<ScheduleEntry> getAllEvents() {
        //JSONObject responseJson = ac.getAllEvents();
        JSONObject responseJson = new JSONObject();
        return ScheduleAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public void displayEvents(List<ScheduleEntry> schedule) {
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(this.view.getEventStartColumn());
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(this.view.getEventEndColumn());
        this.view.getEventNameColumn().setCellValueFactory(new PropertyValueFactory<>("eventName"));
        this.view.getRoomNameColumn().setCellValueFactory(new PropertyValueFactory<>("roomName"));
        this.view.getEventStartColumn().setCellValueFactory(new PropertyValueFactory<>("start"));
        this.view.getEventEndColumn().setCellValueFactory(new PropertyValueFactory<>("end"));

        ObservableList<ScheduleEntry> observableSchedule = FXCollections.observableArrayList(schedule);
        this.view.getEventsTable().setItems(observableSchedule);
        this.view.getEventsTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleSelect(newValue));
    }

    @Override
    public void displayEventDetails(ScheduleEntry event) {
        this.view.setSummaryEventName(event.getEventName());
        this.view.setSummaryRoomName(event.getEventName());
        this.view.setSummaryAmenities(event.getEventName());
        this.view.setSummaryDuration(event.getDuration());
        this.view.setSummaryStart(event.getStart());
        this.view.setSummaryEnd(event.getEnd());
        this.view.setSummarySpeakers(event.getEventName());
        this.view.setSummaryCapacity(event.getCapacity());
    }

    @Override
    public void displayAvailableSpeakers(ScheduleEntry event) {
        clearResultText();
        //JSONObject responseJson = oc.getAvailableSpeakers(event.getStart(), event.getEnd());
        JSONObject responseJson = new JSONObject();
        List<User> speakerList = UserAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        this.view.getAvailableSpeakerChoiceBox().getItems().clear();
        for (User speaker : speakerList)
            this.view.getAvailableSpeakerChoiceBox().getItems().add(speaker.getUsername());
    }

    @Override
    public void init() {
        this.view.setScheduleSpeakerButtonAction(this::scheduleSpeakerButtonAction);
        List<ScheduleEntry> schedule = getAllEvents();
        displayEvents(schedule);
    }

    private void handleSelect(ScheduleEntry event) {
        this.selectedEvent = event;
        displayEventDetails(event);
        displayAvailableSpeakers(event);
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
    }
}
