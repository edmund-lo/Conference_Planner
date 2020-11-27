package toolbar;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import util.ComponentFactory;

public class ToolbarPresenter implements IToolbarPresenter{
    private IToolbarView view;

    public ToolbarPresenter(IToolbarView view) {
        this.view = view;
        init();
    }

    @Override
    public void homeButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "home.fxml");
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "viewSchedule.fxml");
    }

    @Override
    public void viewEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "viewEvents.fxml");
    }

    @Override
    public void messagingButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "messaging.fxml");
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "createAccount.fxml");
    }

    @Override
    public void createRoomButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "createRoom.fxml");
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "createEvent.fxml");
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "scheduleSpeaker.fxml");
    }

    @Override
    public void rescheduleCancelEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "rescheduleCancelEvent.fxml");
    }

    @Override
    public void messageSpeakersButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "messageSpeakers.fxml");
    }

    @Override
    public void messageAttendeesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "messageAttendee.fxml");
    }

    @Override
    public void speakerEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "speakerEvents.fxml");
    }

    @Override
    public void unlockAccountsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "unlockAccounts.fxml");
    }

    @Override
    public void deleteMessagesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "deleteMessages.fxml");
    }

    @Override
    public void removeEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.view.getStage(), this.view.getRoot(),
                "removeEvents.fxml");
    }

    @Override
    public void logoutButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm logout?");
        alert.show();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            ComponentFactory.getInstance().createLoginComponent(this.view.getStage(), "login.fxml");
    }

    @Override
    public void init() {
        this.view.setHomeButtonAction(this::homeButtonAction);
        this.view.setViewScheduleButtonAction(this::viewScheduleButtonAction);
        this.view.setViewEventsButtonAction(this::viewEventsButtonAction);
        this.view.setMessagingButtonAction(this::messagingButtonAction);
        this.view.setCreateAccountButtonAction(this::createAccountButtonAction);
        this.view.setCreateRoomButtonAction(this::createRoomButtonAction);
        this.view.setCreateEventButtonAction(this::createEventButtonAction);
        this.view.setScheduleSpeakerButtonAction(this::scheduleSpeakerButtonAction);
        this.view.setRescheduleCancelEventButtonAction(this::rescheduleCancelEventButtonAction);
        this.view.setMessageSpeakersButtonAction(this::messageSpeakersButtonAction);
        this.view.setMessageAttendeesButtonAction(this::messageAttendeesButtonAction);
        this.view.setSpeakerEventsButtonAction(this::speakerEventsButtonAction);
        this.view.setUnlockAccountsButtonAction(this::unlockAccountsButtonAction);
        this.view.setDeleteMessagesButtonAction(this::deleteMessagesButtonAction);
        this.view.setRemoveEventsButtonAction(this::removeEventsButtonAction);
        this.view.setLogoutButtonAction(this::logoutButtonAction);
    }
}
