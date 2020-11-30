package attendee;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface IViewEventsPresenter extends IPresenter {
    void pressButtonAction(ActionEvent actionEvent);
    void setResult(String result);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
