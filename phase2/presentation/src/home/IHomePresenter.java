package home;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface IHomePresenter extends IPresenter {
    void setGreeting();
    void setUnreadMessages();
    void setAttendingEvents();
}
