package home;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface IHomePresenter extends IPresenter {
    void unreadButtonAction(ActionEvent actionEvent);
    void viewScheduleButtonAction(ActionEvent actionEvent);
    void setGreeting(String greeting);
    void setUnreadMessages(String unreadMessages);
    void setAttendingEvents(String attendingEvents);
}
