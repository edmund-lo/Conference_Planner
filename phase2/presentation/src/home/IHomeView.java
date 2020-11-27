package home;

import common.ISessionView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IHomeView extends ISessionView {
    void setTitle(String title);
    void setUnreadButtonText(String text);
    void setViewScheduleButtonText(String text);

    EventHandler<ActionEvent> getUnreadButtonAction();
    void setUnreadButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getViewScheduleButtonAction();
    void setViewScheduleButtonAction(EventHandler<ActionEvent> eventHandler);
}
