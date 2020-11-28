package toolbar.impl;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import toolbar.IToolbarPresenter;
import toolbar.IToolbarView;
import util.ComponentFactory;

public class ToolbarPresenter implements IToolbarPresenter {
    private IToolbarView view;

    public ToolbarPresenter(IToolbarView view) {
        this.view = view;
        init();
    }

    @Override
    public void homeButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "home.fxml");
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "viewSchedule.fxml");
    }

    @Override
    public void viewEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "viewEvents.fxml");
    }

    @Override
    public void messagingButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messaging.fxml");
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createAccount.fxml");
    }

    @Override
    public void createRoomButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createRoom.fxml");
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createEvent.fxml");
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "scheduleSpeaker.fxml");
    }

    @Override
    public void cancelEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "rescheduleCancelEvent.fxml");
    }

    @Override
    public void messageSpeakersButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messageSpeakers.fxml");
    }

    @Override
    public void messageAttendeesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messageAttendees.fxml");
    }

    @Override
    public void speakerEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "speakerEvents.fxml");
    }

    @Override
    public void unlockAccountsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "unlockAccounts.fxml");
    }

    @Override
    public void deleteMessagesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "deleteMessages.fxml");
    }

    @Override
    public void removeEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "removeEvents.fxml");
    }

    @Override
    public void logoutButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            ComponentFactory.getInstance().createLoginComponent(this.view.getStage(), "login.fxml");
    }

    @Override
    public void init() {
        //call lc.getAccountType method to restrict function access
        this.view.setHomeButtonAction(this::homeButtonAction);
        this.view.setViewScheduleButtonAction(this::viewScheduleButtonAction);
        this.view.setViewEventsButtonAction(this::viewEventsButtonAction);
        this.view.setMessagingButtonAction(this::messagingButtonAction);
        this.view.setCreateAccountButtonAction(this::createAccountButtonAction);
        this.view.setCreateRoomButtonAction(this::createRoomButtonAction);
        this.view.setCreateEventButtonAction(this::createEventButtonAction);
        this.view.setScheduleSpeakerButtonAction(this::scheduleSpeakerButtonAction);
        this.view.setCancelEventButtonAction(this::cancelEventButtonAction);
        this.view.setMessageSpeakersButtonAction(this::messageSpeakersButtonAction);
        this.view.setMessageAttendeesButtonAction(this::messageAttendeesButtonAction);
        this.view.setSpeakerEventsButtonAction(this::speakerEventsButtonAction);
        this.view.setUnlockAccountsButtonAction(this::unlockAccountsButtonAction);
        this.view.setDeleteMessagesButtonAction(this::deleteMessagesButtonAction);
        this.view.setRemoveEventsButtonAction(this::removeEventsButtonAction);
        this.view.setLogoutButtonAction(this::logoutButtonAction);
    }
}
