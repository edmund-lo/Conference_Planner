package organizer.impl;

import adapter.ScheduleAdapter;
import common.UserAccountHolder;
import controllers.OrganizerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ScheduleEntry;
import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import organizer.IRescheduleCancelEventPresenter;
import organizer.IRescheduleCancelEventView;
import util.DateTimePicker;
import util.DateTimeUtil;
import util.TextResultUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class RescheduleCancelEventPresenter implements IRescheduleCancelEventPresenter {
    private final IRescheduleCancelEventView view;
    private final OrganizerController oc;
    private ScheduleEntry selectedEvent;

    public RescheduleCancelEventPresenter(IRescheduleCancelEventView view) {
        this.view = view;
        getUserData();
        this.oc = new OrganizerController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void cancelButtonAction(ActionEvent actionEvent) {
        clearResultText();

        JSONObject responseJson = oc.cancelEvent(this.selectedEvent.getEventId());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        if (String.valueOf(responseJson.get("status")).equals("success")) {
            clearForm();
            List<ScheduleEntry> allEvents = getEvents();
            displayEvents(allEvents);
        }
    }

    @Override
    public void rescheduleButtonAction(ActionEvent actionEvent) {
        clearResultText();

        JSONObject queryJson = constructEventJson();
        JSONObject responseJson = oc.rescheduleEvent(queryJson);
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        if (String.valueOf(responseJson.get("status")).equals("success")) {
            List<ScheduleEntry> allEvents = getEvents();
            displayEvents(allEvents);
        }
    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public List<ScheduleEntry> getEvents() {
        JSONObject responseJson = oc.getAllEventsIncludingCancelled();
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
        this.view.getCancelledColumn().setCellValueFactory(param -> param.getValue().cancelledProperty());

        ObservableList<ScheduleEntry> observableSchedule = FXCollections.observableArrayList(schedule);
        this.view.getEventsTable().setItems(observableSchedule);
        this.view.getEventsTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayEventDetails(newValue));
    }

    @Override
    public void displayEventDetails(ScheduleEntry event) {
        this.selectedEvent = event;
        if(event == null){
            return;
        }
        JSONObject queryJson = constructRoomRequestJson(event);
        JSONObject responseJson = oc.listPossibleRooms(queryJson);
        displayPossibleRooms((JSONArray) responseJson.get("data"));
        this.view.setSummaryEventName(event.getEventName());
        this.view.setSummaryRoomName(event.getRoomName());
        this.view.setSummaryAttendees(event.getAttendees());
        this.view.setSummaryAmenities(event.getAmenities());
        this.view.setSummaryDuration(event.getDuration());
        this.view.setSummaryStart(event.getStart());
        this.view.setSummaryEnd(event.getEnd());
        this.view.setSummarySpeakers(event.getSpeakers());
        this.view.setSummaryCapacity(event.getCapacity());
        this.view.setSummaryRemainingSpots(event.getRemainingSpots());
        this.view.setSummaryVip(event.isVip() ? "Yes" : "No");
        setEditableFields(event.isCancelled());
    }

    @Override
    public void init() {
        this.view.setCancelButtonAction(this::cancelButtonAction);
        this.view.setRescheduleButtonAction(this::rescheduleButtonAction);
        updateDuration(this.view.getSummaryStart());
        updateDuration(this.view.getSummaryEnd());
        List<ScheduleEntry> allEvents = getEvents();
        displayEvents(allEvents);
    }

    @Override
    public void getUserData() {
        UserAccountHolder holder = UserAccountHolder.getInstance();
        UserAccount account = holder.getUserAccount();
        this.view.setSessionUsername(account.getUsername());
        this.view.setSessionUserType(account.getUserType());
    }

    private void updateDuration(DateTimePicker picker) {
        picker.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            LocalDateTime start = this.view.getSummaryStart().getDateTimeValue();
            LocalDateTime end = this.view.getSummaryEnd().getDateTimeValue();
            if(start == null | end == null){
                this.view.setSummaryDuration(Duration.ZERO);
            }
            else{
                this.view.setSummaryDuration(Duration.between(start, end));
            }
        }));
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
    }

    private void clearForm() {
        this.view.setSummaryStart(null);
        this.view.setSummaryEnd(null);
        this.view.setSummaryCapacity(0);
        this.view.setSummaryDuration(Duration.ZERO);
        this.view.setSummaryAttendees("");
        this.view.setSummarySpeakers("");
        this.view.setSummaryRemainingSpots(0);
    }

    private void displayPossibleRooms(JSONArray jsonArray) {
        this.view.getSummaryRoomsChoiceBox().getItems().clear();
        for (Object o : jsonArray)
            this.view.getSummaryRoomsChoiceBox().getItems().add(String.valueOf(o));
    }

    private void setEditableFields(boolean cancelled) {
        this.view.getRescheduleButton().setDisable(!cancelled);
        this.view.getCancelButton().setDisable(cancelled);
        if (cancelled) { // cancelled event
            this.view.getSummaryStart().setDisable(false);
            this.view.getSummaryEnd().setDisable(false);
            this.view.getSummaryCapacityField().setDisable(false);
            this.view.getSummaryVipChoiceBox().setDisable(false);
            this.view.getSummaryRoomsChoiceBox().setDisable(false);
        } else { // not cancelled event
            this.view.getSummaryStart().setDisable(true);
            this.view.getSummaryEnd().setDisable(true);
            this.view.getSummaryCapacityField().setDisable(true);
            this.view.getSummaryVipChoiceBox().setDisable(true);
            this.view.getSummaryRoomsChoiceBox().setDisable(true);
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject constructRoomRequestJson(ScheduleEntry event) {
        JSONObject queryJson = new JSONObject();
        queryJson.put("capacity", event.getCapacity());
        queryJson.put("chairs", event.getAmenities().contains("Chairs"));
        queryJson.put("tables", event.getAmenities().contains("Tables"));
        queryJson.put("projector", event.getAmenities().contains("Projector"));
        queryJson.put("sound", event.getAmenities().contains("Sound"));
        return queryJson;
    }

    @SuppressWarnings("unchecked")
    private JSONObject constructEventJson() {
        JSONObject queryJson = new JSONObject();
        queryJson.put("eventId", this.selectedEvent.getEventId());
        queryJson.put("capacity", this.view.getSummaryCapacityField().getNumber());
        queryJson.put("vip", this.view.getSummaryVipChoiceBox().getValue().equals("Yes"));
        queryJson.put("roomName", this.view.getSummaryRoomsChoiceBox().getValue());
        queryJson.put("start", this.view.getSummaryStart().getDateTimeValue());
        queryJson.put("end", this.view.getSummaryEnd().getDateTimeValue());
        return queryJson;
    }
}
