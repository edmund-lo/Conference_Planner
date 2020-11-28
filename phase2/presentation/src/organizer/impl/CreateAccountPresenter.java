package organizer.impl;

//import Controllers.ILoginController
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import organizer.ICreateAccountPresenter;
import organizer.ICreateAccountView;

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

        if (this.view.getUsername().equals("") || this.view.getFirstName().equals("") ||
                this.view.getLastName().equals("") || this.view.getPassword().equals("") ||
                this.view.getConfirmPassword().equals(""))
            setError("Fields cannot be empty!", 1);
        else if (!this.view.getPassword().equals(this.view.getConfirmPassword()))
            setError("Passwords do not match!", 0);
        else {
            //call lc.register method
        }
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
            this.view.getFirstNameField().pseudoClassStateChanged(errorClass, true);
            this.view.getLastNameField().pseudoClassStateChanged(errorClass, true);
        } else if (errorId == 2) {
            this.view.getUsernameField().pseudoClassStateChanged(errorClass, true);
        }
        this.view.setErrorMsg(error);
    }

    @Override
    public void init() {
        this.view.setCreateAccountButtonAction(this::createAccountButtonAction);
    }

    private void clearError() {
        this.view.setErrorMsg("");
        this.view.getPasswordField().pseudoClassStateChanged(errorClass, false);
        this.view.getConfirmPasswordField().pseudoClassStateChanged(errorClass, false);
        this.view.getUsernameField().pseudoClassStateChanged(errorClass, false);
        this.view.getFirstNameField().pseudoClassStateChanged(errorClass, false);
        this.view.getLastNameField().pseudoClassStateChanged(errorClass, false);
    }
}
