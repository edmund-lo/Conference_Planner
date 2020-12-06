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
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "home.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "viewSchedule.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void viewEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "viewEvents.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void messagingButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messaging.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createAccount.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void createRoomButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createRoom.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "createEvent.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "scheduleSpeaker.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void cancelEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "rescheduleCancelEvent.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void messageSpeakersButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messageSpeakers.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void messageAttendeesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messageAttendees.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void speakerEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "speakerEvents.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void unlockAccountsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "unlockAccounts.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void setVipButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "setVip.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void deleteMessagesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "deleteMessages.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void removeEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "removeEvents.fxml",
                this.view.getSessionUsername(), this.view.getSessionUserType());
    }

    @Override
    public void logoutButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            ComponentFactory.getInstance().createLoggedOutComponent(this.view.getStage(), "login.fxml");
    }

    @Override
    public void filterAccess() {
        if (this.view.getSessionUserType().equals("Organizer")) {
            this.view.getAdminGroup().getChildren().clear();
            this.view.getSpeakerGroup().getChildren().clear();
        } else if (this.view.getSessionUserType().equals("Speaker")) {
            this.view.getAdminGroup().getChildren().clear();
            this.view.getOrganizerGroup().getChildren().clear();
        } else if (this.view.getSessionUserType().equals("Admin")) {
            this.view.getOrganizerGroup().getChildren().clear();
            this.view.getSpeakerGroup().getChildren().clear();
        } else {
            this.view.getAdminGroup().getChildren().clear();
            this.view.getSpeakerGroup().getChildren().clear();
            this.view.getOrganizerGroup().getChildren().clear();
        }
    }

    @Override
    public void init() {
        filterAccess();

        this.view.setHomeButtonAction(this::homeButtonAction);
        this.view.setViewScheduleButtonAction(this::viewScheduleButtonAction);
        this.view.setViewEventsButtonAction(this::viewEventsButtonAction);
        this.view.setMessagingButtonAction(this::messagingButtonAction);
        this.view.setCreateRoomButtonAction(this::createRoomButtonAction);
        this.view.setCreateEventButtonAction(this::createEventButtonAction);
        this.view.setScheduleSpeakerButtonAction(this::scheduleSpeakerButtonAction);
        this.view.setCancelEventButtonAction(this::cancelEventButtonAction);
        this.view.setMessageSpeakersButtonAction(this::messageSpeakersButtonAction);
        this.view.setMessageAttendeesButtonAction(this::messageAttendeesButtonAction);
        this.view.setSpeakerEventsButtonAction(this::speakerEventsButtonAction);
        this.view.setCreateAccountButtonAction(this::createAccountButtonAction);
        this.view.setUnlockAccountsButtonAction(this::unlockAccountsButtonAction);
        this.view.setSetVipButtonAction(this::setVipButtonAction);
        this.view.setDeleteMessagesButtonAction(this::deleteMessagesButtonAction);
        this.view.setRemoveEventsButtonAction(this::removeEventsButtonAction);
        this.view.setLogoutButtonAction(this::logoutButtonAction);
    }
}
