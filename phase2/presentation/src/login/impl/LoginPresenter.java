package login.impl;

//import Controllers.ILoginController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import login.ILoginPresenter;
import login.ILoginView;
import util.ComponentFactory;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public LoginPresenter(ILoginView view) {
        this.view = view;
        init();
    }

    @Override
    public void loginButtonAction(ActionEvent actionEvent) {
        clearError();

        if (this.view.getUsername().equals("") || this.view.getPassword().equals(""))
            setError("Fields cannot be empty!");
        else {
            //call lc.login method
            ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "home.fxml",
                    this.view.getUsername());
        }
    }

    @Override
    public void registerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedOutComponent(this.view.getStage(), "register.fxml");
    }

    @Override
    public void forgotPasswordButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedOutComponent(this.view.getStage(), "forgot.fxml");
    }

    @Override
    public void setError(String error) {
        this.view.getUsernameField().pseudoClassStateChanged(this.errorClass, true);
        this.view.getPasswordField().pseudoClassStateChanged(this.errorClass, true);
        this.view.setUsername("");
        this.view.setPassword("");
        this.view.setErrorMsg(error);
    }

    @Override
    public void init() {
        this.view.setLoginButtonAction(this::loginButtonAction);
        this.view.setRegisterButtonAction(this::registerButtonAction);
        this.view.setForgotPasswordButtonAction(this::forgotPasswordButtonAction);
    }

    private void clearError() {
        this.view.setErrorMsg("");
        this.view.getUsernameField().pseudoClassStateChanged(this.errorClass, false);
        this.view.getPasswordField().pseudoClassStateChanged(this.errorClass, false);
    }
}
