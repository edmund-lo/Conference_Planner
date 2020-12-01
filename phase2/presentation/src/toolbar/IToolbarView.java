package toolbar;

import common.ILoggedInView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface IToolbarView extends ILoggedInView {
    int getSessionUserType();
    void setSessionUserType(int userType);

    EventHandler<ActionEvent> getHomeButtonAction();
    void setHomeButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getViewScheduleButtonAction();
    void setViewScheduleButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getViewEventsButtonAction();
    void setViewEventsButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMessagingButtonAction();
    void setMessagingButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getCreateAccountButtonAction();
    void setCreateAccountButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getCreateRoomButtonAction();
    void setCreateRoomButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getCreateEventButtonAction();
    void setCreateEventButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getScheduleSpeakerButtonAction();
    void setScheduleSpeakerButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getCancelEventButtonAction();
    void setCancelEventButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMessageSpeakersButtonAction();
    void setMessageSpeakersButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getMessageAttendeesButtonAction();
    void setMessageAttendeesButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getSpeakerEventsButtonAction();
    void setSpeakerEventsButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getUnlockAccountsButtonAction();
    void setUnlockAccountsButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getDeleteMessagesButtonAction();
    void setDeleteMessagesButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getRemoveEventsButtonAction();
    void setRemoveEventsButtonAction(EventHandler<ActionEvent> eventHandler);
    EventHandler<ActionEvent> getLogoutButtonAction();
    void setLogoutButtonAction(EventHandler<ActionEvent> eventHandler);
}
