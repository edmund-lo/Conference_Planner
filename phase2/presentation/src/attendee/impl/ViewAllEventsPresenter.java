package attendee.impl;

import adapter.ScheduleAdapter;
import attendee.IViewEventsPresenter;
import attendee.IViewEventsView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;
import util.TextResultUtil;

import java.util.List;

public class ViewAllEventsPresenter implements IViewEventsPresenter {
    private IViewEventsView view;

    public ViewAllEventsPresenter(IViewEventsView view) {
        this.view = view;
        this.ac = new AttendeeController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void pressButtonAction(ActionEvent actionEvent) {
        clearResultText();

        //JSONObject responseJson = ac.signUp(this.view.getEventName());
        JSONObject responseJson = new JSONObject();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        if (responseJson.get("status").equals("success")) init();
    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public List<ScheduleEntry> getEvents() {
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

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
    }
}
