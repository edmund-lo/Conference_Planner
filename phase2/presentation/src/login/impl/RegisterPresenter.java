package login.impl;

//import Controllers.ILoginController
import javafx.css.PseudoClass;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import login.IRegisterPresenter;
import login.IRegisterView;
import util.ComponentFactory;

public class RegisterPresenter implements IRegisterPresenter {
    private IRegisterView view;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public RegisterPresenter(IRegisterView view) {
        this.view = view;
        init();
    }

    @Override
    public void backButtonAction(ActionEvent actionEvent) {
        Stage stage = this.view.getStage();
        ComponentFactory.getInstance().createLoggedOutComponent(stage, "login.fxml");
    }

    @Override
    public void registerButtonAction(ActionEvent actionEvent) {
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
        } else if (errorId == 1) {
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
        this.view.setBackButtonAction(this::backButtonAction);
        this.view.setRegisterButtonAction(this::registerButtonAction);
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
