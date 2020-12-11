package login.impl;

import adapter.UserAccountAdapter;
import controllers.LoginController;
import javafx.event.ActionEvent;
import login.IForgotPresenter;
import login.IForgotView;
import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.ComponentFactory;
import util.TextResultUtil;

public class ForgotPresenter implements IForgotPresenter {
    private final IForgotView view;
    private final LoginController lc;

    public ForgotPresenter(IForgotView view) {
        this.view = view;
        init();
        this.lc = new LoginController();
    }

    @Override
    public void backButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedOutComponent(actionEvent, "login.fxml");
    }

    @Override
    public void previousButtonAction(ActionEvent actionEvent) {
        this.view.getTitledPane(1).setDisable(false);
        this.view.getTitledPane(2).setDisable(true);
        this.view.getTitledPane(1).setExpanded(true);
        this.view.getTitledPane(2).setExpanded(false);
    }

    @Override
    public void resetButtonAction(ActionEvent actionEvent) {
        clearResultText(3);

        JSONObject responseJson = lc.resetPassword(this.view.getUsername(), this.view.getPassword(),
                this.view.getConfirmPassword());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 3);
    }

    @Override
    public void confirmButtonAction(ActionEvent actionEvent) {
        clearResultText(2);

        JSONObject responseJson = lc.verifySecurityAnswers(this.view.getUsername(), this.view.getSecurityAnswer(1),
            this.view.getSecurityAnswer(2));
        if (String.valueOf(responseJson.get("status")).equals("success")) {
            this.view.getTitledPane(2).setDisable(true);
            this.view.getTitledPane(3).setDisable(false);
            this.view.getTitledPane(2).setExpanded(false);
            this.view.getTitledPane(3).setExpanded(true);
        } else
            setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 2);
    }

    @Override
    public void displaySecurityButtonAction(ActionEvent actionEvent) {
        clearResultText(1);

        JSONObject responseJson = lc.accountJson(this.view.getUsername());
        if (String.valueOf(responseJson.get("status")).equals("success")) {
            UserAccount account = UserAccountAdapter.getInstance()
                    .adaptData((JSONArray) responseJson.get("data")).get(0);
            this.view.setSecurityQuestion(account.getSecurityQuestion1(), 1);
            this.view.setSecurityQuestion(account.getSecurityQuestion2(), 2);
            this.view.getTitledPane(1).setDisable(true);
            this.view.getTitledPane(2).setDisable(false);
            this.view.getTitledPane(1).setExpanded(false);
            this.view.getTitledPane(2).setExpanded(true);
        } else
            setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public void setResultText(String resultText, String status, int index) {
        switch (index) {
            case 1:
                if (status.equals("error") || status.equals("warning"))
                    TextResultUtil.getInstance().addPseudoClass(status, this.view.getUsernameField());
                break;
            case 2:
                if (status.equals("error") || status.equals("warning")) {
                    TextResultUtil.getInstance().addPseudoClass(status, this.view.getSecurityAnswerField(1));
                    TextResultUtil.getInstance().addPseudoClass(status, this.view.getSecurityAnswerField(2));
                }
                break;
            case 3:
                if (status.equals("error") || status.equals("warning")) {
                    TextResultUtil.getInstance().addPseudoClass(status, this.view.getPasswordField());
                    TextResultUtil.getInstance().addPseudoClass(status, this.view.getConfirmPasswordField());
                }
        }
        this.view.setResultText(resultText, index);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public void init() {
        this.view.setFirstBackButtonAction(this::backButtonAction);
        this.view.setSecondBackButtonAction(this::backButtonAction);
        this.view.setPreviousButtonAction(this::previousButtonAction);
        this.view.setResetButtonAction(this::resetButtonAction);
        this.view.setConfirmButtonAction(this::confirmButtonAction);
        this.view.setSecurityButtonAction(this::displaySecurityButtonAction);
    }

    private void clearResultText(int index) {
        this.view.setResultText("", index);
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl(index));
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getPasswordField());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getConfirmPasswordField());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getUsernameField());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getSecurityAnswerField(1));
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getSecurityAnswerField(2));
    }
}
