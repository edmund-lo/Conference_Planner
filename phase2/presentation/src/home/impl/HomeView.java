package home.impl;

import home.IHomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeView implements IHomeView {
    @FXML
    private Text title;
    @FXML
    private Button unreadButton;
    @FXML
    private Button attendingButton;

    /*@FXML
    public void executeAddMessaging(ActionEvent event) {
        if (unreadButtonAction != null) unreadButtonAction.handle(event);
    }
    @FXML
    public void executeAddViewSchedule(ActionEvent event) {
        if (viewScheduleButtonAction != null) viewScheduleButtonAction.handle(event);
    }*/
    @FXML
    public void initialize() {
        this.presenter = new HomePresenter(this);
    }

    private HomePresenter presenter;
    private EventHandler<ActionEvent> unreadButtonAction;
    private EventHandler<ActionEvent> viewScheduleButtonAction;
    private Stage stage;
    private String sessionUsername;
    private int sessionUserType;

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setUnreadButtonText(String text) {
        this.unreadButton.setText(text);
    }

    @Override
    public void setViewScheduleButtonText(String text) {
        this.attendingButton.setText(text);
    }

    /*@Override
    public EventHandler<ActionEvent> getUnreadButtonAction() {
        return this.unreadButtonAction;
    }

    @Override
    public void setUnreadButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.unreadButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getViewScheduleButtonAction() {
        return viewScheduleButtonAction;
    }

    @Override
    public void setViewScheduleButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.viewScheduleButtonAction = eventHandler;
    }*/

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Text getResultTextControl() {
        return null;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }

    @Override
    public int getSessionUserType() {
        return this.sessionUserType;
    }

    @Override
    public void setSessionUserType(int userType) {
        this.sessionUserType = userType;
    }
}
