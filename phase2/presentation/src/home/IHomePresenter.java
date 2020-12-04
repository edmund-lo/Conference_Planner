package home;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface IHomePresenter extends IPresenter {
    void setGreeting(String greeting);
    void setUnreadMessages(String unreadMessages);
    void setAttendingEvents(String attendingEvents);
}
