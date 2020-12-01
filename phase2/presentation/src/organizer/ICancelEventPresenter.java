package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface ICancelEventPresenter extends IPresenter {
    void cancelButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
