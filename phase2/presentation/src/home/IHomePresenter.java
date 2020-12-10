package home;

import common.IPresenter;

public interface IHomePresenter extends IPresenter {
    void setGreeting();
    void setUnreadMessages();
    void setAttendingEvents();
}
