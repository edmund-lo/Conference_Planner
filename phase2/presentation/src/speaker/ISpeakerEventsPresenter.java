package speaker;

import common.IPresenter;
import javafx.event.ActionEvent;
import model.ScheduleEntry;
import java.util.List;

public interface ISpeakerEventsPresenter extends IPresenter {
    void sendButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    List<ScheduleEntry> getAllSpeakerEvents();
    void displaySpeakerEvents(List<ScheduleEntry> speakerSchedule);
    void displayEventDetails(ScheduleEntry event);
    void displayRecipients(ScheduleEntry event);
}
