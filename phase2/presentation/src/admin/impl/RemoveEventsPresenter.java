package admin.impl;

import admin.IRemoveEventsPresenter;
import admin.IRemoveEventsView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class RemoveEventsPresenter implements IRemoveEventsPresenter {
    private IRemoveEventsView view;

    public RemoveEventsPresenter(IRemoveEventsView view) {
        this.view = view;
        init();
    }

    @Override
    public void removeButtonAction(ActionEvent actionEvent) {
        clearResult();

        //call oc.cancelEvent method
    }

    @Override
    public void setResultText(String resultText) {
        this.view.setResultText(resultText);
    }

    @Override
    public List<ScheduleEntry> getEvents() {
        //List<String[]> resultJson = ac.getAlEvents method
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
                (observable, oldValue, newValue) -> displayEventDetails(newValue));
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
    public void init() {
        this.view.setRemoveButtonAction(this::removeButtonAction);
        List<ScheduleEntry> allEvents = getEvents();
        displayEvents(allEvents);
    }

    private void clearResult() {
        this.view.setResultText("");
    }
}
