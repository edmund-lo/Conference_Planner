package login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginView implements ILoginView {

    @FXML
    public Text title;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text loginErrorMsg;

    @FXML
    public void executeAddLogin(ActionEvent event) {
        if (loginButtonAction != null) loginButtonAction.handle(event);
    }
    @FXML
    public void executeAddRegister(ActionEvent event) {
        if (registerButtonAction != null) registerButtonAction.handle(event);
    }
    @FXML
    public void executeAddForgotPassword(ActionEvent event) {
        if (forgotPasswordButtonAction != null) forgotPasswordButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        presenter = new LoginPresenter(this);
    }

    private LoginPresenter presenter;
    private EventHandler<ActionEvent> registerButtonAction;
    private EventHandler<ActionEvent> loginButtonAction;
    private EventHandler<ActionEvent> forgotPasswordButtonAction;

    @Override
    public String getUsername() {
        return this.username.getText();
    }

    @Override
    public void setUsername(String username) {
        this.username.setText(username);
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
    public void setLoginErrorMsg(String error) {
        this.loginErrorMsg.setText(error);
    }

    @Override
    public EventHandler<ActionEvent> getLoginButtonAction() {
        return loginButtonAction;
    }

    @Override
    public void setLoginButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.loginButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getRegisterButtonAction() {
        return registerButtonAction;
    }

    @Override
    public void setRegisterButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.registerButtonAction = eventHandler;
    }

    @Override
    public EventHandler<ActionEvent> getForgotPasswordButtonAction() {
        return forgotPasswordButtonAction;
    }

    @Override
    public void setForgotPasswordButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.forgotPasswordButtonAction = eventHandler;
    }
}