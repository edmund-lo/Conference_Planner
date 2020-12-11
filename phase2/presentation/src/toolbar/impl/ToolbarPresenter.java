package toolbar.impl;

import common.UserAccountHolder;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import model.UserAccount;
import toolbar.IToolbarPresenter;
import toolbar.IToolbarView;
import util.ComponentFactory;

public class ToolbarPresenter implements IToolbarPresenter {
    private final IToolbarView view;

    public ToolbarPresenter(IToolbarView view) {
        this.view = view;
        getUserData();
        init();
    }

    @Override
    public void homeButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "home.fxml");
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "viewSchedule.fxml");
    }

    @Override
    public void viewEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "viewEvents.fxml");
    }

    @Override
    public void messagingButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "messaging.fxml");
    }

    @Override
    public void friendsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "friends.fxml");
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "createAccount.fxml");
    }

    @Override
    public void createRoomButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "createRoom.fxml");
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "createEvent.fxml");
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "scheduleSpeaker.fxml");
    }

    @Override
    public void cancelEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "rescheduleCancelEvent.fxml");
    }

    @Override
    public void messageSpeakersButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "messageSpeakers.fxml");
    }

    @Override
    public void messageAttendeesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "messageAttendees.fxml");
    }

    @Override
    public void speakerEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "speakerEvents.fxml");
    }

    @Override
    public void unlockAccountsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "unlockAccounts.fxml");
    }

    @Override
    public void setVipButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "setVip.fxml");
    }

    @Override
    public void deleteMessagesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "deleteMessages.fxml");
    }

    @Override
    public void removeEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(actionEvent, "removeEvents.fxml");
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void logoutButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            UserAccountHolder holder = UserAccountHolder.getInstance();
            holder.setUserAccount(null);
            ComponentFactory.getInstance().createLoggedOutComponent(actionEvent, "login.fxml");
        }

    }

    @Override
    public void filterAccess() {
        switch (this.view.getSessionUserType()) {
            case "Organizer":
                this.view.getAdminGroup().getChildren().clear();
                this.view.getSpeakerGroup().getChildren().clear();
                break;
            case "Speaker":
                this.view.getAdminGroup().getChildren().clear();
                this.view.getOrganizerGroup().getChildren().clear();
                break;
            case "Admin":
                this.view.getOrganizerGroup().getChildren().clear();
                this.view.getSpeakerGroup().getChildren().clear();
                break;
            default:
                this.view.getAdminGroup().getChildren().clear();
                this.view.getSpeakerGroup().getChildren().clear();
                this.view.getOrganizerGroup().getChildren().clear();
                break;
        }
    }

    @Override
    public void init() {
        filterAccess();

        this.view.setHomeButtonAction(this::homeButtonAction);
        this.view.setViewScheduleButtonAction(this::viewScheduleButtonAction);
        this.view.setViewEventsButtonAction(this::viewEventsButtonAction);
        this.view.setMessagingButtonAction(this::messagingButtonAction);
        this.view.setFriendsButtonAction(this::friendsButtonAction);
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

    @Override
    public void getUserData() {
        UserAccountHolder holder = UserAccountHolder.getInstance();
        UserAccount account = holder.getUserAccount();
        this.view.setSessionUsername(account.getUsername());
        this.view.setSessionUserType(account.getUserType());
    }
}
