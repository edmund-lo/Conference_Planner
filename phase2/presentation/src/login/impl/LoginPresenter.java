package login.impl;

import adapter.UserAccountAdapter;
import controllers.LoginController;
import javafx.event.ActionEvent;
import login.ILoginPresenter;
import login.ILoginView;
import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.ComponentFactory;
import util.TextResultUtil;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private LoginController lc;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        //this.lc = new LoginController();
        init();
    }

    @Override
    public void loginButtonAction(ActionEvent actionEvent) {
        clearResultText();

        //JSONObject responseJson = lc.login(this.view.getUsername(), this.view.getPassword());
        JSONObject responseJson = new JSONObject();
        if (responseJson.get("status").equals("success")) {
            UserAccount userAccount = UserAccountAdapter.getInstance()
                    .adaptData((JSONArray) responseJson.get("data")).get(0);
            if (userAccount.isSetSecurity())
                ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "setup.fxml",
                        userAccount.getUsername(), userAccount.getUserType());
            else
                ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "home.fxml",
                    userAccount.getUsername(), userAccount.getUserType());
        } else
            setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
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
    public void setResultText(String resultText, String status) {
        this.view.setUsername("");
        this.view.setPassword("");
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getUsernameField());
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getPasswordField());
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public void init() {
        this.view.setLoginButtonAction(this::loginButtonAction);
        this.view.setRegisterButtonAction(this::registerButtonAction);
        this.view.setForgotPasswordButtonAction(this::forgotPasswordButtonAction);
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getUsernameField());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getPasswordField());
    }
}
