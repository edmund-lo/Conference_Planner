package toolbar.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import toolbar.IToolbarView;

public class ToolbarView implements IToolbarView {
    @FXML
    public void executeAddHome(ActionEvent event) {
        if (homeButtonAction != null) homeButtonAction.handle(event);
    }
    @FXML
    public void executeAddViewSchedule(ActionEvent event) {
        if (viewScheduleButtonAction != null) viewScheduleButtonAction.handle(event);
    }
    @FXML
    public void executeAddViewEvents(ActionEvent event) {
        if (viewEventsButtonAction != null) viewEventsButtonAction.handle(event);
    }
    @FXML
    public void executeAddMessaging(ActionEvent event) {
        if (messagingButtonAction != null) messagingButtonAction.handle(event);
    }
    @FXML
    public void executeAddCreateAccount(ActionEvent event) {
        if (createAccountButtonAction != null) createAccountButtonAction.handle(event);
    }
    @FXML
    public void executeAddCreateRoom(ActionEvent event) {
        if (createRoomButtonAction != null) createRoomButtonAction.handle(event);
    }
    @FXML
    public void executeAddCreateEvent(ActionEvent event) {
        if (createEventButtonAction != null) createEventButtonAction.handle(event);
    }
    @FXML
    public void executeAddScheduleSpeaker(ActionEvent event) {
        if (scheduleSpeakerButtonAction != null) scheduleSpeakerButtonAction.handle(event);
    }
    @FXML
    public void executeAddCancelEvent(ActionEvent event) {
        if (rescheduleCancelEventButtonAction != null) rescheduleCancelEventButtonAction.handle(event);
    }
    @FXML
    public void executeAddMessageSpeakers(ActionEvent event) {
        if (messageSpeakersButtonAction != null) messageSpeakersButtonAction.handle(event);
    }
    @FXML
    public void executeAddMessageAttendees(ActionEvent event) {
        if (messageAttendeesButtonAction != null) messageAttendeesButtonAction.handle(event);
    }
    @FXML
    public void executeAddSpeakerEvents(ActionEvent event) {
        if (speakerEventsButtonAction != null) speakerEventsButtonAction.handle(event);
    }
    @FXML
    public void executeAddUnlockAccounts(ActionEvent event) {
        if (unlockAccountsButtonAction != null) unlockAccountsButtonAction.handle(event);
    }
    @FXML
    public void executeAddDeleteMessages(ActionEvent event) {
        if (deleteMessagesButtonAction != null) deleteMessagesButtonAction.handle(event);
    }
    @FXML
    public void executeAddRemoveEvents(ActionEvent event) {
        if (removeEventsButtonAction != null) removeEventsButtonAction.handle(event);
    }
    @FXML
    public void executeAddLogout(ActionEvent event) {
        if (logoutButtonAction != null) logoutButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new ToolbarPresenter(this);
    }

    private ToolbarPresenter presenter;
    private EventHandler<ActionEvent> homeButtonAction;
    private EventHandler<ActionEvent> viewScheduleButtonAction;
    private EventHandler<ActionEvent> viewEventsButtonAction;
    private EventHandler<ActionEvent> messagingButtonAction;
    private EventHandler<ActionEvent> createAccountButtonAction;
    private EventHandler<ActionEvent> createRoomButtonAction;
    private EventHandler<ActionEvent> createEventButtonAction;
    private EventHandler<ActionEvent> scheduleSpeakerButtonAction;
    private EventHandler<ActionEvent> rescheduleCancelEventButtonAction;
    private EventHandler<ActionEvent> messageSpeakersButtonAction;
    private EventHandler<ActionEvent> messageAttendeesButtonAction;
    private EventHandler<ActionEvent> speakerEventsButtonAction;
    private EventHandler<ActionEvent> unlockAccountsButtonAction;
    private EventHandler<ActionEvent> deleteMessagesButtonAction;
    private EventHandler<ActionEvent> removeEventsButtonAction;
    private EventHandler<ActionEvent> logoutButtonAction;
    private Stage stage;
    private String sessionUsername;

    @Override
    public EventHandler<ActionEvent> getHomeButtonAction() {
        return this.homeButtonAction;
    }

    @Override
    public void setHomeButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.homeButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getViewScheduleButtonAction() {
        return this.viewScheduleButtonAction;
    }

    @Override
    public void setViewScheduleButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.viewScheduleButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getViewEventsButtonAction() {
        return this.viewEventsButtonAction;
    }

    @Override
    public void setViewEventsButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.viewEventsButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMessagingButtonAction() {
        return this.messagingButtonAction;
    }

    @Override
    public void setMessagingButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.messagingButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getCreateAccountButtonAction() {
        return this.createAccountButtonAction;
    }

    @Override
    public void setCreateAccountButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createAccountButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getCreateRoomButtonAction() {
        return this.createRoomButtonAction;
    }

    @Override
    public void setCreateRoomButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createRoomButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getCreateEventButtonAction() {
        return this.createEventButtonAction;
    }

    @Override
    public void setCreateEventButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createEventButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getScheduleSpeakerButtonAction() {
        return this.scheduleSpeakerButtonAction;
    }

    @Override
    public void setScheduleSpeakerButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.scheduleSpeakerButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getCancelEventButtonAction() {
        return this.rescheduleCancelEventButtonAction;
    }

    @Override
    public void setCancelEventButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.rescheduleCancelEventButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMessageSpeakersButtonAction() {
        return this.messageSpeakersButtonAction;
    }

    @Override
    public void setMessageSpeakersButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.messageSpeakersButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getMessageAttendeesButtonAction() {
        return this.messageAttendeesButtonAction;
    }

    @Override
    public void setMessageAttendeesButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.messageAttendeesButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getSpeakerEventsButtonAction() {
        return this.speakerEventsButtonAction;
    }

    @Override
    public void setSpeakerEventsButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.speakerEventsButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getUnlockAccountsButtonAction() {
        return this.unlockAccountsButtonAction;
    }

    @Override
    public void setUnlockAccountsButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.unlockAccountsButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getDeleteMessagesButtonAction() {
        return deleteMessagesButtonAction;
    }

    @Override
    public void setDeleteMessagesButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.deleteMessagesButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getRemoveEventsButtonAction() {
        return this.removeEventsButtonAction;
    }

    @Override
    public void setRemoveEventsButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.removeEventsButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getLogoutButtonAction() {
        return this.logoutButtonAction;
    }

    @Override
    public void setLogoutButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.logoutButtonAction = eventHandler;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }
}
