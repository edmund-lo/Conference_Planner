package login;

import javafx.event.ActionEvent;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;


    public LoginPresenter(ILoginView view) {
        this.view = view;
        init();
    }

    @Override
    public void loginButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void registerButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void forgotPasswordButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void setLoginStatus() {

    }

    @Override
    public void init() {
        /*this.view.setUsername("");
        this.view.setPassword("");
        this.view.setLoginErrorMsg("");*/
        this.view.setLoginButtonAction(this::loginButtonAction);
        this.view.setRegisterButtonAction(this::registerButtonAction);
        this.view.setForgotPasswordButtonAction(this::forgotPasswordButtonAction);
    }
}
