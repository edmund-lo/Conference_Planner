package attendee.impl;

import attendee.IViewEventsPresenter;
import attendee.IViewEventsView;
//import javafx.css.PseudoClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class ViewAllEventsPresenter implements IViewEventsPresenter {
    private IViewEventsView view;
    //private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public ViewAllEventsPresenter(IViewEventsView view) {
        this.view = view;
        init();
    }

    @Override
    public void pressButtonAction(ActionEvent actionEvent) {
        clearResult();

        //call ac.signUp method
    }

    @Override
    public void setResult(String result) {
        this.view.setResultMsg(result);
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
        this.view.getRemainingSpotsColumn().setCellValueFactory(new PropertyValueFactory<>("remainingSpots"));

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
        this.view.setSummaryRemainingSpots(event.getRemainingSpots());
    }

    @Override
    public void init() {
        this.view.setPressButtonAction(this::pressButtonAction);
        List<ScheduleEntry> allEvents = getEvents();
        displayEvents(allEvents);
    }

    private void clearResult() {
        this.view.setResultMsg("");
    }
}
