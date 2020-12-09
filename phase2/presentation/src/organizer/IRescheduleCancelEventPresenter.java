package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.ScheduleEntry;
import java.util.List;

public interface IRescheduleCancelEventPresenter extends IPresenter {
    void cancelButtonAction(ActionEvent actionEvent);
    void rescheduleButtonAction(ActionEvent actionEvent);
    void toggleSwitchListener();
    EventHandler<Event> toggleSwitchHandler(Event Event);
    void setResultText(String resultText, String status);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
