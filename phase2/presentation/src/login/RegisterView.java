package login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterView implements IRegisterView {
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
    public void executeAddGoBack(ActionEvent event) {
        if (backButtonAction != null) backButtonAction.handle(event);
    }
    @FXML
    public void executeAddRegister(ActionEvent event) {
        if (registerButtonAction != null) registerButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new RegisterPresenter(this);
    }

    private RegisterPresenter presenter;
    private EventHandler<ActionEvent> registerButtonAction;
    private EventHandler<ActionEvent> backButtonAction;
    private Stage stage;

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
    public EventHandler<ActionEvent> getBackButtonAction() {
        return this.backButtonAction;
    }

    @Override
    public void setBackButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.backButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getRegisterButtonAction() {
        return this.registerButtonAction;
    }

    @Override
    public void setRegisterButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.registerButtonAction = eventHandler;
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
