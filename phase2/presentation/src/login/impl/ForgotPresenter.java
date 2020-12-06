package login.impl;

import controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import login.IForgotPresenter;
import login.IForgotView;
import org.json.simple.JSONObject;
import util.ComponentFactory;
import util.TextResultUtil;

public class ForgotPresenter implements IForgotPresenter {
    private IForgotView view;
    private LoginController lc;

    public ForgotPresenter(IForgotView view) {
        this.view = view;
        init();
    }

    @Override
    public void backButtonAction(ActionEvent actionEvent) {
        Stage stage = this.view.getStage();
        ComponentFactory.getInstance().createLoggedOutComponent(stage, "login.fxml");
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

        //JSONObject json = lc.resetPassword();
        JSONObject responseJson = new JSONObject();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 3);
    }

    @Override
    public void confirmButtonAction(ActionEvent actionEvent) {
        clearResultText(2);

        //JSONObject responseJson = lc.securityCheck()
        JSONObject responseJson = new JSONObject();
        if (responseJson.get("status").equals("success")) {
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

        //JSONObject responseJson = lc.forgot()
        JSONObject responseJson = new JSONObject();
        if (responseJson.get("status").equals("success")) {
            this.view.getTitledPane(1).setDisable(true);
            this.view.getTitledPane(2).setDisable(false);
            this.view.getTitledPane(1).setExpanded(false);
            this.view.getTitledPane(2).setExpanded(true);
        } else
            setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public void setResultText(String resultText, String status, int index) {
        if (status.equals("error") || status.equals("warning"))
            TextResultUtil.getInstance().addPseudoClass(status, this.view.getSecurityAnswerField());
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
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getSecurityAnswerField());
    }
}
