package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface IRescheduleCancelEventPresenter extends IPresenter {
    void cancelButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
