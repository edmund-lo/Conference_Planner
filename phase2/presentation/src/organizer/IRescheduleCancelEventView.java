package organizer;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ScheduleEntry;
import util.DateTimePicker;
import util.NumberTextField;
import util.ToggleSwitch;

import java.time.Duration;
import java.time.LocalDateTime;

public interface IRescheduleCancelEventView extends ILoggedInView {
    TableView<ScheduleEntry> getEventsTable();
    TableColumn<ScheduleEntry, String> getEventNameColumn();
    TableColumn<ScheduleEntry, String> getRoomNameColumn();
    TableColumn<ScheduleEntry, LocalDateTime> getEventStartColumn();
    TableColumn<ScheduleEntry, LocalDateTime> getEventEndColumn();
    TableColumn<ScheduleEntry, Integer> getRemainingSpotsColumn();
    ToggleSwitch getToggleSwitch();
    NumberTextField getSummaryCapacityField();
    ChoiceBox<String> getSummaryRoomsChoiceBox();
    DateTimePicker getSummaryStart();
    DateTimePicker getSummaryEnd();
    ChoiceBox<String> getSummaryVipChoiceBox();

    void setSummaryEventName(String eventName);
    void setSummaryCapacity(int capacity);
    void setSummaryRemainingSpots(int remainingSpots);
    void setSummaryRoomName(String roomName);
    void setSummaryStart(LocalDateTime start);
    void setSummaryEnd(LocalDateTime end);
    void setSummaryDuration(Duration duration);
    void setSummaryAmenities(String amenities);
    void setSummaryAttendees(String attendees);
    void setSummarySpeakers(String speakers);
    void setSummaryVip(String vip);
    void setResultText(String resultText);

    EventHandler<ActionEvent> getCancelButtonAction();
    void setCancelButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRescheduleButtonAction();
    void setRescheduleButtonAction(EventHandler<ActionEvent> eventHandler);
}
