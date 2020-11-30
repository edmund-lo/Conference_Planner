package home;

import common.ILoggedInView;

public interface IHomeView extends ILoggedInView {
    void setTitle(String title);
    void setUnreadButtonText(String text);
    void setViewScheduleButtonText(String text);

    /*EventHandler<ActionEvent> getUnreadButtonAction();
    void setUnreadButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getViewScheduleButtonAction();
    void setViewScheduleButtonAction(EventHandler<ActionEvent> eventHandler);*/
}
