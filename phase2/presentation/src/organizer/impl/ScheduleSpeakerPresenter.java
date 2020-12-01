package organizer.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import model.User;
import organizer.IScheduleSpeakerPresenter;
import organizer.IScheduleSpeakerView;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class ScheduleSpeakerPresenter implements IScheduleSpeakerPresenter {
    private IScheduleSpeakerView view;
    //private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public ScheduleSpeakerPresenter(IScheduleSpeakerView view) {
        this.view = view;
        init();
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        clearResult();

        String speaker = this.view.getAvailableSpeakerChoiceBox().getValue();
        //call oc.scheduleSpeaker method
        setResult("Successfully scheduled " + speaker + " to " + this.view.getSummaryEventName());
    }

    @Override
    public void setResult(String result) {
        this.view.setResultMsg(result);
    }

    @Override
    public List<ScheduleEntry> getAllEvents() {
        //List<String[]> resultJson = oc.getAllEvents method
        //List<ScheduleEntry> allEvents = ScheduleAdapter.adapt(resultJson);
        List<ScheduleEntry> allEvents = new ArrayList<>();
        return allEvents;
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
        //call oc.getAvailableSpeakers method
        List<User> speakerList = new ArrayList<>();
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
        displayEventDetails(event);
        displayAvailableSpeakers(event);
    }

    private void clearResult() {
        this.view.setResultMsg("");
    }
}
