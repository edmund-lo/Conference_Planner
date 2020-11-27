package toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Optional;
import util.ComponentFactory;

public class ToolbarPresenter implements IToolbarPresenter{
    private IToolbarView view;
    private final Stage stage;
    private final BorderPane root;

    public ToolbarPresenter(IToolbarView view) {
        this.view = view;
        this.stage = this.view.getStage();
        this.root = this.view.getRoot();
        init();
    }

    @Override
    public void homeButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "home.fxml");
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "viewSchedule.fxml");
    }

    @Override
    public void viewEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "viewEvents.fxml");
    }

    @Override
    public void messagingButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "messaging.fxml");
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "createAccount.fxml");
    }

    @Override
    public void createRoomButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "createRoom.fxml");
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "createEvent.fxml");
    }

    @Override
    public void scheduleSpeakerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "scheduleSpeaker.fxml");
    }

    @Override
    public void rescheduleCancelEventButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "rescheduleCancelEvent.fxml");
    }

    @Override
    public void messageSpeakersButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "messageSpeakers.fxml");
    }

    @Override
    public void messageAttendeesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "messageAttendee.fxml");
    }

    @Override
    public void speakerEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "speakerEvents.fxml");
    }

    @Override
    public void unlockAccountsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "unlockAccounts.fxml");
    }

    @Override
    public void deleteMessagesButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "deleteMessages.fxml");
    }

    @Override
    public void removeEventsButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createCenterComponent(this.stage, this.root, "removeEvents.fxml");
    }

    @Override
    public void logoutButtonAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm logout?");
        alert.show();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            ComponentFactory.getInstance().createLoginComponent(this.stage, "login.fxml");
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
