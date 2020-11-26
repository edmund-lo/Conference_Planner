package login;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface IForgotPresenter extends IPresenter {
    void backButtonAction(ActionEvent actionEvent);
    void recoverButtonAction(ActionEvent actionEvent);
    String generatePrompt();
    void setResult(String result, int resultId);
}
