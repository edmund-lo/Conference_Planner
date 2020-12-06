package admin;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface IRemoveEventsPresenter extends IPresenter {
    void removeButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<ScheduleEntry> getEvents();
    void displayEvents(List<ScheduleEntry> schedule);
    void displayEventDetails(ScheduleEntry event);
}
