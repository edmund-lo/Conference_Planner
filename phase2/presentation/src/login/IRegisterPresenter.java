package login;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface IRegisterPresenter extends IPresenter {
    void backButtonAction(ActionEvent actionEvent);
    void registerButtonAction(ActionEvent actionEvent);
    void setError(String error, int errorId);
}
