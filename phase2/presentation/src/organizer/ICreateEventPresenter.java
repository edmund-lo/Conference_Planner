package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;

import java.util.List;

public interface ICreateEventPresenter extends IPresenter {
    void createEventButtonAction(ActionEvent actionEvent);
    void previousFirstButtonAction(ActionEvent actionEvent);
    void previousSecondButtonAction(ActionEvent actionEvent);
    void findRoomsButtonAction(ActionEvent actionEvent);
    void previewRoomButtonAction(ActionEvent actionEvent);
    void summaryButtonAction(ActionEvent actionEvent);
    void setError(String error, int errorId);
    void setResult(String result);
    void observeAmenities();
    List<ScheduleEntry> getRoomSchedule();
    void displayRoomSchedule(List<ScheduleEntry> schedule);
}
