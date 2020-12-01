package organizer;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import util.DateTimePicker;
import java.time.LocalDateTime;

public interface ICreateEventView extends ILoggedInView {
    String getEventName();
    void setEventName(String eventName);
    int getCapacity();
    void setCapacity(int capacity);
    boolean getAmenity(int index);
    void setAmenity(int index, boolean checked);
    String getRoomName();
    void setRoomName(String roomName);
    LocalDateTime getStart();
    void setStart(LocalDateTime start);
    LocalDateTime getEnd();
    void setEnd(LocalDateTime end);
    void setErrorMsg(String error);
    void setSummaryEventName(String eventName);
    void setSummaryCapacity(int capacity);
    void setSummaryRoomName(String roomName);
    void setSummaryStart(LocalDateTime start);
    void setSummaryEnd(LocalDateTime end);
    void setSummaryAmenities(String amenities);
    void setResultMsg(String result);

    ComboBox<String> getRoomComboBox();
    DateTimePicker getStartPicker();
    DateTimePicker getEndPicker();
    CheckBox getAmenityBox(int index);
    TitledPane getTitledPane(int index);
    VBox getTableContainer();

    EventHandler<ActionEvent> getFindRoomsButtonAction();
    void setFindRoomsButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getPreviousFirstButtonAction();
    void setPreviousFirstButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getPreviewRoomButtonAction();
    void setPreviewRoomButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getSummaryButtonAction();
    void setSummaryButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getPreviousSecondButtonAction();
    void setPreviousSecondButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getCreateEventButtonAction();
    void setCreateEventButtonAction(EventHandler<ActionEvent> eventHandler);
}
