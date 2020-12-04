package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface IRemoveEventsPresenter extends IPresenter {
    void removeButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
