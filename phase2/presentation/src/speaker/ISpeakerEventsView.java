package speaker;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import model.ScheduleEntry;

import java.time.Duration;
import java.time.LocalDateTime;

public interface ISpeakerEventsView extends ILoggedInView {
    TableView<ScheduleEntry> getEventsTable();
    TableColumn<ScheduleEntry, String> getEventNameColumn();
    TableColumn<ScheduleEntry, String> getRoomNameColumn();
    TableColumn<ScheduleEntry, LocalDateTime> getEventStartColumn();
    TableColumn<ScheduleEntry, LocalDateTime> getEventEndColumn();
    FlowPane getRecipientFlowPane();

    void setSummaryEventName(String eventName);
    void setSummaryCapacity(int capacity);
    void setSummaryRoomName(String roomName);
    void setSummaryStart(LocalDateTime start);
    void setSummaryEnd(LocalDateTime end);
    void setSummaryDuration(Duration duration);
    void setSummaryAmenities(String amenities);
    void setSummaryAttendees(String attendees);
    void setSummarySpeakers(String speakers);
    String getSender();
    void setSender(String sender);
    String getContent();
    void setContent(String content);
    void setResultMsg(String resultMsg);

    EventHandler<ActionEvent> getSendButtonAction();
    void setSendButtonAction(EventHandler<ActionEvent> eventHandler);
}
