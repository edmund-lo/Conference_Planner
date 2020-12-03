package admin.impl;

//import Controllers.ILoginController
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import admin.ICreateAccountPresenter;
import admin.ICreateAccountView;

public class CreateAccountPresenter implements ICreateAccountPresenter {
    private ICreateAccountView view;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public CreateAccountPresenter(ICreateAccountView view) {
        this.view = view;
        init();
    }

    @Override
    public void createAccountButtonAction(ActionEvent actionEvent) {
        clearError();

        //call lc.register method
    }

    @Override
    public void setError(String error, int errorId) {
        if (errorId == 0) {
            this.view.getPasswordField().pseudoClassStateChanged(errorClass, true);
            this.view.getConfirmPasswordField().pseudoClassStateChanged(errorClass, true);
        }
        if (errorId == 1) {
            this.view.getPasswordField().pseudoClassStateChanged(errorClass, true);
            this.view.getConfirmPasswordField().pseudoClassStateChanged(errorClass, true);
            this.view.getUsernameField().pseudoClassStateChanged(errorClass, true);
        } else if (errorId == 2) {
            this.view.getUsernameField().pseudoClassStateChanged(errorClass, true);
        }
        this.view.setResultMsg(error);
    }

    @Override
    public void init() {
        this.view.setCreateAccountButtonAction(this::createAccountButtonAction);
    }

    private void clearError() {
        this.view.setResultMsg("");
        this.view.getPasswordField().pseudoClassStateChanged(errorClass, false);
        this.view.getConfirmPasswordField().pseudoClassStateChanged(errorClass, false);
        this.view.getUsernameField().pseudoClassStateChanged(errorClass, false);
    }
}
