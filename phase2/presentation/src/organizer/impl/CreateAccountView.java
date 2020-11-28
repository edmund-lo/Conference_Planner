package organizer.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import organizer.ICreateAccountView;

public class CreateAccountView implements ICreateAccountView {
    @FXML
    private ChoiceBox<String> userType;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Text registerErrorMsg;

    @FXML
    public void executeAddCreateAccount(ActionEvent event) {
        if (createAccountButtonAction != null) createAccountButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new CreateAccountPresenter(this);
    }

    private CreateAccountPresenter presenter;
    private EventHandler<ActionEvent> createAccountButtonAction;
    private Stage stage;

    @Override
    public String getUserType() {
        return this.userType.getValue();
    }

    @Override
    public void setUserType(String userType) {
        this.userType.setValue(userType);
    }

    @Override
    public String getUsername() {
        return this.username.getText();
    }

    @Override
    public void setUsername(String username) {
        this.username.setText(username);
    }

    @Override
    public String getFirstName() {
        return this.firstName.getText();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public String getLastName() {
        return this.lastName.getText();
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    @Override
    public String getPassword() {
        return this.password.getText();
    }

    @Override
    public void setPassword(String password) {
        this.password.setText(password);
    }

    @Override
    public String getConfirmPassword() {
        return this.confirmPassword.getText();
    }

    @Override
    public void setConfirmPassword(String password) {
        this.confirmPassword.setText(password);
    }

    @Override
    public void setErrorMsg(String error) {
        this.registerErrorMsg.setText(error);
    }

    @Override
    public TextField getUsernameField() {
        return this.username;
    }

    @Override
    public TextField getFirstNameField() {
        return this.firstName;
    }

    @Override
    public TextField getLastNameField() {
        return this.lastName;
    }

    @Override
    public PasswordField getPasswordField() {
        return this.password;
    }

    @Override
    public PasswordField getConfirmPasswordField() {
        return this.confirmPassword;
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
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
